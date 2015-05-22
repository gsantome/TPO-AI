package com.ai.models;

import java.util.ArrayList;

public class Puesto {
	private int codigo;
	private String direccion;
	private String nombre;
	private String barrio;
	private int idZona;
	private String tipoPublicacion;
	
	public Puesto() {
		
	}
	
	public Puesto(int codigo, String nombre, String direccion, String barrio, int idZona, String tipoPublicacion) {
		this.codigo = codigo;
		this.direccion = direccion;
		this.nombre = nombre;
		this.barrio = barrio;
		this.idZona = idZona;
		this.tipoPublicacion = tipoPublicacion;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	public String getTipoPublicacion() {
		return tipoPublicacion;
	}
	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}
	
	public ArrayList<ItemColocacion> getUltimasColocaciones(int cantidad){
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
