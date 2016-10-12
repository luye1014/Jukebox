package tests;

import static org.junit.Assert.*;
import model.Account;
import model.PlayList;
import model.Song;

import org.junit.Test;

/**
 * Test the Account class.
 * 
 * @author 	George Barnum
 * 		 	Lu Ye
 *
 */
public class AccountTest {

	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	@Test
	public void test() {
		Song testSong = (new Song("Flute", 7, "Sun Microsytems", baseDir
				+ "flute.aif"));
		Song testLongSong = (new Song("Flute", 1500 * 60, "Sun Microsytems",
				baseDir + "flute.aif"));
		PlayList playList = new PlayList();

		String username = "test";
		String password = "1234";
		Account testAccount = new Account(username, password);
		assertTrue(testAccount.canLogin(username, password));
		assertFalse(testAccount.canLogin("foo", password));
		assertFalse(testAccount.canLogin(username, "bar"));
		assertFalse(testAccount.canLogin("foo", "bar"));
		assertEquals(Account.INITIAL_TIME, testAccount.getRemainingTime());
		assertTrue(testAccount.canPlay(testSong));
		testAccount.play(playList, testSong);
		assertEquals(Account.INITIAL_TIME - testSong.getLength(),
				testAccount.getRemainingTime());
		assertFalse(testAccount.canPlay(testLongSong));
		testAccount.play(playList, testSong);
		testAccount.play(playList, testSong);
		assertFalse(testAccount.canPlay(testSong));
	}

}
