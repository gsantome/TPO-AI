package com.ai.ui.models;

import java.util.Date;


public class ColocacionView {
	private int codigo;
	private String pauta;
	private int idEdicion;
	private Date fecha;
	
	public ColocacionView( int codigo, int idEdicion, String pauta, Date fecha ) {
		this.codigo = codigo;
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
	
	@Override
	public String toString() {
		return this.codigo + ": " + this.fecha;
	}
	
}

