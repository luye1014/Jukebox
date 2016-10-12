package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Song;

/**
 * Test the Song class.
 * 
 * @author 	George Barnum 
 * 			Lu Ye
 *
 */
public class SongTest {
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	@Test
	public void testGetter() {

		String name1 = "a";
		int seconds = 12;
		String artist = "who";
		String location = baseDir + "BlueRidgeMountainMist.mp3";
		Song songtest = new Song(name1, seconds, artist, location);
		assertEquals(name1, songtest.getTitle());
		assertEquals(seconds, songtest.getLength());
		assertEquals(artist, songtest.getArtist());
		assertEquals(location, songtest.getLocation());
	}

	@Test
	public void testcanPlay() {
		String name1 = "a";
		int seconds = 12;
		String artist = "who";
		String location = baseDir + "BlueRidgeMountainMist.mp3";
		Song songtest = new Song(name1, seconds, artist, location);
		assertTrue(songtest.canPlay());
		songtest.play();
		assertTrue(songtest.canPlay());
		songtest.play();
		assertTrue(songtest.canPlay());
		songtest.play();
		assertFalse(songtest.canPlay());
		songtest.play();

	}

}
