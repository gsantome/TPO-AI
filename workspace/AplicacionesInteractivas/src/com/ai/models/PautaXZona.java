package com.ai.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PautaXZona extends Pauta{
	private int codigoZona;

	public PautaXZona(){
		super();
		this.codigoZona = -1;
	}
	
	public PautaXZona(int codigoZona){
		super();
		this.codigoZona = codigoZona;
	}
	

	public ArrayList<ItemColocacion> procesarColocaciones(
			ArrayList<Puesto> puestos, int totalEjemplares, int idPublicacion, int idEdicion) {
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
//			public ItemColocacion(int codigoPuesto, int totalEjemplares, int devoluciones, int idEdicion, int idPublicacion, Date fecha) {
				
			item = new ItemColocacion(currentPuesto.getCodigo(),0,0,idEdicion,idPublicacion, new Date());
			item.setCantidadDevoluciones(0);
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
		int particionFuera = 0;
		if( colocacionZona.size() - contadorZona > 0 ) {
			particionFuera = (totalEjemplares - particionZona*contadorZona)/ (colocacionZona.size()-contadorZona);
		}

		int particion = 0;
		while (iColocaciones.hasNext()){
			item = iColocaciones.next();
			if (contadorZona >0){
				item.setCantidadEjemplares(particionZona);
				particion = this.getEjemplaresNecesarios()+particionZona;
				this.setEjemplaresNecesarios(particion);
			}else{
				item.setCantidadEjemplares(particionFuera);
				particion = this.getEjemplaresNecesarios()+particionFuera;
				this.setEjemplaresNecesarios(particion);
			}
			contadorZona--;
		}
		return colocacionZona;
	}
	

}
