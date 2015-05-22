package com.ai.business;

import java.util.Date;
import java.util.Vector;

import com.ai.models.ItemColocacion;

public class Colocacion {
	private int codigo;
	private String pauta;
	private int idEdicion;
	private Date fecha;
	private Vector<ItemColocacion> itemsColocaciones;
	
	public Colocacion() {
		this.itemsColocaciones = new Vector<ItemColocacion>();
	}
	
	public Colocacion( int codigo, int idEdicion, String pauta, Date fecha ) {
		this.itemsColocaciones = new Vector<ItemColocacion>();
		
		this.codigo = codigo;
		this.idEdicion = idEdicion;
		this.pauta = pauta;
		this.fecha = fecha;
	}
	
	public Colocacion( int idEdicion, String pauta, Date fecha ) {
		this.itemsColocaciones = new Vector<ItemColocacion>();
		
		this.idEdicion = idEdicion;
		this.pauta = pauta;
		this.fecha = fecha;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public int getIdEdicion() {
		return idEdicion;
	}

	public void setIdEdicion(int idEdicion) {
		this.idEdicion = idEdicion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Vector<ItemColocacion> getItemsColocaciones() {
		return itemsColocaciones;
	}
	
	public void addItemColocacion( ItemColocacion itemColocacion ) {
		this.itemsColocaciones.add( itemColocacion );
	}
}
