package tests;

import org.junit.Test;

import model.PlayList;
import model.Song;

/**
 * Test the PlayList class.
 * 
 * @author 	George Barnum 
 * 			Lu Ye
 *
 */
public class PlayListTest {
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	@Test
	public void test() {
		String name1 = "a";
		int seconds = 12;
		String artist = "who";
		String location = baseDir + "BlueRidgeMountainMist.mp3";
		Song songtest = new Song(name1, seconds, artist, location);
		PlayList playList = new PlayList();
		playList.queueUpNextSong(songtest);
		playList.queueUpNextSong(songtest);
		playList.queueUpNextSong(songtest);

	}

}
