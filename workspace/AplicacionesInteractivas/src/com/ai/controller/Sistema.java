package com.ai.controller;

import java.util.Vector;

import com.ai.models.Edicion;
import com.ai.models.Publicacion;

public class Sistema {
	private static Sistema _sistema;
	
	public Sistema() {
		
	}
	
	public static Sistema getInstance()	{
		if( _sistema == null ) {
			_sistema = new Sistema();
		}
		
		return _sistema;
	}
	
	public Vector<Publicacion> getPublicaciones() {
		
		Vector<Publicacion> publicaciones = new Vector<Publicacion>();
		
		return publicaciones;

	}
	
	public Vector<Edicion> getEdiciones() {
		
		Vector<Edicion> ediciones = new Vector<Edicion>();
		
		return ediciones;
		
	}
}
