package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class to represent a song, storing the title, artist, length and location.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class Song implements Serializable {
	public static final int MAX_PLAYS = 3;

	private String title;
	private int length;
	private String artist;
	private String location;
	private int plays;
	private LocalDate lastPlayed;

	public Song(String name, int seconds, String artist, String location) {
		this.title = name;
		this.length = seconds;
		this.artist = artist;
		this.location = location;
		plays = 0;
		lastPlayed = null;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getLength() {
		return length;
	}

	public String getLocation() {
		return location;
	}

	public boolean canPlay() {
		if (LocalDate.now().equals(lastPlayed)) {
			return plays < MAX_PLAYS;
		} else {
			return true;
		}
	}

	public void play() {
		if (LocalDate.now().equals(lastPlayed)) {
			plays++;
		} else {
			plays = 1;
		}
		lastPlayed = LocalDate.now();
	}

	public String toString() {
		return getTitle() + " " + getArtist() + " " + getLength();
	}
}
