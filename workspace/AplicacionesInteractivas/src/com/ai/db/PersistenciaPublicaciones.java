package com.ai.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import com.ai.models.Publicacion;

public class PersistenciaPublicaciones extends Persistencia {
	
	public static PersistenciaPublicaciones instance;
	
	private PersistenciaPublicaciones() {
		
	}
	
	public static PersistenciaPublicaciones getInstance() {
		
		if( instance == null ) {
			instance = new PersistenciaPublicaciones();
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
			PreparedStatement statement = conn.prepareStatement("select * from AplicacionesInteractivas.dbo.Publicaciones");
			
			ResultSet result = statement.executeQuery();
			
			while( result.next() ) {
				int id = result.getInt("idPublicacion");
				String tipoPublicacion = result.getString("tipoPublicacion");
				String editor = result.getString("editor");
				String tema = result.getString("tema");
				String subtema = result.getString("subtema");
				Boolean publico = result.getString("publico") == "t" ? true : false;
				String periodicidad = result.getString("periodicidad");
				String idioma = result.getString("idioma");
				String paisOrigen = result.getString("paisOrigen");
				String titulo = result.getString("titulo");
				
				Publicacion publicacion = new Publicacion(id, titulo, tipoPublicacion, editor, tema, subtema, publico, periodicidad, idioma, paisOrigen);		
				list.add(publicacion);
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
