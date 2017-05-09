package testUnit;


import static org.junit.Assert.*;
import org.junit.*;
import model.NumberNewMessages;

public class TestNumberNewMessages {

	private NumberNewMessages nbNewMsgs ;
	
	@Before
	public void setUp() throws Exception {

		nbNewMsgs = new NumberNewMessages();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIncrementNumber() {
		int before = nbNewMsgs.getUnreadMessages();
		nbNewMsgs.incrementNumber();
		assertEquals(before+1, nbNewMsgs.getUnreadMessages());
	}

	@Test
	public void testResetNumber() {
		NumberNewMessages nbNewMsgs = new NumberNewMessages();
		nbNewMsgs.incrementNumber();
		nbNewMsgs.resetNumber();
		assertEquals(0, nbNewMsgs.getUnreadMessages());
	}

}