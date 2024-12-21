package hust.soict.dsai.aims.media;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import hust.soict.dsai.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {

    private String artist;
    private ArrayList<Track> tracks;

    public CompactDisc(int id, String title, String category, float cost, String artist, ArrayList<Track> tracks) {
        super(id, title, category, cost);
        this.tracks = tracks;
        this.artist = artist;
        this.setLength(getLength());
    }
    
    public CompactDisc(int id, String title, String category, float cost, String artist) {
        super(id, title, category, cost);
        this.artist = artist;
        this.setLength(getLength());
    }

    public CompactDisc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public CompactDisc(String title, String category, String director, float cost, String artist) {
		super(title, category, director, cost, artist);
	}

	public String getArtist() {
        return artist;
    }
	
	public void play1() throws PlayerException {
	    if (this.getLength() <= 0) {
	        throw new PlayerException("ERROR: CD length is non-positive!");
	    }

	    System.out.println("Now Playing: " + this.getTitle());

	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    JPanel panel = new JPanel();
	    JDialog dialog = new JDialog();
	    JLabel titleLabel = new JLabel("Playing: " + this.getTitle());
	    JTextArea trackList = new JTextArea();

	    StringBuilder trackInfo = new StringBuilder();
	    for (Track track : this.tracks) {
	        trackInfo.append("- ").append(track.getTitle())
	                 .append(" (").append(track.getLength()).append(" mins)\n");
	    }
	    trackList.setText(trackInfo.toString());
	    trackList.setEditable(false); 

	    panel.setLayout(new BorderLayout());
	    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(titleLabel, BorderLayout.NORTH);
	    panel.add(new JScrollPane(trackList), BorderLayout.CENTER);

	    dialog.setTitle("Media Player");
	    dialog.add(panel);
	    dialog.setSize(400, 300); 
	    int x = (screenSize.width - dialog.getWidth()) / 2;
	    int y = (screenSize.height - dialog.getHeight()) / 2;
	    dialog.setLocation(x, y);
	    dialog.setVisible(true);

	    for (Track track : this.tracks) {
	        track.play();
	    }
	}


    public void addTrack(Track song) {
        if (tracks.contains(song)) {
            System.out.println(song.getTitle() + " is already in the CD");
        } else {
            tracks.add(song);
            System.out.println("Track added");
        }
    }

    public void removeTrack(Track song) {
        if (tracks.contains(song)) {
            tracks.remove(song);
        } else {
            System.out.println(song.getTitle() + " is not in the CD");
        }
    }

    @Override
    public String toString() {
        return "CD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " - " + this.getDirector() + " - " + this.getLength() + " - " + this.getArtist();
    }

    public void play() {
        System.out.println("\nTitle: " + getTitle() + "\nArtist: " + getArtist());
        for (Track track : tracks) {
            track.play();
        }
    }
  }