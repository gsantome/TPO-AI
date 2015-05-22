package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import com.ai.models.ItemColocacion;
import com.ai.models.Publicacion;

public class PersistenciaItemsColocacion extends Persistencia {

	public static PersistenciaItemsColocacion instance;
	
	private PersistenciaItemsColocacion() {
		
	}
	
	public static PersistenciaItemsColocacion getInstance() {
		if( instance == null ) {
			instance = new PersistenciaItemsColocacion();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<ItemColocacion> getLastThreeDayItems(int idPuesto) {
		// debe devolver la lista del mas nuevo al mas viejo. Solo los ultimos 3 dias
		
		try {
			Vector<ItemColocacion> list = new Vector<ItemColocacion>();
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM AplicacionesInteractivas.dbo.ItemsColocaciones WHERE idPuesto = ? AND convert(date, fecha) BETWEEN (GETDATE() - 3) AND GETDATE() ORDER BY fecha DESC");
			statement.setInt(1, idPuesto);
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idPublicacion");
				int ejemplares = result.getInt("totalEjemplaresEntregados");
				int devoluciones = result.getInt("totalEjemplaresDevueltos");
				int idPublicacion = result.getInt("idPublicacion");
				int idEdicion = result.getInt("idEdicion");
				Date date = result.getDate("fecha");
				
				ItemColocacion itemColocacion = new ItemColocacion(id, ejemplares, devoluciones);		
				list.add(itemColocacion);
			}
			
			return list;
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
		
	}
	
	public ItemColocacion getUltimaColocacion(int idPuesto, int idEdicion) {
		// devolver ultima colocacion de una edicion particular para un puesto particular
		
		return null;
	}
}
