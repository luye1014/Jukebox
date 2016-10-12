package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class to store information about a students account in the Jukebox and
 * authenticate usernames and passwords.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class Account implements Serializable {
	public static final int MAX_PLAYS = 3;

	public static final int INITIAL_TIME = 1500 * 60;

	private int secondsRemaining;
	private LocalDate lastPlayed;
	private int plays;
	private String username;
	private String password;

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
		secondsRemaining = INITIAL_TIME;
		plays = 0;
		lastPlayed = null;
	}

	public boolean canLogin(String username, String password) {
		return username.equals(this.username) && password.equals(this.password);
	}

	public int getRemainingTime() {
		return secondsRemaining;
	}

	public int getRemainingPlays() {
		if (LocalDate.now().equals(lastPlayed)) {
			return MAX_PLAYS - plays;
		} else {
			return MAX_PLAYS;
		}
	}

	public boolean canPlay(Song song) {
		if (secondsRemaining >= song.getLength()) {
			if (LocalDate.now().equals(lastPlayed)) {
				return plays < MAX_PLAYS && song.canPlay();
			} else {
				return song.canPlay();
			}
		} else {
			return false;
		}
	}

	public boolean hasTimeForSong(Song song) {
		return secondsRemaining >= song.getLength();
	}

	public boolean hasPlays() {
		if (LocalDate.now().equals(lastPlayed)) {
			return MAX_PLAYS > plays;
		} else {
			return true;
		}
	}

	public boolean play(PlayList list, Song song) {
		if (secondsRemaining >= song.getLength()) {
			if (LocalDate.now().equals(lastPlayed)) {
				if (song.canPlay() && plays < MAX_PLAYS) {
					plays++;
					secondsRemaining -= song.getLength();
					list.queueUpNextSong(song);
					return true;
				}
			} else if (song.canPlay()) {
				plays = 1;
				lastPlayed = LocalDate.now();
				secondsRemaining -= song.getLength();
				list.queueUpNextSong(song);
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return username;
	}
}
