package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.ai.models.ItemColocacion;

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
	
	public Vector<ItemColocacion> getItemsColocacionByColocacion(int idColocacion) {
		try {
			Vector<ItemColocacion> list = new Vector<ItemColocacion>();
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM AplicacionesInteractivas.dbo.ItemsColocaciones WHERE idColocacion = ? ORDER BY fecha DESC");
			statement.setInt(1, idColocacion);
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idPublicacion");
				int ejemplares = result.getInt("totalEjemplaresEntregados");
				int devoluciones = result.getInt("totalEjemplaresDevueltos");
				int idEdicion = result.getInt("idEdicion");
				int idPublicacion = result.getInt("idPublicacion");
				Date date = result.getDate("fecha");
				
				ItemColocacion itemColocacion = new ItemColocacion(id, ejemplares, devoluciones, idEdicion, idPublicacion, date);		
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

	public ArrayList<ItemColocacion> getLastThreeDayItems(int idPuesto, int idPublicacion) {
		// debe devolver la lista del mas nuevo al mas viejo. Solo los ultimos 3 dias
		
		try {
			ArrayList<ItemColocacion> list = new ArrayList<ItemColocacion>();
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM AplicacionesInteractivas.dbo.ItemsColocaciones WHERE idPuesto = ? AND idPublicacion = ? AND convert(date, fecha) BETWEEN (GETDATE() - 3) AND GETDATE() ORDER BY fecha DESC");
			statement.setInt(1, idPuesto);
			statement.setInt(2, idPublicacion);
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idPublicacion");
				int ejemplares = result.getInt("totalEjemplaresEntregados");
				int devoluciones = result.getInt("totalEjemplaresDevueltos");
				int idEdicion = result.getInt("idEdicion");
				Date date = result.getDate("fecha");
				
				ItemColocacion itemColocacion = new ItemColocacion(id, ejemplares, devoluciones, idEdicion, idPublicacion, date);		
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
		ItemColocacion last = new ItemColocacion();
		try {
			
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM AplicacionesInteractivas.dbo.ItemsColocaciones WHERE idPuesto = ? AND idEdicion= ? ORDER BY fecha DESC");
			statement.setInt(1, idPuesto);
			statement.setInt(2, idEdicion);
			ResultSet result = statement.executeQuery();
			if (result.next() ){
				int id = result.getInt("idPublicacion");
				int ejemplares = result.getInt("totalEjemplaresEntregados");
				int devoluciones = result.getInt("totalEjemplaresDevueltos");
				int publicacion = result.getInt("idPublicacion");
				Date date = result.getDate("fecha");
				
				last = new ItemColocacion(id, ejemplares, devoluciones, idEdicion, publicacion, date);		

			}
			
			
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
		return last;
	}

	public void insertAll(int idColocacion, Vector<ItemColocacion> itemsColocaciones) {
		
		try {
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			
			for (ItemColocacion itemColocacion : itemsColocaciones) {
				
				PreparedStatement statement = conn.prepareStatement("insert into AplicacionesInteractivas.dbo.ItemsColocaciones (idPuesto, totalEjemplaresEntregados, totalEjemplaresDevueltos, idPublicacion, idEdicion, fecha, idColocacion) values (?, ?, ?, ?, ?, ?, ?)");
				statement.setInt(1, itemColocacion.getCodigoPuesto());
				statement.setInt(2, itemColocacion.getCantidadEjemplares());
				statement.setInt(3, itemColocacion.getCantidadDevoluciones());
				statement.setInt(4, itemColocacion.getIdPublicacion());
				statement.setInt(5, itemColocacion.getIdEdicion());
				statement.setDate(6, new java.sql.Date(itemColocacion.getFechaColocacion().getTime()));
				statement.setInt(7, idColocacion);
				
				statement.executeUpdate();
				
			}
		}
		catch( Exception ex ) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
		
	}

	public ArrayList<ItemColocacion> getUltimasColocaciones(int idPuesto,
			int idPublicacion, int cantUltimasEdiciones) {
		ArrayList<ItemColocacion> list = new ArrayList<ItemColocacion>();
		try {
			
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM AplicacionesInteractivas.dbo.ItemsColocaciones WHERE idPuesto = ? AND idPublicacion= ? AND convert(date, fecha) BETWEEN (GETDATE() - ?) AND GETDATE() ORDER BY fecha DESC");
			statement.setInt(1, idPuesto);
			statement.setInt(2, idPublicacion);
			statement.setInt(3, cantUltimasEdiciones);
			ResultSet result = statement.executeQuery();
			
			while( result.next() && cantUltimasEdiciones>0) {
				int id = result.getInt("idPublicacion");
				int ejemplares = result.getInt("totalEjemplaresEntregados");
				int devoluciones = result.getInt("totalEjemplaresDevueltos");
				int publicacion = result.getInt("idPublicacion");
				int idEdicion = result.getInt("idEdicion");
				Date date = result.getDate("fecha");
				
				ItemColocacion itemColocacion = new ItemColocacion(id, ejemplares, devoluciones, idEdicion, publicacion, date);		
				list.add(itemColocacion);
				cantUltimasEdiciones--;
			}
			
			
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
		return list;
	}
}
