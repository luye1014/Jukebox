package tests;

import static org.junit.Assert.*;
import model.Song;
import model.SongCollection;

import org.junit.Test;

/**
 * Test the SongCollection class.
 * 
 * @author 	George Barnum
 * 		 	Lu Ye
 *
 */
public class SongCollectionTest {
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	@Test
	public void test() {
		String name1 = "a";
		int seconds = 12;
		String artist = "who";
		String location = baseDir + "BlueRidgeMountainMist.mp3";
		Song c = new Song(name1, seconds, artist, location);
		Song a = (new Song("Space Music", 7, "Sun Microsystems", baseDir
				+ "spacemusic.au"));
		Song b = (new Song("Flute", 7, "Sun Microsystems", baseDir
				+ "flute.aif"));
		SongCollection songs = new SongCollection();
		songs.add(a);
		songs.add(b);
		songs.add(c);
		assertEquals(String.class, songs.getColumnClass(0));
		assertEquals(String.class, songs.getColumnClass(1));
		assertEquals(Integer.class, songs.getColumnClass(2));
		assertEquals(null, songs.getColumnName(7));
		assertEquals("Artist", songs.getColumnName(0));
		assertEquals("Title", songs.getColumnName(1));
		assertEquals("Seconds", songs.getColumnName(2));
		assertEquals(3, songs.getColumnCount());
		assertEquals(3, songs.getRowCount());
		assertEquals("Sun Microsystems", songs.getValueAt(0, 0));
		assertEquals("Space Music", songs.getValueAt(0, 1));
		assertEquals(7, songs.getValueAt(0, 2));
		assertEquals(null, songs.getValueAt(0, 7));
		assertFalse(songs.isCellEditable(0, 0));
		assertEquals(a, songs.getSongAt(0));
	}
}
