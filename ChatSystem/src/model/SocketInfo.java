package model;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SocketInfo {

	private InetAddress add;
	private int port ;
	
	public SocketInfo(String addStr, int port){
		try {
			this.add = InetAddress.getByName(addStr) ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.out.println("Erreur à la creéation d'une adresse");
		}
		this.port = port;
	}

	public SocketInfo(InetAddress add, int port ) {
		this.add = add;
		this.port = port ;
	}
	
	public InetAddress getAdd() {
		return add;
	}

	public void setAdd(InetAddress add) {
		this.add = add;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
}
