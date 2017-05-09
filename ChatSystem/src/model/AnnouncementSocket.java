package model;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import user.MessageUser;

public class AnnouncementSocket {

	private MulticastSocket sock ;
	private InetAddress multiAdd;
	private int port ;
	
	public AnnouncementSocket(InetAddress add, int port) {
		try {
			this.port = port;
			this.multiAdd = add ;
			this.sock = new MulticastSocket(port);
			this.sock.joinGroup(add);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la creation du socket multicast");
		}
		
	}
	
	public void sendAnnounce(MessageUser announce){
		// Serialize to a byte array
		byte[] bar;
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		ObjectOutput oo;
		try {
			oo = new ObjectOutputStream(bStream);
			oo.writeObject(announce);
			oo.close();
			bar = bStream.toByteArray();
			this.sock.send(new DatagramPacket(bar, bar.length, multiAdd , port));
			System.out.println("Hello!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erreur lors de la serialisation (byte array) de l'annonce de connexion");
		} 
	}
	
	public MessageUser receiveAnnounce(){

		byte [] bar = new byte[500];
		DatagramPacket datapack = new DatagramPacket(bar,bar.length );
		
		try {
			sock.receive(datapack);
			ObjectInputStream iStream = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(bar)));
			MessageUser resultat = (MessageUser) iStream.readObject();
			iStream.close();
			return resultat ;
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Erreur lors de la reception d'une annonce");
			e.printStackTrace();
		}
		return null ;
	}
	
	public MulticastSocket getSocket(){
		return this.sock ;
	}
	
}
