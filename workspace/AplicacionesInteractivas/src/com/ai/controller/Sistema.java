package com.ai.controller;

import java.util.Vector;

import com.ai.business.Colocacion;
import com.ai.db.PersistenciaColocaciones;
import com.ai.db.PersistenciaEdiciones;
import com.ai.db.PersistenciaPublicaciones;
import com.ai.db.PersistenciaPuestos;
import com.ai.models.Edicion;
import com.ai.models.Publicacion;
import com.ai.models.Puesto;

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
	
	public void guardarColocacion(Colocacion colocacion) {
		
		PersistenciaColocaciones.getInstance().insert(colocacion);
		
	}
	
	public Puesto getPuesto(int idPuesto) {
		return (Puesto)PersistenciaPuestos.getInstance().selectById(idPuesto);
	}
	
	public Vector<Puesto> getPuestos() {
		
		PersistenciaPuestos persistencia = PersistenciaPuestos.getInstance();
		
		Vector<Object> vector = persistencia.selectAll();
		Vector typelessVector = vector;
		
		return (Vector<Puesto>)typelessVector;
		
	}
	
	public Vector<Publicacion> getPublicaciones() {
		
		PersistenciaPublicaciones persistencia = PersistenciaPublicaciones.getInstance();
		
		Vector<Object> vector = persistencia.selectAll();
		Vector typelessVector = vector;
		
		return (Vector<Publicacion>)typelessVector;
		
	}
	
	public Vector<Edicion> getEdiciones(int idPublicacion){
		
		PersistenciaEdiciones persistencia = PersistenciaEdiciones.getInstance();
		
		Vector<Edicion> ediciones = persistencia.selectByPublicacion(idPublicacion);
		
		return ediciones;
	}
	
	public Vector<Edicion> getEdiciones() {
		PersistenciaEdiciones persistencia = PersistenciaEdiciones.getInstance();
		Vector<Object> vector = persistencia.selectAll();
		Vector typelessVector = vector;
		
		return (Vector<Edicion>)typelessVector;
	}
	
	public void altaEdicion(Edicion edicion, int codigoPublicacion)
	{
		
		PersistenciaEdiciones.getInstance().insert(edicion, codigoPublicacion);
	}
	
	public void bajaEdicion(int codigo)
	{
		PersistenciaEdiciones.getInstance().delete(codigo);
	}
	
	public void modificacionEdicion(Edicion edicion)
	{
		PersistenciaEdiciones.getInstance().update(edicion);
	}

	public Vector<Edicion> getEdicionesByPublicacion(int codigo) {
		return PersistenciaEdiciones.getInstance().selectByPublicacion(codigo);
	}
}
