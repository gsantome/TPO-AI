package com.ai.observer;

public interface Observable {
	public void registerObserver(Observer observer);
	public void removeObservers();
	public void notifyObservers(int cantidad);
}
