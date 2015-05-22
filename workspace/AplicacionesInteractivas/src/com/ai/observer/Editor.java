package com.ai.observer;

public class Editor implements Observer {
	
	private String nombre;
	
	public Editor(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public void update(int cantidad) {
		System.out.println("Hola, se esta notificando que necesitamos " + cantidad + " de ediciones al editor: " + this.nombre + ". Muchas gracias.");		
	}
		
}
