package com.ai.models;

import java.util.ArrayList;

import com.ai.business.Colocacion;
import com.ai.db.PersistenciaPautas;

/*Ejecuta sobre la colocacion por defecto calculada
 * Tabla de exceso en base de datos
 *  porcentaje excedido/porcentaje de descuento
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
			ItemColocacion colocacionAnterior = ReporteColocacion.getInstance().getUltimaColocacion(idpuesto, colocacionActual.getIdEdicion());
			int diferencia = colocacionAnterior.getCantidadDevoluciones()-colocacionAnterior.getCantidadEjemplares();
			if (diferencia>0) {		
				int descuento = PersistenciaPautas.selectTableExceso(diferencia); //pendiente de implementacion
				itemColActual.setCantidadEjemplares(itemColActual.getCantidadEjemplares()-descuento);
				
				colocacionActual.setCantEjemplares(colocacionActual.getCantEjemplares() - descuento);
			}
		}
	}
}
