package com.ai.business;

public enum TiposPautas {
	PorDefecto("Por defecto"), 
	PorExceso("Por exceso de devolucion"),
	PorZona("Por zona"),
	PorAgotado("Por agotado");
	
	private final String display;
	
	private TiposPautas(String s) {
		display = s;
	}
	
	@Override
	public String toString() {
		return display;
	}
}
