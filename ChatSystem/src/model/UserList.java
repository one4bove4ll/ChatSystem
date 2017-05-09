/*Contient la liste des correspondances User <-> Socket*/

package model;

import java.util.Date;
import java.util.Observable;
import java.util.concurrent.ConcurrentHashMap;

public class UserList extends Observable{

	private ConcurrentHashMap<User, SocketInfo> userToSocket ;
	
	public UserList() {
		userToSocket = new ConcurrentHashMap<User, SocketInfo>() ;
	}
	
	synchronized public void addCouple(User user, SocketInfo sock){
		userToSocket.put(user,sock);
		setChanged();
		notifyObservers(new NotifyAddOrRemoveUser(user, 1));
	}
	
	synchronized public void removeCouple(User user){
		userToSocket.remove(user);
		setChanged();
		notifyObservers(new NotifyAddOrRemoveUser(user, 0));
	}
	
	synchronized public ConcurrentHashMap<User, SocketInfo> getCouples() {
		return userToSocket;
	}

	synchronized public SocketInfo getSock(User user){
		return userToSocket.get(user);
	}
	
	synchronized public boolean isConnected(User user) {
		//faire un test unitaire pour voir si il compare l'egalite des attributs ou l'emplacement meoire
		for(User u: userToSocket.keySet()){
			if(u.equals(user)) {
				return true;
			}
		}
		return false ;
	}
	
	synchronized public User getUser(String name){
		User user = new User(new Login(name));
		for(User u: userToSocket.keySet()){
			if(u.equals(user)) {
				return user;
			}
		}
		return null ;
	}
	
	synchronized public void refreshUser(String userName){
		User user = new User(new Login(userName));
		for(User u: userToSocket.keySet()){
			if(u.equals(user)) {
				Date currentDate = new Date();
				u.setLastSeen(currentDate);
			}
		}
	}
	
}
