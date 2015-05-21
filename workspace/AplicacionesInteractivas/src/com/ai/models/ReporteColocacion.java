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

}
