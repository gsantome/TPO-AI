package com.ai.models;

import java.util.ArrayList;
import java.util.Iterator;

public class PautaXZona implements IPauta{
	private int codigoZona;

	public ArrayList<ItemColocacion> procesarColocaciones(
			ArrayList<Puesto> puestos, int totalEjemplares) {
		// TODO Auto-generated method stub
		/*
		 * Para todos los puestos que pertenezcan a la zona 
		 * determinada en la pauta se les asignar el 70% del total de ejmplares.
		 * para aquellos puestos que sean de otra zona se dispondra del 30%
		 */
		ItemColocacion item = null;
		ArrayList<ItemColocacion> colocacionZona = new ArrayList<ItemColocacion>();
		int contadorZona = 0;
		Iterator<Puesto> iterator = puestos.iterator();
		Puesto currentPuesto;
		while (iterator.hasNext()){
			currentPuesto = (Puesto) iterator.next();
			item = new ItemColocacion();
			item.setCantidadDevoluciones(0);
			item.setCodigoPuesto(currentPuesto.getCodigo());
			if ( codigoZona == currentPuesto.getIdZona() ){
				contadorZona++;
				//los correspondientes a la zona se agregan al principio de la lista
				colocacionZona.add(0, item);
			} else {
				//los puestos que no corresponden a la zona se agregan al final
				colocacionZona.add(item);
			}
		}

		Iterator<ItemColocacion> iColocaciones = colocacionZona.iterator();
		/*
		 * Ediciones por puesto en la zona
		 */
		int particionZona = (int) Math.round(totalEjemplares*0.7/contadorZona);
		int particionFuera = (totalEjemplares - particionZona*contadorZona)/ (colocacionZona.size()-contadorZona);
		
		while (iColocaciones.hasNext()){
			item = iColocaciones.next();
			if (contadorZona >0){
				item.setCantidadEjemplares(particionZona);
			}else{
				item.setCantidadEjemplares(particionFuera);
			}
			contadorZona--;
		}
		return colocacionZona;
	}
	

}
