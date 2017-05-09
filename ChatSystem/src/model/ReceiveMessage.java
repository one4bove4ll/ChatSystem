/*Un thread pour attendre les demandes de connexions et lancer un nouveau thread "StreamToFile" pour recevoir un message apres une demande de connexion*/

package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ReceiveMessage extends Thread {
	
	private ServerSocket srvSock;
	private UserList userList ;
	NumberNewMessages nbNotifs;
	
	public ReceiveMessage(ServerSocket srvSock, UserList userList, NumberNewMessages nbNotifs){
		this.srvSock = srvSock;
		this.userList = userList;
		this.nbNotifs = nbNotifs;
	}
	
	@Override
    public void run() {
		
		//Attendre les demandes de connexions
		while(true){
			
			Socket fromClientSocket;

		    try {
		    	fromClientSocket = srvSock.accept();
		    	System.out.println("Création d'un Socket de réception");
				StreamToFile uneReception = new StreamToFile(fromClientSocket,userList);
				uneReception.start();
				// Notification de reception du nouveau message
				nbNotifs.incrementNumber();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
}
