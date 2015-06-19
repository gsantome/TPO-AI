package com.ai.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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
import com.ai.ui.models.EdicionView;
import com.ai.ui.models.ItemColocacionView;
import com.ai.ui.models.PublicacionView;
import com.ai.ui.models.PuestoView;

public class Sistema {
	private static Sistema _sistema;
	private Pauta pauta;
	private Vector<Edicion> ediciones;
	private Vector<Colocacion> colocaciones;
	private Vector<Puesto> puestos;
	private Vector<Publicacion> publicaciones;
	
	public Sistema() {
		Vector<?> typelessVector = PersistenciaEdiciones.getInstance().selectAll();
		this.ediciones = (Vector<Edicion>)typelessVector;
		
		typelessVector = PersistenciaColocaciones.getInstance().selectAll();
		this.colocaciones = (Vector<Colocacion>)typelessVector;
		
		typelessVector = PersistenciaPuestos.getInstance().selectAll();
		this.puestos = (Vector<Puesto>)typelessVector;
		
		typelessVector = PersistenciaPublicaciones.getInstance().selectAll();
		this.publicaciones = (Vector<Publicacion>)typelessVector;
	}
	
	public static Sistema getInstance()	{
		if( _sistema == null ) {
			_sistema = new Sistema();
		}
		
		return _sistema;
	}
	
	/*public void guardarColocacion(Colocacion colocacion) {
		
		PersistenciaColocaciones.getInstance().insert(colocacion);
		
	}*/
	
	public PuestoView getPuesto(int idPuesto) {
		Puesto puesto = null;
		
		for(Puesto p : this.puestos)
			if(p.getCodigo() == idPuesto)
				puesto = p;
		
		if(puesto == null)
			puesto = (Puesto)PersistenciaPuestos.getInstance().selectById(idPuesto);
		
		return new PuestoView(
				puesto.getCodigo(),
				puesto.getDireccion(),
				puesto.getNombre(),
				puesto.getBarrio(),
				puesto.getIdZona(),
				puesto.getTipoPublicacion());
	}
	
	public Vector<Puesto> getPuestos() {
		
		/*PersistenciaPuestos persistencia = PersistenciaPuestos.getInstance();
		
		Vector<Object> vector = persistencia.selectAll();
		Vector typelessVector = vector;
		
		return (Vector<Puesto>)typelessVector;*/
		return this.puestos;
	}
	
	public Vector<PublicacionView> getPublicaciones() {
		
		/*PersistenciaPublicaciones persistencia = PersistenciaPublicaciones.getInstance();
		
		Vector<Object> vector = persistencia.selectAll();
		Vector typelessVector = vector;
		
		return (Vector<Publicacion>)typelessVector;*/
		
		Vector<PublicacionView> retorno = new Vector<PublicacionView>();
		for (Publicacion publicacion : this.publicaciones)
			retorno.add(new PublicacionView(
					publicacion.getCodigo(),
					publicacion.getTitulo(),
					publicacion.getTipoPublicacion(),
					publicacion.getEditor(),
					publicacion.getTema(),
					publicacion.getSubtema(),
					publicacion.getSePublico(),
					publicacion.getPeriodicidad(),
					publicacion.getIdioma(),
					publicacion.getPaisOrigen()));
		
		return retorno;
	}
	
	public Vector<EdicionView> getEdiciones(int idPublicacion) {
		Vector<Edicion> edicionesPub = PersistenciaEdiciones.getInstance().selectByPublicacion(idPublicacion);
		Vector<EdicionView> retorno = new Vector<EdicionView>();
		
		for (Edicion edicion : edicionesPub)
			retorno.add(new EdicionView(
					edicion.getCodigo(),
					edicion.getTituloDeTapa(),
					edicion.getPrecio(),
					edicion.getFechaSalida(),
					edicion.getCantidadEjemplares()));
			
		return retorno;
	}
	
	public Vector<EdicionView> getEdiciones() {
		/*PersistenciaEdiciones persistencia = PersistenciaEdiciones.getInstance();
		Vector<Object> vector = persistencia.selectAll();
		Vector typelessVector = vector;
		
		return (Vector<Edicion>)typelessVector;*/
		Vector<EdicionView> retorno = new Vector<EdicionView>();
		for (Edicion edicion : this.ediciones)
			retorno.add(new EdicionView(
						edicion.getCodigo(),
						edicion.getTituloDeTapa(),
						edicion.getPrecio(),
						edicion.getFechaSalida(),
						edicion.getCantidadEjemplares()));
		
		return retorno;		
	}
	
	
	public void altaEdicion(EdicionView edicionView, int codigoPublicacion)
	{
		Edicion edicion = new Edicion(
				edicionView.getCodigo(),
				edicionView.getTituloDeTapa(),
				edicionView.getPrecio(),
				edicionView.getFechaSalida(),
				edicionView.getCantidadEjemplares());
		
		PersistenciaEdiciones.getInstance().insert(edicion, codigoPublicacion);
		this.ediciones.addElement(edicion);
	}
	
	public void bajaEdicion(int codigo)
	{
		PersistenciaEdiciones.getInstance().delete(codigo);
	}
	
	public void modificacionEdicion(EdicionView edicionView)
	{
		PersistenciaEdiciones.getInstance().update(
				new Edicion(edicionView.getCodigo(),
						edicionView.getTituloDeTapa(),
						edicionView.getPrecio(),
						edicionView.getFechaSalida(),
						edicionView.getCantidadEjemplares()));
	}

	/*public Vector<Edicion> getEdicionesByPublicacion(int codigo) {
		return PersistenciaEdiciones.getInstance().selectByPublicacion(codigo);
	}*/
	
	public int getEjemplaresNecesarios() {
		return pauta.getEjemplaresNecesarios();
	}
	
	public ArrayList<ItemColocacionView> getItemsColocacionByPauta(TiposPautas tipoPauta, int condicional, int cantidadEjemplares, int idPublicacion, int idEdicion) {
		
		if( tipoPauta == TiposPautas.PorDefecto ) {
			pauta = new PautaXDefecto();
		}
		else if( tipoPauta == TiposPautas.PorExceso ) {
			pauta = new PautaXExcesoDevolucion();
		}
		else if( tipoPauta == TiposPautas.PorZona ) {
			pauta = new PautaXZona(condicional);
		}
		else if( tipoPauta == TiposPautas.PorAgotado ) {
			pauta = new PautaXAgotado(condicional);
		}
		
		if( pauta != null ) {			
			//TODO Como todas las pautas estan con arraylist y nosotros manejamos vector entionces aca es donde lo casteo
			ArrayList<Puesto> puestos = new ArrayList<Puesto>(this.puestos);
			
			ArrayList<ItemColocacion> colocaciones = pauta.procesarColocaciones(puestos, cantidadEjemplares, idPublicacion, idEdicion);
			ArrayList<ItemColocacionView> retorno = new ArrayList<ItemColocacionView>();
			
			for(ItemColocacion itemColocacion : colocaciones)
				retorno.add(new ItemColocacionView(
						itemColocacion.getCodigoPuesto(),
						itemColocacion.getCantidadEjemplares(),
						itemColocacion.getCantidadDevoluciones(),
						itemColocacion.getIdEdicion(),
						itemColocacion.getIdPublicacion(),
						itemColocacion.getFechaColocacion()));
				
			return retorno;	
		}
		
		return null;
	}
	
	public void crearColocacion(Colocacion colocacion, int cantidadEjemplares, int idPublicacion, int idEdicion, ArrayList<ItemColocacionView> colocaciones, String nombrePauta) {
		//Chequeo si necesito mas ejemplares
		if( (cantidadEjemplares - this.getEjemplaresNecesarios()) < 0 ) {
			colocacion.notifyObservers(Math.abs(cantidadEjemplares - Sistema.getInstance().getEjemplaresNecesarios()));
		}
		
		colocacion.setIdEdicion(idEdicion);
		colocacion.setPauta(nombrePauta);
		colocacion.setFecha(new Date());
		
		for (ItemColocacionView itemColocacionView : colocaciones) {
			itemColocacionView.setIdPublicacion(idPublicacion);
			itemColocacionView.setIdEdicion(idEdicion);
			
			colocacion.addItemColocacion(new ItemColocacion(
					itemColocacionView.getCodigoPuesto(),
					itemColocacionView.getCantidadEjemplares(),
					itemColocacionView.getCantidadDevoluciones(),
					itemColocacionView.getIdEdicion(),
					itemColocacionView.getIdPublicacion(),
					itemColocacionView.getFechaColocacion()));
		}
		
		//Sistema.getInstance().guardarColocacion(colocacion);
		PersistenciaColocaciones.getInstance().insert(colocacion);
		this.colocaciones.addElement(colocacion);
		colocacion.removeObservers();
	}
	
	public void inicializarObservers(Colocacion colocacion, String nombreEditor) {
		Editor editor = new Editor(nombreEditor);
		
		colocacion.registerObserver(editor);
	}
}
