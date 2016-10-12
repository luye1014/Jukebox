package model;

import java.io.Serializable;

/**
 * Class to coordinate the functionality of Account, Song, and PlayList classes.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class Jukebox implements Serializable {
	public static String baseDir = System.getProperty("user.dir")
			+ System.getProperty("file.separator") + "songfiles"
			+ System.getProperty("file.separator");

	private SongCollection songs;
	private AccountCollection accounts;
	private PlayList playList;

	private Account activeAccount;

	public Jukebox() {
		songs = new SongCollection();
		accounts = new AccountCollection();
		playList = new PlayList();

		// Hardcoded values:
		songs.add(new Song("Space Music", 6, "Unknown", baseDir
				+ "spacemusic.au"));
		songs.add(new Song("Flute", 5, "Sun Microsystems", baseDir
				+ "flute.aif"));
		songs.add(new Song("Blue Ridge Mountain Mist", 38, "Ralph Schuckett",
				baseDir + "BlueRidgeMountainMist.mp3"));
		songs.add(new Song("Determined Tumbao", 20, " FreePlay Music", baseDir
				+ "DeterminedTumbao.mp3"));
		songs.add(new Song("Swing Cheese", 15, "FreePlay Music", baseDir
				+ "SwingCheese.mp3"));
		songs.add(new Song("Tada", 2, "Microsoft", baseDir + "tada.wav"));
		songs.add(new Song("Untameable Fire", 282, "Pierre Langer", baseDir
				+ "UntameableFire.mp3"));

		accounts.add(new Account("Chris", "1"));
		accounts.add(new Account("Devon", "22"));
		accounts.add(new Account("River", "333"));
		accounts.add(new Account("Ryan", "4444"));

		activeAccount = null;
	}

	public void login(String username, String password) {
		activeAccount = accounts.login(username, password);
	}

	public void logout() {
		activeAccount = null;
	}

	public Account getActiveAccount() {
		return activeAccount;
	}

	public boolean selectSong(Song song) {
		if (activeAccount != null) {
			return activeAccount.play(playList, song);
		} else {
			return false;
		}
	}

	public boolean selectSong(int index) {
		if (activeAccount != null) {
			return activeAccount.play(playList, songs.getSongAt(index));
		} else {
			return false;
		}
	}

	public SongCollection getSongCollection() {
		return songs;
	}

	public PlayList getPlayList() {
		return playList;
	}

}
