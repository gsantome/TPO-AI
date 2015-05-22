package com.ai.models;

import java.util.ArrayList;

public abstract class Pauta {
	private int ejemplaresNecesarios;
	
	public Pauta(){
		this.ejemplaresNecesarios = 0;
	}
	/*
	 * Este metodo se debe sobreescribir en sus clases herederas
	 */
	public ArrayList<ItemColocacion> procesarColocaciones(
			ArrayList<Puesto> puestos, int totalEjemplares, int idPublicacion, int idEdicion) {
		
		ArrayList<ItemColocacion> colocacion = new ArrayList<ItemColocacion>();
		
		return colocacion;
	}
	
	public int getEjemplaresNecesarios() {
		return ejemplaresNecesarios;
	}

	public void setEjemplaresNecesarios(int ejemplaresNecesarios) {
		this.ejemplaresNecesarios = ejemplaresNecesarios;
	}

}
