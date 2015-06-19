package com.ai.ui.models;

import java.util.ArrayList;

public class PuestoView {
	private int codigo;
	private String direccion;
	private String nombre;
	private String barrio;
	private int idZona;
	private String tipoPublicacion;
	
	public PuestoView() {
		
	}
	
	public PuestoView(int codigo, String nombre, String direccion, String barrio, int idZona, String tipoPublicacion) {
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
	
	public ArrayList<ItemColocacionView> getUltimasColocaciones(int cantidad){
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getBarrio() {
		return this.barrio;
	}
	
	public void setBarrio(String barrio){
		this.barrio = barrio;
	}

	
}
