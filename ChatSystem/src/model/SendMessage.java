/*Declenchee par SendMessageRequest
 *Envoi un message texte*/

package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

import message.Message;

public class SendMessage {
	
	private UserList userList;
	private SelectedInterlocutor selectedInterlocutor;
	private int LOCAL_PORT = 8888;
	
	public SendMessage(UserList userList, SelectedInterlocutor selectedInterlocutor){
		this.userList = userList;
		this.selectedInterlocutor = selectedInterlocutor;
	};
	
	public void send(String data) throws Exception {
		
		if(userList.isConnected(selectedInterlocutor.getSelectedInterlocutor())){
			Message messageToSend = new Message();
			messageToSend.setData(data);
			
			// Envoyer le message a l'utilisateur correspondant a la conversation selectionnee
			// TODO : Le port utilise localement par le socket ci-dessous est un port qqconque on utilise toujours le meme
			// 		  SendMessage n'etant pas un thread (et aussi par la logique des choses) on n'envoie qu'un seul message
			//		  a la la fois donc on suppose que dans cette methode on peut utiliser toujours le meme port : LOCAL_PORT
			Socket outToDest = null;
			try {
				
				outToDest = new Socket(
						userList.getSock(selectedInterlocutor.getSelectedInterlocutor()).getAdd(),
						userList.getSock(selectedInterlocutor.getSelectedInterlocutor()).getPort()
				);
				System.out.println("Creation du socket d'envoi de "+outToDest.getInetAddress()+":"+outToDest.getLocalPort()+" vers "+userList.getSock(selectedInterlocutor.getSelectedInterlocutor()).getAdd()+":"+userList.getSock(selectedInterlocutor.getSelectedInterlocutor()).getPort());
				
				
				ObjectOutputStream outputStream = new ObjectOutputStream(outToDest.getOutputStream());
				outputStream.writeObject(messageToSend);
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(outToDest != null){
					try {
						System.out.println("Fermeture du socket d'envoi de "+InetAddress.getLocalHost()+":"+LOCAL_PORT+" vers "+userList.getSock(selectedInterlocutor.getSelectedInterlocutor()).getAdd()+":"+userList.getSock(selectedInterlocutor.getSelectedInterlocutor()).getPort());
						outToDest.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			// Mise a jour du fichier de la conversation log pour l'interlocuteur concerne
			selectedInterlocutor.getSelectedInterlocutor().updateConversationFile(
					"Vous"+", "+new Date()+" :"+
					String.format("%n")+messageToSend.getData()+String.format("%n")+String.format("%n"));
		}else{
			System.out.println("Impossible d'envoyer le message");
			throw new Exception("Envoi impossible") ; 
		}
	}
	
}
