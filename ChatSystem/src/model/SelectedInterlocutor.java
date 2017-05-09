package model;

import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

public class SelectedInterlocutor extends Observable {
	
	private User selectedInterlocutor;
	private UserList userList;
	
	public SelectedInterlocutor(UserList userList){
		this.userList = userList;
		this.selectedInterlocutor = new User(new Login("NoOne"));
	}
	
	public void selectInterlocutor(String interlocutorName){
		System.out.println("Conversation selectionnee (\""+selectedInterlocutor.getLogin().getName()+"\") ");
		//Bouclage sur la liste d'utilisateurs connectes pour trouver celui dont on a le nom
		Iterator<Map.Entry<User, SocketInfo>> entries = userList.getCouples().entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<User, SocketInfo> entry = entries.next();
		    if (entry.getKey().getLogin().getName() == interlocutorName){
		    	selectedInterlocutor = entry.getKey();
		    	System.out.println("changee pour "+selectedInterlocutor.getLogin().getName());
		    	setChanged();
				//Notifie la vue qu'il faut afficher les messages de la nouvelle conversation courante
				// TODO verifier que la lecture dans le fichier de log n'est pas en concurrence avec un autre lecteur !!
				// TODO creer un fichier de conv d�s qu'on sait qu'une personne est connectee (si le fichier
				//		existe pas deja ; pour verifier se servir du nom du fichier) sinon on va lire un fichier qui n'existe pas !!!
				notifyObservers(selectedInterlocutor.getConvFile().readFromLog());
		    }
		}
	}
	
	public User getSelectedInterlocutor(){
		return selectedInterlocutor;
	}
	
}
