package com.ai.models;

public class ItemColocacion {
	private int codigoPuesto;
	private int cantidadEjemplares;
	private int cantidadDevoluciones;
	
	public ItemColocacion() {
		this.codigoPuesto = 0;
		this.cantidadEjemplares = 0;
		this.cantidadDevoluciones = 0;
	}
	
	public ItemColocacion(int codigo, int ejemplares, int devoluciones) {
		this.codigoPuesto = codigo;
		this.cantidadEjemplares = ejemplares;
		this.cantidadDevoluciones = devoluciones;
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
	

}
