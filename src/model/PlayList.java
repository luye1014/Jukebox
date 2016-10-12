package model;

import javax.swing.JTable;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

/**
 * Class to handle the queuing and sequential playing of Song classes.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class PlayList extends SongCollection {
	private boolean canPlay;
	private JTable table = null;

	public PlayList() {
		canPlay = true;
	}

	public void setDisplay(JTable table) {
		this.table = table;
	}

	public void queueUpNextSong(Song song) {
		if (song.canPlay()) {
			song.play();
			songList.add(song);
			playNext();
		}
	}

	public void forcePlay() {
		canPlay = true;
		playNext();
	}

	private void playNext() {
		if (canPlay && !songList.isEmpty()) {
			canPlay = false;
			SongPlayer.playFile(new PlayNextListener(), songList.get(0)
					.getLocation());
		}
		if (table != null) {
			table.updateUI();
		}
	}

	private class PlayNextListener implements EndOfSongListener {

		@Override
		public void songFinishedPlaying(
				EndOfSongEvent eventWithFileNameAndDateFinished) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			canPlay = true;
			songList.remove(0);
			playNext();
		}

	}

}
