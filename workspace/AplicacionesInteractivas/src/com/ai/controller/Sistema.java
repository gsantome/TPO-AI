package com.ai.controller;

import java.util.Calendar;
import java.util.Date;
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
		Calendar calendar = Calendar.getInstance();         
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		
		Vector<Publicacion> publicaciones = new Vector<Publicacion>();
		
		Publicacion publicacion1 = new Publicacion(1, "La Nacion", "Domingo", "Pepe", "Politica", "Elecciones", false, "Semanal", "espanol", "Argentina");
		Publicacion publicacion2 = new Publicacion(2, "La Nacion", "Sabado", "Gonzalo", "Economia", "Inflacion", false, "Semanal", "espanol", "Argentina");
		
		publicacion1.addEdicion(new Edicion(1, "Cristina y sus negocios", 10.0, new Date(), 100));
		publicacion2.addEdicion(new Edicion(1, "La inflacion", 2.0, calendar.getTime(), 150));
		
		publicaciones.addElement(publicacion1);
		publicaciones.addElement(publicacion2);
		
		return publicaciones;

	}
}
