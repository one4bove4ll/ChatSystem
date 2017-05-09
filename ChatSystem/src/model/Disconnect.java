/*Declenchee par ConnectionRequest
 *(Reset le username ?)
 *PossÃ¨de une mÃ©thode statique qui :
 *	-Stoppe le thread d'envoi des annonces
 *	-Stoppe le thread d'ecoute des annonces des utilisateurs connectes*/

package model;

public class Disconnect {
	
	private SendAnnouncement sendHellos;
	private ListenAnnouncement rcvHellos;
	private WatchdogVerifier wtdVerifier;
	private ReceiveMessage rcvMessage;
	private IsConnected isConnected;
	
	public Disconnect( SendAnnouncement sendHellos, ListenAnnouncement rcvHellos, WatchdogVerifier wtdVerif, ReceiveMessage rcvMessage, IsConnected isConnected ){ 
		this.sendHellos= sendHellos;
		this.rcvHellos = rcvHellos ;
		this.wtdVerifier = wtdVerif;
		this.rcvMessage = rcvMessage;
		this.isConnected = isConnected;
	}
	
	//public void stopConnection(User user, UserList userList, SocketAnnouncements socket){
	public void stopConnection() throws InterruptedException {
		
		//TODO : Attention notre facon de faire du multithreading n'est pas top :
		//		 avec cette implementation a la va-vite, on génère une exception si l'on tente de se reconnecter
		//		 après la deconnexion, et les threads ne redémarrent pas. Mais il n'est pas demandé dans les requirements
		//		 de permettre a l'tuilisateur local d'enchainer plusieurs phases "connexion/deconnexion" sans quitter
		//		 l'application, donc nous ne gererons pas ce probleme pour le moment.
		sendHellos.interrupt();
		rcvHellos.interrupt();
		wtdVerifier.interrupt();
		rcvMessage.interrupt();
		
		//Changement de l'etat de connexion -> on passe a "deconnecte"
		isConnected.setConnected(false);
		
		// Peut-etre faut-il nettoyer des structures ??? genre la liste des users connectÃ©s
	}
	
}
