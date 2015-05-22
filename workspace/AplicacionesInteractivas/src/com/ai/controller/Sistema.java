package com.ai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.ai.business.Colocacion;
import com.ai.business.TiposPautas;
import com.ai.db.PersistenciaColocaciones;
import com.ai.db.PersistenciaEdiciones;
import com.ai.db.PersistenciaPublicaciones;
import com.ai.db.PersistenciaPuestos;
import com.ai.models.Edicion;
import com.ai.models.ItemColocacion;
import com.ai.models.Pauta;
import com.ai.models.PautaXAgotado;
import com.ai.models.PautaXDefecto;
import com.ai.models.PautaXExcesoDevolucion;
import com.ai.models.PautaXZona;
import com.ai.models.Publicacion;
import com.ai.models.Puesto;
import com.ai.observer.Editor;

public class Sistema {
	private static Sistema _sistema;
	private Pauta pauta;
	
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
	
	public int getEjemplaresNecesarios() {
		return pauta.getEjemplaresNecesarios();
	}
	
	public ArrayList<ItemColocacion> getItemsColocacionByPauta(TiposPautas tipoPauta, int condicional, int cantidadEjemplares, int idPublicacion, int idEdicion) {
		
		if( tipoPauta == TiposPautas.PorDefecto ) {
			pauta = new PautaXDefecto();
		}
		else if( tipoPauta == TiposPautas.PorExceso ) {
			pauta = new PautaXExcesoDevolucion();
		}
		else if( tipoPauta == TiposPautas.PorZona ) {
			pauta = new PautaXZona( condicional );
			
		}
		else if( tipoPauta == TiposPautas.PorAgotado ) {
			pauta = new PautaXAgotado(condicional);
		}
		
		if( pauta != null ) {
			
			//TODO Como todas las pautas estan con arraylist y nosotros manejamos vector entionces aca es donde lo casteo
			ArrayList<Puesto> puestos = new ArrayList<Puesto>(Sistema.getInstance().getPuestos());
			
			ArrayList<ItemColocacion> colocaciones = pauta.procesarColocaciones(puestos, cantidadEjemplares, idPublicacion, idEdicion);
			
			return colocaciones;
			
		}
		
		return null;
	}
	
	public void crearColocacion(Colocacion colocacion, int cantidadEjemplares, int idPublicacion, int idEdicion, ArrayList<ItemColocacion> colocaciones, String nombrePauta) {
		//Chequeo si necesito mas ejemplares
		if( (cantidadEjemplares - Sistema.getInstance().getEjemplaresNecesarios()) < 0 ) {
			colocacion.notifyObservers(Math.abs(cantidadEjemplares - Sistema.getInstance().getEjemplaresNecesarios()));
		}
		
		colocacion.setIdEdicion(idEdicion);
		colocacion.setPauta(nombrePauta);
		colocacion.setFecha(new Date());
		
		for (ItemColocacion itemColocacion : colocaciones) {
			itemColocacion.setIdPublicacion(idPublicacion);
			itemColocacion.setIdEdicion(idEdicion);
			
			colocacion.addItemColocacion(itemColocacion);
		}
		
		Sistema.getInstance().guardarColocacion(colocacion);
		
		colocacion.removeObservers();
	}
	
	public void inicializarObservers(Colocacion colocacion, String nombreEditor) {
		Editor editor = new Editor(nombreEditor);
		
		colocacion.registerObserver(editor);
	}
}
