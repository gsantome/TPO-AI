package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.ai.models.Puesto;

public class PersistenciaPuestos extends Persistencia {

	public static PersistenciaPuestos instance;
	
	private PersistenciaPuestos() {
	
	}
	
	public static PersistenciaPuestos getInstance() {
		
		if( instance == null ) {
			instance = new PersistenciaPuestos();
		}
		
		return instance;
	}
	
	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub
		
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
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Puestos");
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idPuesto");
				String nombre = result.getString("nombre");
				String direccion = result.getString("direccion");
				String barrio = result.getString("barrio");
				String tipo = result.getString("tipo");
				int idZona = result.getInt("idZona");
				
				Puesto puesto = new Puesto(id, nombre, direccion, barrio, idZona, tipo);
				
				list.add(puesto);
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
		
		try {
			Puesto puesto = null;
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Puestos WHERE idPuesto = ?");
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int codigo = result.getInt("idPuesto");
				String nombre = result.getString("nombre");
				String direccion = result.getString("direccion");
				String barrio = result.getString("barrio");
				String tipo = result.getString("tipo");
				int idZona = result.getInt("idZona");
				
				puesto = new Puesto(codigo, nombre, direccion, barrio, idZona, tipo);
			}
			
			return puesto;			
		}
		catch( Exception ex ) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
		
	}

}
