package com.ai.models;

import java.util.Date;

public class ItemColocacion {
	private int codigoPuesto;
	private int idEdicion;
	private int idPublicacion;
	private int cantidadEjemplares;
	private int cantidadDevoluciones;
	private Date fechaColocacion;
	
	
	public ItemColocacion() {
		this.codigoPuesto = 0;
		this.cantidadEjemplares = 0;
		this.cantidadDevoluciones = 0;
		idEdicion = 0;
		idPublicacion = 0;
		fechaColocacion = new Date();
	}
	
	public ItemColocacion(int codigoPuesto, int totalEjemplares, int devoluciones, int idEdicion, int idPublicacion, Date fecha) {
		this.codigoPuesto = codigoPuesto;
		this.cantidadEjemplares = totalEjemplares;
		this.cantidadDevoluciones = devoluciones;
		this.idEdicion = idEdicion;
		this.idPublicacion = idPublicacion;
		this.fechaColocacion = fecha;
	}

	public int getCantidadEjemplares() {
		return cantidadEjemplares;
	}
	public void setCantidadEjemplares(int cantidadEjemplares) {
		this.cantidadEjemplares = cantidadEjemplares;
	}
	public int getCantidadDevoluciones() {
		return cantidadDevoluciones;
	}
	public void setCantidadDevoluciones(int cantidadDevoluciones) {
		this.cantidadDevoluciones = cantidadDevoluciones;
	}
	public int getCodigoPuesto() {
		return codigoPuesto;
	}
	public void setCodigoPuesto(int codigoPuesto) {
		this.codigoPuesto = codigoPuesto;
	}

	public int getIdEdicion() {
		return idEdicion;
	}

	public void setIdEdicion(int idEdicion) {
		this.idEdicion = idEdicion;
	}

	public int getIdPublicacion() {
		return idPublicacion;
	}

	public void setIdPublicacion(int idPublicacion) {
		this.idPublicacion = idPublicacion;
	}

	public Date getFechaColocacion() {
		return fechaColocacion;
	}

	public void setFechaColocacion(Date fechaColocacion) {
		this.fechaColocacion = fechaColocacion;
	}
}
