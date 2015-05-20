package com.ai.models;

import java.util.ArrayList;

public interface IPauta {
	
	public ArrayList<ItemColocacion> procesarColocaciones(ArrayList<Puesto> puestos, int totalEjemplares);
	
}
