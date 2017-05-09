package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import message.Message;

public class StreamToFile extends Thread{

	private Socket sock;
	private UserList userList ;
	
	public StreamToFile(Socket sock,UserList userList) {
		this.sock = sock;
		this.userList = userList ;
	}
	
	@Override
	public void run(){
	
	   try {
			Message message;
			
		    ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		    // TODO verifier que les deux lignes ci-dessous renvoient bien adresse IP et port de l'utilisateur
		    // qui nous contacte et pas NOTRE adresse IP et notre port local!!!!
		    InetAddress distantAdd = sock.getInetAddress();
		    int port = sock.getLocalPort();
		    
		    
		    
		    // Il faut trouver de quel utilisateur vient le message pour l'ecrire dans le fichier qui correspond
		    // a la bonne conversation
		    User sourceUser = null;
		    boolean found = false;
			Iterator<Map.Entry<User, SocketInfo>> entries = userList.getCouples().entrySet().iterator();
			
			while (entries.hasNext() && !found) {
			    Map.Entry<User, SocketInfo> entry = entries.next();
			    //System.out.println("Comparaison avec : "+ entry.getValue().getAdd()+":"+port+ "avec "+ entry.getValue().getAdd()+":"+ entry.getValue().getPort());
			    if (entry.getValue().getAdd().equals(distantAdd) && entry.getValue().getPort()== port){
			    	found = true ;
			    	sourceUser = entry.getKey();
			    }
			}
		    if(!found){
		    	System.out.println("Bug dans la matrice.");
		    }

		    
		    // TODO faut-il rajouter l'utilisateur source dans la liste des utilisateurs connect�s si cela n'avait pas
		    // ete fait car pas de hellos recus de sa part ????
		    
		    message = (Message) ois.readObject();
		    

		    System.out.println("Mise a jour de la conversation : "+distantAdd+":"+port+" : "+message.getData());
		    
		    sourceUser.updateConversationFile(sourceUser.getLogin().getName()+", "+new Date()+" :"+String.format("%n")+
					message.getData()+String.format("%n")+String.format("%n"));
		    
		    System.out.println("Fermeture du socket réception");
		    sock.close();
		    ois.close();
	   } catch (ClassNotFoundException | IOException e) {
		   	e.printStackTrace();
	   }
	   
	}
}
