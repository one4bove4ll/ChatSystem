package model;


import user.MessageUser;

public class ListenAnnouncement extends Thread{

	private AnnouncementSocket sock ;
	private UserList usList;
	
	public ListenAnnouncement(AnnouncementSocket socket, UserList usList){
		this.sock = socket ;
		this.usList = usList;
	}
	
	public void run() {
		System.out.println("Debut de l'ecoute des annonces");
		while(true){
			MessageUser announce = sock.receiveAnnounce();
			 
			System.out.println(announce.getPseudo()+" is connected at "+announce.getIP()+":"+announce.getPort()+"!");
			User isNewUser = new User(new Login(announce.getPseudo()));
			if (!usList.isConnected(isNewUser)) {
				// On recupere l'adresse IP que le remote user nous a specifie dans son annonce
				// et ou l'on peut le contacter (un MessageUser contient ces infos ; mais pas un Message)
				System.out.println("Adding user"+announce.getPseudo()+" at "+announce.getIP()+":"+announce.getPort());
				usList.addCouple(isNewUser, new SocketInfo(announce.getIP(),announce.getPort())); // On recupere l'adresse IP que le remote user nous a specifie dans son annonce et ou l'on peut le contacter

			}else { // S'il est deja dans la liste alors on met simplement a jour son watchdog
				//Date currentDate = new Date();
				//usList.getUser(isNewUser.getLogin().getName()).setLastSeen(currentDate);
				//isNewUser.setLastSeen(currentDate);
				usList.refreshUser(isNewUser.getLogin().getName());
			}
			
		}
	}
	
	/*
 ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(recBytes));
Message messageClass = (Message) iStream.readObject();
iStream.close();
	 */
	
}
