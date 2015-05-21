package com.ai.models;

import java.util.ArrayList;

public class ReporteColocacion {
	private static ReporteColocacion reportador;
	
	public ReporteColocacion(){
	}
	
	public static ReporteColocacion getInstance(){
		if (reportador == null)
		{
			reportador = new ReporteColocacion();
		}
		return reportador;
	}
	
	public ArrayList<ItemColocacion> getLastThreeDayItems(int IdPuesto){
		// debe devolver la lista del mas nuevo al mas viejo. Solo los ultimos 3 dias
		return null;
	}
	public ItemColocacion getUltimaColocacion(int IdPuesto,int CodigoEdicion) {
		// devolver ultima colocacion de una edicion particular para un puesto particular
		return null;
	}

}
