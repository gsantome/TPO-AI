package com.ai.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import com.ai.db.PersistenciaItemsColocacion;

public class PautaXDefecto extends Pauta {


	
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

	@Override
	public ArrayList<ItemColocacion> procesarColocaciones(
			ArrayList<Puesto> puestos, int totalEjemplares, int idPublicacion,
			int idEdicion) {
		int ejemplaresNecesarios = 0;
		ArrayList<ItemColocacion> colocacionZona = new ArrayList<ItemColocacion>();
		Iterator<Puesto> iterator = puestos.iterator();
		Puesto currentPuesto;
		ArrayList<ItemColocacion> historial = null;
		ItemColocacion itemColocacion;
		while (iterator.hasNext()){
			
			currentPuesto = iterator.next();
			Vector<ItemColocacion> vectorHistorial = PersistenciaItemsColocacion.getInstance().getLastThreeDayItems(currentPuesto.getCodigo());
			historial = new ArrayList<ItemColocacion>(vectorHistorial);
			itemColocacion = new ItemColocacion (currentPuesto.getCodigo(), calcularEjemplares(historial), 0,idEdicion, idPublicacion, new Date());
			colocacionZona.add(itemColocacion);
			ejemplaresNecesarios += itemColocacion.getCantidadEjemplares();
			this.setEjemplaresNecesarios(ejemplaresNecesarios);
		}
		
		return colocacionZona;
	}

	
}
