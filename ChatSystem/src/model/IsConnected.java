package model;

import java.util.Observable;

public class IsConnected extends Observable{
	
	private boolean isConnected;
	
	public IsConnected(){
		this.isConnected = false;
	}
	
	public void setConnected(boolean isConnected){
		System.out.println("methode setConnected de l'objet de classe IsConnected!");
		this.isConnected = isConnected;
		System.out.println("		----> l'attribut \"isConnected\" a pour valeur: "+this.isConnected);
		setChanged();
		notifyObservers(this.isConnected);
	}
	
}
