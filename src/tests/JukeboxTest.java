package tests;

import model.Jukebox;
import model.Song;
import org.junit.Test;

/**
 * Test the Jukebox class.
 * 
 * @author 	George Barnum
 * 		 	Lu Ye
 *
 */
public class JukeboxTest {
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	@Test
	public void test() {
		Song a = new Song("Space Music", 6, "Unknown", baseDir
				+ "spacemusic.au");
		Jukebox testJukebox = new Jukebox();
		testJukebox.selectSong(a);
		testJukebox.login("Not an", "account");
		testJukebox.selectSong(a);
		testJukebox.login("Chris", "1");
		testJukebox.selectSong(a);
		testJukebox.logout();
		testJukebox.selectSong(a);
		testJukebox.getSongCollection();
	}

}
