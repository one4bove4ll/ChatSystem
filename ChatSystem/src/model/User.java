package model;

import java.io.File;
import java.util.Date;


public class User {

	private Login myLogin ;
	private Date lastSeen;
	private ConversationFile discussion;
	
	public User(Login log) {
		this.myLogin = log ;
		this.lastSeen = new Date();
		this.discussion = new ConversationFile(myLogin.getName());
	}
	
	public Login getLogin(){
		return myLogin ;
	}
	
	synchronized public void setLogin(Login log){
		this.myLogin = log ;
		File newName = new File(this.myLogin.getName()+".txt");
		this.discussion.getLog().renameTo(newName);
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	synchronized public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}
	
	public boolean equals(User other){
		return this.getLogin().equals(other.getLogin());
	}

	
	public ConversationFile getConvFile(){
		return discussion;
	}
	
	//Ecrire un message recu dans la conversation correspondante
	synchronized public void updateConversationFile(String newMessage){
		
		// PEUT-ETRE QUE LES DATA DE "GETDATA()" NE SONT PAS SOUS FORME DE STRING !!!!!
		discussion.writeToLog(newMessage);
	}
	
}
