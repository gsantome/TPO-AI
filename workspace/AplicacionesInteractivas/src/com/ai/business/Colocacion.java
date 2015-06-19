package com.ai.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.ai.models.ItemColocacion;
import com.ai.observer.Observable;
import com.ai.observer.Observer;

public class Colocacion implements Observable {
	private int codigo;
	private String pauta;
	private int idEdicion;
	private Date fecha;
	private Vector<ItemColocacion> itemsColocaciones;
	
	private ArrayList<Observer> observers; 
	
	public Colocacion() {
		this.observers = new ArrayList<Observer>();
		this.itemsColocaciones = new Vector<ItemColocacion>();
	}
	
	public Colocacion( int codigo, int idEdicion, String pauta, Date fecha ) {
		this.observers = new ArrayList<Observer>();
		this.itemsColocaciones = new Vector<ItemColocacion>();
		
		this.codigo = codigo;
		this.idEdicion = idEdicion;
		this.pauta = pauta;
		this.fecha = fecha;
	}
	
	public Colocacion( int idEdicion, String pauta, Date fecha ) {
		this.itemsColocaciones = new Vector<ItemColocacion>();
		
		this.idEdicion = idEdicion;
		this.pauta = pauta;
		this.fecha = fecha;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getPauta() {
		return pauta;
	}

	public void setPauta(String pauta) {
		this.pauta = pauta;
	}

	public int getIdEdicion() {
		return idEdicion;
	}

	public void setIdEdicion(int idEdicion) {
		this.idEdicion = idEdicion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Vector<ItemColocacion> getItemsColocaciones() {
		return itemsColocaciones;
	}
	
	public void addItemColocacion(ItemColocacion itemColocacion) {
		this.itemsColocaciones.add(itemColocacion);
	}

	@Override
	public void registerObserver(Observer observer) {
		observers.add(observer);	
	}

	@Override
	public void removeObservers() {
		observers.clear();
	}

	@Override
	public void notifyObservers(int cantidad) {
		System.out.println("Empezamos a notificar a los editores");
		for (Observer observer : observers) {
			observer.update(cantidad);
		}
		
	}
	
	@Override
	public String toString() {
		return this.getCodigo() + ": " + this.getFecha().toString() + " - " + this.getPauta();
	}
}
