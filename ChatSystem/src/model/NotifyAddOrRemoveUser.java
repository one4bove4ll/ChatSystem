package model;

public class NotifyAddOrRemoveUser {
	
	private User userToNotify; // The user we want to notify the view about (user disconnected or connected)
	private int addOrRemove; // 0 => remove ; 1 => add 
	
	public NotifyAddOrRemoveUser(User userToNotify, int addOrRemove){
		this.userToNotify = userToNotify;
		this.addOrRemove = addOrRemove;
	}
	
	public User getUser(){
		return userToNotify;
	}
	
	public int getAddOrRemove(){
		return addOrRemove;
	}
}
