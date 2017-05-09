package controller;

import model.SelectedInterlocutor;

public class SelectInterlocutorRequest {

	private SelectedInterlocutor selectedInterlocutor;
	
	public SelectInterlocutorRequest(SelectedInterlocutor selectedInterlocutor){
		this.selectedInterlocutor = selectedInterlocutor;
	}
	
	public void doIt(String interlocutorName){
		selectedInterlocutor.selectInterlocutor(interlocutorName);
	}
	
}
