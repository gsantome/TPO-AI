package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import com.ai.models.Edicion;
import com.ai.models.Puesto;

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

	public void insert(Object obj, Object obj2)
	{
		try {
			Edicion edicion = (Edicion)obj;
			int codigoPublicacion = (int)obj2;
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();

			PreparedStatement statement = conn.prepareStatement("insert into AplicacionesInteractivas.dbo.Ediciones (idPublicacion, tituloTapa, precio, fechaSalida, cantidadEjemplares) values (?,?,?,?,?)");
			
			statement.setInt(1, codigoPublicacion);
			statement.setString(2, edicion.getTituloDeTapa());
			statement.setDouble(3, edicion.getPrecio());
			statement.setDate(4, new java.sql.Date(edicion.getFechaSalida().getTime()));
			statement.setInt(5, edicion.getCantidadEjemplares());
			
			statement.execute();
			
			PoolConnection.getPoolConnection().releaseConnection(conn);
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
	}

	@Override
	public void update(Object obj) {
		try {
			Edicion edicion = (Edicion)obj;
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("update AplicacionesInteractivas.dbo.Ediciones" +
												"set tituloTapa = ?" +
												"set precio = ?" +
												"set cantidadEjemplares = ?" +
												"set fechaSalida = ?" +
												"where idEdicion = ?");
			
			statement.setString(1, edicion.getTituloDeTapa());
			statement.setDouble(2, edicion.getPrecio());
			statement.setInt(3, edicion.getCantidadEjemplares());
			statement.setDate(4, new java.sql.Date(edicion.getFechaSalida().getTime()));
			statement.setInt(5, edicion.getCodigo());
			
			statement.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
	}

	@Override
	public void delete(Object obj) {
		try {
			int codigo = (int)obj;
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("delete from AplicacionesInteractivas.dbo.Ediciones" +
												"where idEdicion = ?");
			statement.setInt(1,codigo);
			
			statement.execute();
			PoolConnection.getPoolConnection().releaseConnection(conn);
		}
		catch(Exception ex)
		{
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		}
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
		try {
			Edicion edicion = null;
			
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Ediciones WHERE idEdicion = ?");
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int codigo = result.getInt("idEdicion");
				String tituloDeTapa = result.getString("tituloTapa");
				double precio = result.getDouble("precio");
				Date fechaSalida = result.getDate("fechaSalida");
				int cantidadEjemplares = result.getInt("cantidadEjemplares");
				
				edicion = new Edicion(codigo, tituloDeTapa, precio, fechaSalida, cantidadEjemplares);
			}
			
			return edicion;			
		}
		catch( Exception ex ) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
		
			return null;
		}
	}
	
	public Vector<Edicion> selectByPublicacion(int idPublicacion) {
		try {
			Vector<Edicion> list = new Vector<Edicion>();
			
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
