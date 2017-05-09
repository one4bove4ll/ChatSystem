package controller;

import model.NumberNewMessages;

public class ResetNbNotifRequest {
	
	private NumberNewMessages nbNotifs;
	
	public ResetNbNotifRequest(NumberNewMessages nbNotifs){
		this.nbNotifs = nbNotifs;
	}
	
	public void doIt(){
		this.nbNotifs.resetNumber();
	}
}
