package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.ai.business.Colocacion;
import com.ai.models.ItemColocacion;

public class PersistenciaColocaciones extends Persistencia {
	
	public static PersistenciaColocaciones instance;
	
	private PersistenciaColocaciones() {
		
	}
	
	public static PersistenciaColocaciones getInstance() {
		
		if( instance == null ) {
			instance = new PersistenciaColocaciones();
		}
		
		return instance;
	}
	
	@Override
	public void insert(Object obj) {
		
		try {
			Colocacion colocacion = (Colocacion)obj;
			
			//Primero guardo la colocacion
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("insert into AplicacionesInteractivas.dbo.Colocaciones (idEdicion, pauta, fecha) values (?, ?, ?)");
			statement.setInt(1, colocacion.getIdEdicion());
			statement.setString(2, colocacion.getPauta());
			statement.setDate(3, new java.sql.Date(colocacion.getFecha().getTime()));
			
			statement.executeUpdate();
			
			PreparedStatement statement2 = conn.prepareStatement("SELECT @@IDENTITY as codigo");
			ResultSet generatedKeys = statement2.executeQuery();
			int colocacionId = -1;
			
			while( generatedKeys.next() ) {
				colocacionId = generatedKeys.getInt("codigo");
				
				break;
			}
			
			//Segundo guardo los items colocacion
			PersistenciaItemsColocacion.getInstance().insertAll(colocacionId, colocacion.getItemsColocaciones());
			
		}
		catch( Exception ex ) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
		
		
		
	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<Object> selectAll() {
		try {
			Vector<Object> list = new Vector<Object>();
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Colocaciones");
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idColocacion");
				int idEdicion = result.getInt("idEdicion");
				String pauta = result.getString("pauta");
				java.util.Date fecha = result.getDate("fecha");
				
				Colocacion colocacion = new Colocacion(id, idEdicion, pauta, fecha);
				
				Vector<ItemColocacion> itemsColocacion = PersistenciaItemsColocacion.getInstance().getItemsColocacionByColocacion(id);
				
				for (ItemColocacion itemColocacion : itemsColocacion) {
					colocacion.addItemColocacion(itemColocacion);
				}
				
				list.add(colocacion);
			}
			
			return list;
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
	}

	@Override
	public Object selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
