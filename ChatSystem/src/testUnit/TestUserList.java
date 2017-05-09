package testUnit;

import static org.junit.Assert.assertTrue;

import java.net.InetAddress;

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

public class TestUserList {

private UserList usList = new UserList();
	
	private User u1 ;
	private User u2 ;
	private User u3 ;
	
	private SocketInfo sock1 ;
	private SocketInfo sock2 ;
	private SocketInfo sock3 ;
	
	@Before
	public void setUp() throws Exception {
		usList = new UserList();
		
		u1 = new User(new Login("User1"));
		u2 = new User(new Login("User2"));
		u3 = new User(new Login("User3"));
		
		sock1 = new SocketInfo("10.10.10.1",7777);
		sock2 = new SocketInfo("10.10.10.2",8888);
		sock3 = new SocketInfo("10.10.10.3",9999);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddCouple() {
		usList.addCouple(u1, sock1);
		
		assertTrue("",usList.getCouples().size()==1);
		assertTrue("",usList.isConnected(u1));
		
		usList.addCouple(u2, sock2);
		assertTrue("",usList.getCouples().size()==2);
		assertTrue("",usList.isConnected(u2));
		
		usList.addCouple(u3, sock3);
		usList.removeCouple(u2);

		assertTrue("",usList.getCouples().size()==2);
		assertTrue("",usList.isConnected(u3));
		assertTrue("",!usList.isConnected(u2));
		
		//fail("Not yet implemented");
	}




}
