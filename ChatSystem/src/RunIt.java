import java.io.IOException;
import java.net.InetAddress;
//import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;

import model.AnnouncementSocket;
import model.Connect;
import model.Disconnect;
import model.IsConnected;
import model.ListenAnnouncement;
import model.Login;
import model.NumberNewMessages;
import model.ReceiveMessage;
import model.SelectedInterlocutor;
import model.SendAnnouncement;
import model.SendMessage;
import model.SocketInfo;
import model.User;
import model.UserList;
import model.WatchdogVerifier;
import view.ChatInterface;
import view.LoginInterface;
import controller.ConnectionRequest;
import controller.DisconnectionRequest;
import controller.ResetNbNotifRequest;
import controller.SelectInterlocutorRequest;
import controller.SendMessageRequest;

public class RunIt {

	public static void main(String[] args) {
		
		System.out.println("Bienvenue dans le Chatsystem");
		
		try {
			InetAddress myAdd = null ;
			if(args.length == 1){
				try{
					myAdd = InetAddress.getByName(args[0]);
				}catch(Exception e ){
					e.printStackTrace();
				}
			}
			if(myAdd==null){
				try{
					myAdd = InetAddress.getLocalHost();
				}catch(Exception e ){
					e.printStackTrace();
				}
			}
			
			int portLocal = 4567 ;
			
			
			/*************************************************************
			 * CREATION DU MODELE
			 *************************************************************/
			
			//Creation d'un objet User pour l'utilisateur qui se connecte
			User localUser = new User(new Login("uselessFile"));
			//creation du socket de reception (sur lequel on attend les messages venant des autres personnes)
			//comme on fait du tcp c en fait le 3way hanshake qui arrive sur ce socket qui est en "accept" et qui ouvre
			//ensuite un socket temporaire pour la reception des paquets.
			ServerSocket srvSock;
			srvSock = new ServerSocket(portLocal);
			
			//Socket sur lequel les autres doivent me joindre (sert juste à remplir la ligne du localUser dans la userList)
			SocketInfo sockToJoin = new SocketInfo(myAdd,portLocal); //repr�sente dans la userList le srvSock qu'on vient de cr�er plus haut. (on voit qu'on utilise le m�me port 4567).
			
			//liste des utilisateurs connectes (on met l'utilisateur local dans cette liste en le mappant au Socket sur lequel on le contacte du point de vue des users distants)
			UserList userList = new UserList();
			//userList.addCouple(localUser,sockToJoin);
			
			//objet memorisant la conversation selectionnee
			SelectedInterlocutor selectedInterlocutor = new SelectedInterlocutor(userList);
			
			//Creation de l'annonce
			//MessageUser announce = new MessageUser("localUser(temporary)", InetAddress.getLocalHost(), 7777, typeConnect.CONNECTED);
			//TODO CHANGER le num PORT
			// Objets pour l'operation de maintien de connection
			AnnouncementSocket scktHellos = new AnnouncementSocket(InetAddress.getByName("225.1.2.3"), 5001);
			//Ligne Optionnel pour faire fonctionner en wifi
			scktHellos.getSocket().setNetworkInterface(NetworkInterface.getByInetAddress(myAdd));
			SendAnnouncement sendHellos = new SendAnnouncement(scktHellos, localUser, sockToJoin);
			ListenAnnouncement rcvHellos = new ListenAnnouncement(scktHellos, userList);
			WatchdogVerifier wtdVerif = new WatchdogVerifier(userList);
			
			//Nombre de nouveaux messages notifies
			NumberNewMessages nbNotifs = new NumberNewMessages();
			
			//Envoi et reception des messages de données
			SendMessage sendMessage = new SendMessage(userList,selectedInterlocutor);
			ReceiveMessage rcvMessage = new ReceiveMessage(srvSock, userList, nbNotifs);
			
			//Objet traduisant l'etat de connexion de l'utilisateur local (connecte ou deconnecte)
			IsConnected isConnected = new IsConnected();

			//Prise en charge de la connexion et de la deconnexion
			Connect co = new Connect(sendHellos, rcvHellos, wtdVerif,rcvMessage, isConnected);
			Disconnect disco = new Disconnect(sendHellos, rcvHellos, wtdVerif,rcvMessage, isConnected);
			
			/*************************************************************
			 * CREATION DU CONTROLEUR
			 *************************************************************/

			ConnectionRequest coRequest = new ConnectionRequest(localUser, co);
			DisconnectionRequest discoRequest = new DisconnectionRequest(disco);
			SendMessageRequest sendMessageRequest = new SendMessageRequest(sendMessage);
			//SendFileRequest sendFileRequest = new SendFileRequest(...);
			SelectInterlocutorRequest selectInterlocutorRequest = new SelectInterlocutorRequest(selectedInterlocutor);
			ResetNbNotifRequest resetNotifsRequest = new ResetNbNotifRequest(nbNotifs);
			
			/*************************************************************
			 * CREATION DE LA VUE
			 *************************************************************/
			
			//Lancer IHM de login
			LoginInterface loginIHM = new LoginInterface("Chatsystem:Login", coRequest);
			//Lancer IHM de chat
			ChatInterface chatIHM = new ChatInterface("Chatsystem", discoRequest, sendMessageRequest,
									selectInterlocutorRequest, resetNotifsRequest);
			
			
			/*************************************************************
			 * AJOUTER LA VUE EN TANT QU'OBSERVER DU MODELE
			 *************************************************************/
			isConnected.addObserver(loginIHM);
			isConnected.addObserver(chatIHM);
			userList.addObserver(chatIHM);
			selectedInterlocutor.addObserver(chatIHM);
			nbNotifs.addObserver(chatIHM);
			
			
			/*************************************************************
			 * POUR TESTS TEMPORAIRES
			 *************************************************************/
			/**
			// J'attend 3 secondes pour avoir le temps de me connecter en cliquant sur connexion 
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// puis je simule la connexion d'un remote user (il faut que ce user apparaisse dans la liste
			// des conv sur la chat interface)
			User toto = new User(new Login("toto"));
			userList.addCouple(toto, new Socket());
			
			
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// je simule l'ajout d'une notif d'arrivee de message pour verifier son reset en cliquant sur l'area
			nbNotifs.incrementNumber();
			
			// J'attend 3 secondes pour avoir le temps de cliquer sur toto dans la JList et donc voir la
			// mise a jour de la conversation avec toto lors de la reception du message simulee plus bas
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Je simule l'arrivee d'un message
			Message msg = new Message();
			msg.setData("coucou ! oui ca va et toi ? :D");
			toto.updateConversationFile(toto.getLogin().getName()+", "+new Date()+" :"+String.format("%n")+
					msg.getData()+String.format("%n")+String.format("%n"));
			
			*/
			
			//userList.addCouple(new User(new Login("Vera")), new SocketInfo("127.0.0.1", 3000));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		/* model();
		 * controller(model)
		 * view(controller);
		 * */
	}

}
