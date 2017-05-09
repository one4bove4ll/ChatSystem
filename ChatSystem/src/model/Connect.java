/*Declenchee par ConnectionRequest
 *Possède une méthode qui :
 *	-Edite le username recupere
 *	-Lance un thread qui envoie periodiquement les annonces pour dire que l'utilisateur local est connecte
 *	-Lance un autre thread qui ecoute sur un socket pour les annonces des autres utilisateurs connectes*/

package model;

public class Connect {
	
	private SendAnnouncement sendHellos;
	private ListenAnnouncement rcvHellos;
	private WatchdogVerifier wtdVerifier;
	private ReceiveMessage rcvMessage ;
	private IsConnected isConnected;
	
	
	public Connect(SendAnnouncement sendHellos, ListenAnnouncement rcvHellos, WatchdogVerifier wtdVerifier, ReceiveMessage rcvMessage, IsConnected isConnected){
		this.sendHellos = sendHellos;
		this.rcvHellos = rcvHellos;
		this.wtdVerifier = wtdVerifier;
		this.rcvMessage = rcvMessage ;
		this.isConnected = isConnected;
	}
	
	public void startConnection(){
		//edite le username recupere
		//...
		
		//System.out.println("methode startConnection de l'objet de classe Connect!");
		
		//Changement de l'etat de connexion -> on passe a "connecte"
		isConnected.setConnected(true);
		
		//thread qui envoie periodiquement les annonces
		sendHellos.start();
		
		//thread qui ecoute sur un socket pour les annonces des autres utilisateurs connectes
		rcvHellos.start();
		
		//thread WatchdogVerifier
		wtdVerifier.start();
		
		//thread de reception des messages
		rcvMessage.start();
	}
	
}
