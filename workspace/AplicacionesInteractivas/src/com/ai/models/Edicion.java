package com.ai.models;

import java.util.Date;

public class Edicion {
	private int codigo;
	private String tituloDeTapa;
	private double precio;
	private Date fechaSalida;
	private int cantidadEjemplares;
	
	public Edicion() {
	}
	
	public Edicion(int codigo, String tituloDeTapa, double precio, Date fechaSalida, int cantidadEjemplares) {
		this.codigo = codigo;
		this.tituloDeTapa = tituloDeTapa;
		this.precio = precio;
		this.fechaSalida = fechaSalida;
		this.cantidadEjemplares = cantidadEjemplares;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTituloDeTapa() {
		return tituloDeTapa;
	}

	public void setTituloDeTapa(String tituloDeTapa) {
		this.tituloDeTapa = tituloDeTapa;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getCantidadEjemplares() {
		return cantidadEjemplares;
	}

	public void setCantidadEjemplares(int cantidadEjemplares) {
		this.cantidadEjemplares = cantidadEjemplares;
	}
	
	@Override
	public String toString() {
		return this.codigo + ": " + this.tituloDeTapa;
	}
}
