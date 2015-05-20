package com.ai.models;

import java.util.ArrayList;
import java.util.Iterator;

public class PautaXDefecto implements IPauta {

	@Override
	public ArrayList<ItemColocacion> procesarColocaciones(
			ArrayList<Puesto> puestos, int totalEjemplares) {
		int ejemplaresNecesarios = 0;
		ArrayList<ItemColocacion> colocacionZona = new ArrayList<ItemColocacion>();
		Iterator<Puesto> iterator = puestos.iterator();
		Puesto currentPuesto;
		ArrayList<ItemColocacion> historial = null;
		ItemColocacion colocacion;
		while (iterator.hasNext()){
			
			currentPuesto = iterator.next();
			historial = ReporteColocacion.getInstance().getLastThreeDayItems(currentPuesto.getCodigo());
			colocacion = new ItemColocacion(currentPuesto.getCodigo(), calcularEjemplares(historial), 0);
			colocacionZona.add(colocacion);
			ejemplaresNecesarios += colocacion.getCantidadEjemplares();
		}
		
		return colocacionZona;
	}
	
	public int calcularEjemplares(ArrayList<ItemColocacion> historial){
		int ejemplares = 0;
		int devoluciones = 0;
		int totalEntregados = 0;
		for (ItemColocacion item : historial){
			totalEntregados += item.getCantidadEjemplares();
			devoluciones += item.getCantidadDevoluciones();
		}
		ejemplares = Math.round((totalEntregados - devoluciones) / historial.size());
		return ejemplares;
	}

	
}
