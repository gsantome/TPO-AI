/*Pauta por exceso de devolucion. Para las N ultimas ediciones ingresadas por el usuario, se verifica la carga con la cual
 *el vendedor agoto, y de a cuerdo a la carga, y tabla de agotadoes, se devuelve la colocacion.
 *
 *Tabla de agotados:
 *
 *	    Carga	|	Se colocara con
 *	--------------------------------
 *		1		|		3
 *		2 a 5	|		5
 *		6 a 10	|		10
 * 				|
 * 
 * Se entiende que el vendedor carga n y devueve 0.
 * 
 * @params 	int CantidadEdiciones		es la cantidad de ediciones para atras en las cuales se desea verificar que el vendedor
 * 									haya agotado
 * @param	ArrayList Puesto		Son todos los puestos dados de alta, en los cuales verificaremos si agotaron durante las
 * 									CantidadEdiciones
 * 
 * @return	Colocacion				Devuevlo la colocacion para ese puesto.
 * */

package com.ai.models;
import java.util.ArrayList;

import com.ai.business.Colocacion;

public class PautaXExcesoDevolucion extends Pauta {
	
	public PautaXExcesoDevolucion() {
		super();
	}
	
	public ArrayList<Colocacion> getColocacion(int CantidadEdiciones, ArrayList<Puesto> puestos){
		ArrayList <Colocacion> arrayColocacionesGeneradas = new ArrayList<Colocacion>();
		
		
		for (Puesto puesto : puestos) {
			ArrayList<ItemColocacion> ItemColocacion = puesto.getUltimasColocaciones(CantidadEdiciones);
			
			boolean cumpleAgotados = true;
			
			for (int i = 0; i < CantidadEdiciones; i++){			//Recorr las N ediciones anteriores y chequeo que haya agotado
				ItemColocacion item = ItemColocacion.get(i);
				if (item.getCantidadDevoluciones() != 0){
					cumpleAgotados = false;
				}	
			}
			
			if (cumpleAgotados){
				int CantidadNuevaColocacion = 0;
				
				switch (CantidadEdiciones){
											case 1:
													CantidadNuevaColocacion = 3;
													break;
											case 2:
											case 3:
											case 4:
											case 5:
												CantidadNuevaColocacion = 5;
												    break;
				
											case 6:
											case 7:
											case 8:
											case 9:
											case 10:
												CantidadNuevaColocacion = 10;
													break;
				}
				
				
				Colocacion nuevaColocacion = new Colocacion();
				nuevaColocacion.setCantEjemplares(CantidadNuevaColocacion);
				arrayColocacionesGeneradas.add(nuevaColocacion);
			}
		}
		return arrayColocacionesGeneradas;
	}
}
