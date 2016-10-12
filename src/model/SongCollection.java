package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 * Class to store all the songs available in a Jukebox and allow them to be
 * displayed in a JTable.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class SongCollection implements TableModel, Serializable {
	protected List<Song> songList;

	public SongCollection() {
		songList = new ArrayList<Song>();
	}

	public void add(Song song) {
		songList.add(song);
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// No modification neccesary.
	}

	@Override
	public Class<?> getColumnClass(int column) {
		if (column == 2) {
			return Integer.class;
		}
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Artist";
		case 1:
			return "Title";
		case 2:
			return "Seconds";
		default:
			return null;
		}
	}

	@Override
	public int getRowCount() {
		return songList.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column) {
		case 0:
			return songList.get(row).getArtist();
		case 1:
			return songList.get(row).getTitle();
		case 2:
			return songList.get(row).getLength();
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener listener) {
		// No modification neccesary.
	}

	@Override
	public void setValueAt(Object value, int row, int column) {
		// No modification neccesary.
	}

	public Song getSongAt(int index) {
		return songList.get(index);
	}

}
