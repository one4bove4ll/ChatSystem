/*Declenchee quand on appuie sur le bouton d'envoi de message de l'IHM "ChatInterface"
 *Appelle la methode d'envoi d'un message texte de la classe SendMessage en lui passant en argument :
 *	-le message texte à envoyer
 *	-le UserGroup correspondant à la liste d'utilisateurs auxquels il faut envoyer le message texte*/

package controller;

import model.*;

public class SendMessageRequest {
	
	private SendMessage sendMsg;
	
	public SendMessageRequest(SendMessage sendMsg){
		this.sendMsg = sendMsg;
	}
	
	public void doIt(String messageToSend) throws Exception{
		this.sendMsg.send(messageToSend);
	}
}
