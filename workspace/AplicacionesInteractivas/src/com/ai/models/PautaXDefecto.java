package com.ai.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
		if (historial.size() == 0)
			ejemplares = Math.round(totalEntregados - devoluciones);
		else
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
			historial = PersistenciaItemsColocacion.getInstance().getLastThreeDayItems(currentPuesto.getCodigo(), idPublicacion);
			itemColocacion = new ItemColocacion (currentPuesto.getCodigo(), calcularEjemplares(historial), 0,idEdicion, idPublicacion, new Date());
			colocacionZona.add(itemColocacion);
			ejemplaresNecesarios += itemColocacion.getCantidadEjemplares();
			this.setEjemplaresNecesarios(ejemplaresNecesarios);
		}
		
		return colocacionZona;
	}

	
}
