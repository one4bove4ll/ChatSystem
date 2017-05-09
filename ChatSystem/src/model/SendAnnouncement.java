/*Un thread pour ecouter les annonces des utilisateurs qui sont connectes*/

package model;

import user.MessageUser;
import user.MessageUser.typeConnect;


public class SendAnnouncement extends Thread {
	
	private AnnouncementSocket sock ;
	private MessageUser  announce ;
	private User user;
	private SocketInfo infoLocalUSer ;
	
	public SendAnnouncement(AnnouncementSocket socket, User user,SocketInfo infoLocalUser) {
		
		//creation du socket Multicast destination
		this.sock = socket ;
		
		this.user = user ;
		
		this.infoLocalUSer = infoLocalUser;
		
		//this.usList = usList;
		
	}
	
	public void run(){
		//construction de l'annonce
		this.announce= new MessageUser(this.user.getLogin().getName(), infoLocalUSer.getAdd(), infoLocalUSer.getPort(), typeConnect.CONNECTED);
		
		while (true){
			try {
				sleep(2000);
			} catch (InterruptedException e1) {
				//e1.printStackTrace();
			}
			// envoyer une annonce
			sock.sendAnnounce(announce);
		}
		
	}

	
    //private SocketAnnouncements sock ;
    //private MessageUser  announce ;
    //private byte[] bar ;
    //private InetAddress myAdd ;
	//
    //public SendAnnouncement(SocketAnnouncements socket,User user, UserList userList) {
    //    //creation du socket Multicast destination
    //    this.sock = socket ;
	//
    //    //creation de l'annonce et de byte array à envoyer
    //    typeConnect state = typeConnect.CONNECTED ;
    //    try{
    //            myAdd = InetAddress.getLocalHost();
    //            announce = new MessageUser(user.getLogin().getName(), userList.getSock(user).getInetAddress(), userList.getSock(user).getLocalPort(), state);
    //    }catch(Exception e){
    //            e.printStackTrace();
    //            System.out.println("Erreur lors de la recuperation de l'adresse locale");
    //    }
    //    // Serialize to a byte array
    //    ByteArrayOutputStream bStream = new ByteArrayOutputStream();
    //    ObjectOutput oo;
    //    try {
    //            oo = new ObjectOutputStream(bStream);
    //            oo.writeObject(announce);
    //            oo.close();
    //            bar = bStream.toByteArray();
    //    } catch (IOException e) {
    //            e.printStackTrace();
    //            System.out.println("Erreur lors de la transformation en byte array de la classe");
    //    }
	//}
	//
    //
	//@Override
    //public void run() {
	//	
    //    while(true) {
    //        try {
    //        	//envoie periodiquement des announcements
    //        	Thread.sleep(2000);
    //        	sock.send(new DatagramPacket(bar, bar.length,myAdd , 8080));
    //        	System.out.println("Hello!");
    //        } catch (InterruptedException ex) {
    //            Thread.currentThread().interrupt();
    //            break; // Sortie de la boucle infinie                
    //        } catch (IOException e2) {
    //            e2.printStackTrace();
    //            System.out.println("Erreur lors de l'envoi de l'annonce");
    //        }
	//
    //    }
    //}
	
	
}
