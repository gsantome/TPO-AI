package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import com.ai.models.Edicion;

public class PersistenciaEdiciones extends Persistencia {
	
	public static PersistenciaEdiciones instance;
	
	private PersistenciaEdiciones() {
		
	}
	
	public static PersistenciaEdiciones getInstance() {
		if( instance == null ) {
			instance = new PersistenciaEdiciones();
		}
		
		return instance;
	}
	
	@Override
	public void insert(Object obj) {
		
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
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Ediciones");
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idEdicion");
				String tituloDeTapa = result.getString("tituloTapa");
				double precio = result.getDouble("precio");
				Date fechaSalida = result.getTimestamp("fechaSalida");
				int cantidadEjemplares = result.getInt("cantidadEjemplares");
				
				Edicion edicion = new Edicion(id, tituloDeTapa, precio, fechaSalida, cantidadEjemplares);
				
				list.add(edicion);
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
	
	public Vector<Object> selectByPublicacion(int idPublicacion) {
		try {
			Vector<Object> list = new Vector<Object>();
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Ediciones where idPublicacion = ?");
			statement.setInt(1, idPublicacion);
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idEdicion");
				String tituloDeTapa = result.getString("tituloTapa");
				double precio = result.getDouble("precio");
				Date fechaSalida = result.getDate("fechaSalida");
				int cantidadEjemplares = result.getInt("cantidadEjemplares");
				
				Edicion edicion = new Edicion(id, tituloDeTapa, precio, fechaSalida, cantidadEjemplares);
				
				list.add(edicion);
			}
			
			return list;
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
	}

}
