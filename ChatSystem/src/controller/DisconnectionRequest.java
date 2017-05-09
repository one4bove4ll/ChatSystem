/*Declenchee quand on appuie sur le bouton de deconnexion de l'IHM "ChatInterface"
 *Appelle la methode statique de la classe Disconnect*/

package controller;

import model.*;

public class DisconnectionRequest {
	
	private Disconnect disco ;
	
	public DisconnectionRequest(Disconnect disco){
		this.disco = disco;
	}
	
	public void doIt() throws InterruptedException{
		System.out.println("methode doIt de l'objet de classe DisconnectionRequest!");
		this.disco.stopConnection();
	}
}
