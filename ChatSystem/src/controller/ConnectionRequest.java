/*Declenchee quand on appuie sur le bouton de connexion de l'IHM "LoginInterface"
 *Appelle la methode "startConnection" de la classe Connect en lui passant le username en argument*/

package controller;

import model.*;


public class ConnectionRequest {

	private User localUser;
	private Connect co ;
	
	public ConnectionRequest(User localUser, Connect co){
		this.localUser = localUser ;
		this.co = co ;
	}
	
	public void doIt(String name) throws Exception{
		//System.out.println("methode doIt de l'objet de classe ConnectionRequest!");
		if(name.equals("")){
			throw new Exception("Veuillez rentrer un nom d'utilisateur pour vous connecter");
		}
		localUser.setLogin(new Login(name));
		this.co.startConnection();
	}
}
