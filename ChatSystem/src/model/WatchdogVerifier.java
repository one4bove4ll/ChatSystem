package model;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class WatchdogVerifier extends Thread {
	
	private UserList connectedUsers;
	private long MAXTIME = 3000; // max time (in milliseconds) before considering distant user is disconnected
	
	public WatchdogVerifier(UserList connectedUsers){
		this.connectedUsers = connectedUsers;
	}
	
	@Override
	public void run() {
		
		while (true){
			try {
				Thread.sleep(5000); // verification periodique des utilisateurs connectes, toutes les 5 secondes
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
			//Bouclage sur la liste d'utilisateurs connectes et verification de leurs timers
			//Map<User, Socket> map = new HashMap<User, Socket>();
			Iterator<Map.Entry<User, SocketInfo>> entries = connectedUsers.getCouples().entrySet().iterator();
			Date currentDate = new Date();
			while (entries.hasNext()) {
			    Map.Entry<User, SocketInfo> entry = entries.next();
			    if (currentDate.getTime() - entry.getKey().getLastSeen().getTime() > MAXTIME){
			    	connectedUsers.removeCouple(entry.getKey());
			    }
			}
		}
		
	}
	
}
