package model;

import java.util.Observable;

public class NumberNewMessages extends Observable {

	private int unreadMessages;
	
	public NumberNewMessages(){
		this.unreadMessages = 0;
	}
	
	public void incrementNumber(){
		this.unreadMessages++;
		System.out.println("incrementNumber appelee\n");
		setChanged();
		notifyObservers(this.unreadMessages);
	}
	
	public void resetNumber(){
		this.unreadMessages = 0;
		setChanged();
		notifyObservers(this.unreadMessages);
	}

	public int getUnreadMessages() {
		return unreadMessages;
	}
	
}
