package com.ai.models;

import java.util.ArrayList;

import com.ai.business.Colocacion;

/*
 * Tabla de exceso
 *  porcentaje excedido/porcentaje de descuento
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class PautaXExcesoDevolucion extends Pauta {

	public PautaXExcesoDevolucion() {
		super();
	}
	
	public void procesarPauta(Colocacion colocacionActual) {
		ArrayList<ItemColocacion> itemsColActual = colocacionActual.getItemsColocacion();
		int cantpuestos = itemsColActual.size();
		for (int i = 0; i < cantpuestos; i++) {
			ItemColocacion itemColActual = itemsColActual.get(i);
			int idpuesto = itemColActual.getCodigoPuesto();
			ItemColocacion colocacionAnterior = ReporteColocacion.getInstance().getUltimaColocacion(idpuesto, colocacionActual.g.getIdEdicion());
			int diferencia = colocacionAnterior.getCantidadDevoluciones()-colocacionAnterior.getCantidadEjemplares();
			if (diferencia>0) {		
				int descuento = PersistenciaPautas.getInstance().selectTableExceso(diferencia); //
				itemColActual.setCantidadEjemplares(itemColActual.getCantidadEjemplares()-descuento);
				colocacionActual.setCantEjemplares(colocacionActual.getCantEjemplares()-descuento);
			}
		}
	}
}
