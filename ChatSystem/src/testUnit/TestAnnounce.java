package testUnit;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import model.AnnouncementSocket;
import model.ListenAnnouncement;
import model.Login;
import model.SendAnnouncement;
import model.SocketInfo;
import model.User;
import model.UserList;
import model.WatchdogVerifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import com.sun.corba.se.impl.legacy.connection.USLPort;

public class TestAnnounce {

	private AnnouncementSocket sockAnnonce ;
	private SendAnnouncement sendA ;
	private ListenAnnouncement listenA ;
	private WatchdogVerifier wtdV ;
	
	private UserList usList = new UserList();
	
	private User u1 ;
	
	private SocketInfo sock1 ;
	
	@Before
	public void setUp() throws Exception {
		usList = new UserList();
		
		u1 = new User(new Login("User1"));
		sock1 = new SocketInfo("10.10.10.1",7777);

		sockAnnonce = new AnnouncementSocket(InetAddress.getByName("225.1.2.3"), 4567);
		sendA = new SendAnnouncement(sockAnnonce, u1, sock1);
		listenA = new ListenAnnouncement(sockAnnonce, usList);
		wtdV = new WatchdogVerifier(usList);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCouple() {
		sendA.start();
		listenA.start();
		wtdV.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(usList.isConnected(u1));
		
		sendA.stop();
		
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue("L'utilisateur est toujours connecté",!usList.isConnected(u1));
		
		//fail("Not yet implemented");
	}
	
}

	
