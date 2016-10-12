package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.Jukebox;

/**
 * A GUI for the Jukebox, only displays the available songs currently.
 * 
 * @author George Barnum Lu Ye
 *
 */
public class JukeboxGUI extends JFrame {

	public static final String SAVE_LOCATION = "savefile";

	public static void main(String[] args) {
		new JukeboxGUI().setVisible(true);
	}

	private JLabel loginPrompt = new JLabel(
			"Enter Username and Password to Log In");
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private JTextField usernameField = new JTextField(15);
	private JTextField passwordField = new JTextField(15);
	private JButton loginButton = new JButton("Login");

	private JLabel accountLabel = new JLabel();
	private JLabel playsRemainingLabel = new JLabel();
	private JLabel timeRemainingLabel = new JLabel();
	private JButton logoutButton = new JButton("Log Out");

	private JLabel prompt = new JLabel("Select a Song from this Jukebox");
	private JLabel listLabel = new JLabel("Queued Songs:");
	private JTable songTable = new JTable();
	private JButton playButton = new JButton("Play Selected Song");
	private Jukebox jukebox;
	private JTable playList = new JTable();
	private JPanel playPanel = new JPanel();
	private JPanel selectPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel usernamePanel = new JPanel();
	private JPanel accountPanel = new JPanel();
	private JPanel passwordPanel = new JPanel();

	public JukeboxGUI() {
		if (JOptionPane.showConfirmDialog(null,
				"Do you want to load saved data?", null,
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			try {
				FileInputStream fis = new FileInputStream(SAVE_LOCATION);
				ObjectInputStream ois = new ObjectInputStream(fis);
				jukebox = (Jukebox) ois.readObject();
				ois.close();
				fis.close();
			} catch (Exception e) {
				jukebox = new Jukebox();
			}
		} else {
			jukebox = new Jukebox();
		}
		jukebox.getPlayList().forcePlay();
		layoutGUI();
		registerListeners();
	}

	private void layoutGUI() {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLayout(new GridLayout(1, 3));

		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameField);
		passwordPanel.add(passwordLabel);
		passwordPanel .add(passwordField);
		
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		loginPanel.add(loginPrompt);
		loginPanel.add(usernamePanel);
		loginPanel.add(passwordPanel);
		loginPanel.add(loginButton);

		accountPanel.add(accountLabel);
		accountPanel.add(playsRemainingLabel);
		accountPanel.add(timeRemainingLabel);
		accountPanel.add(logoutButton);

		selectPanel.setLayout(new BorderLayout());
		selectPanel.add(prompt, BorderLayout.NORTH);
		songTable.setModel(jukebox.getSongCollection());
		songTable.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(songTable);
		selectPanel.add(scrollPane, BorderLayout.CENTER);
		playButton.setEnabled(false);
		selectPanel.add(playButton, BorderLayout.SOUTH);

		playPanel.setLayout(new BorderLayout());
		playPanel.add(listLabel, BorderLayout.NORTH);
		jukebox.getPlayList().setDisplay(playList);
		playList.setModel(jukebox.getPlayList());
		playPanel.add(playList, BorderLayout.CENTER);

		this.add(loginPanel);
		this.add(selectPanel);
		this.add(playPanel);
		this.pack();
	}

	private void registerListeners() {
		playButton.addActionListener(new SelectedListener());
		loginButton.addActionListener(new LoginListener());
		logoutButton.addActionListener(new LogoutListener());
		this.addWindowListener(new CloseListener());
	}

	private void updateAccountInfo() {
		accountLabel.setText("Hello, " + jukebox.getActiveAccount().getName());
		playsRemainingLabel.setText("You have "
				+ Integer.toString(jukebox.getActiveAccount()
						.getRemainingPlays()) + " plays remaining.");
		timeRemainingLabel.setText("You have "
				+ Integer.toString(jukebox.getActiveAccount()
						.getRemainingTime()) + " seconds remaining.");
	}

	private class SelectedListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!jukebox.selectSong(songTable.getRowSorter()
					.convertRowIndexToModel(songTable.getSelectedRow()))) {

				if (!jukebox.getActiveAccount().hasTimeForSong(
						jukebox.getSongCollection().getSongAt(
								songTable.getRowSorter()
										.convertRowIndexToModel(
												songTable.getSelectedRow())))) {

					JOptionPane.showMessageDialog(null,
							"You don't have enough time remaining.",
							"Play Failed", JOptionPane.ERROR_MESSAGE);
				} else if (!jukebox.getActiveAccount().hasPlays()) {
					JOptionPane
							.showMessageDialog(
									null,
									"You have no more plays today. Try again tomorrow.",
									"Play Failed", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"This song has been played too many times today. Try again tomorrow.",
									"Play Failed", JOptionPane.ERROR_MESSAGE);
				}
			}
			updateAccountInfo();
			repaint();
		}
	}

	private class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jukebox.login(usernameField.getText(), passwordField.getText());
			usernameField.setText("");
			passwordField.setText("");

			if (jukebox.getActiveAccount() == null) {
				JOptionPane.showMessageDialog(null, "No such account",
						"Login Failed", JOptionPane.ERROR_MESSAGE);
			} else {
				updateAccountInfo();
				playButton.setEnabled(true);
				remove(loginPanel);
				add(accountPanel, 0);
				revalidate();
				repaint();
			}
		}

	}

	private class LogoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			jukebox.logout();
			playButton.setEnabled(false);
			remove(accountPanel);
			add(loginPanel, 0);
			revalidate();
			repaint();
		}

	}

	private class CloseListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			switch (JOptionPane.showConfirmDialog(null,
					"Do you want to save before exiting?", null,
					JOptionPane.YES_NO_CANCEL_OPTION)) {
			case JOptionPane.YES_OPTION:
				try {
					FileOutputStream fos = new FileOutputStream(SAVE_LOCATION);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(jukebox);
					fos.close();
					oos.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			case JOptionPane.NO_OPTION:
				setVisible(false);
				dispose();
				System.exit(0);
			}

		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
