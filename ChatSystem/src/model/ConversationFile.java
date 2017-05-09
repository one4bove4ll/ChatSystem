/*Un type pour representer un fichier texte dans lequel est memorisee une conversation*/

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

public class ConversationFile extends Observable {

	//prevoir un format d'ecriture d'une conversation dans le fichier texte !!!!!
	private String nameDiscussion;
	private File discussionLog;
	
	public ConversationFile(String nameLog){
		nameDiscussion = nameLog;
		//creation du fichier de log de la conversation
		discussionLog = new File((nameDiscussion+".txt"));

		
		try {
			discussionLog.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error while trying to create file \""+discussionLog.getName()+"\" as the conversation"
					+ " file for corresponding User. Further access tried to this file may generate another exception");
		}
	}
	
	//Ecrire un nouveau message dans le fichier de log de la discussion
	/*
	 * ATTENTION !!! VERIFIER QU'ON UTILISE BIEN LE MODE APPEND ET PAS ECRITURE PAR-DESSUS !!!!
	 */
	public void writeToLog(String textToAppend){
		
		FileWriter fw = null;
		try {
			//Ouverture du flux

			fw = new FileWriter(this.discussionLog, true);
			//Ecriture
			// TODO : verifier que write ci-dessous fait un append et pas un ecrasement avec la nouvelle donnee
			fw.append(textToAppend);
			//Forcage de l'ecriture
			fw.flush();
		} catch (IOException e){
			System.out.println("Erreur lors de l'ecriture dans le fichier de la conversation " + nameDiscussion);
			e.printStackTrace();
		} finally {
			// On ferme notre flux de donnees dans un bloc finally pour s'assurer
			// que cette instruction sera executee dans tous les cas meme si
			// une exception est levee
			try {
				if (fw != null)
					//Fermeture du flux
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		setChanged();
		notifyObservers(readFromLog());
	}
	
	//Lire le fichier de log de la discussion
	public String readFromLog(){
		String discussion = "";
		FileReader fr = null;
		try {
			//Ouverture
			fr = new FileReader(this.discussionLog);
			//BufferedReader br = new BufferedReader(fr);
			
			int i = 0;
			//Lecture des donn�es
			while((i = fr.read()) != -1)
				discussion += (char)i;
			
		} catch (FileNotFoundException e){
			System.out.println("Erreur lors de l'ouverture du fichier de la conversation " + nameDiscussion);
			e.printStackTrace();
		} catch (IOException e){
		System.out.println("Erreur lors de la lecture depuis le fichier de la conversation " + nameDiscussion);
		e.printStackTrace();
		} finally {
			// On ferme notre flux de donnees dans un bloc finally pour s'assurer
			// que cette instruction sera executee dans tous les cas meme si
			// une exception est levee
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return discussion;
	}
	
	public void cleanLog(){
		//ECRIRE EN MODE "ECRITURE PAR-DESSUS" UNE CHAINE DE CARACTERE VIDE DANS LE FICHIER DE LOG
		// penser dans ce cas a faire le setChanged() et le notifyObservers()
		//setChanged();
		//notifyObservers(ReadFromLog());
	}
	
	public File getLog(){
		return discussionLog;
	}
	
}
