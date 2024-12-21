package hust.soict.dsai.aims.media;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hust.soict.dsai.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {
	private static int nbDigitalVideoDiscs = 0;

	public DigitalVideoDisc(int id, String title, String category, float cost, String director, int length) {
		super(id, title, category, cost, director, length);
		nbDigitalVideoDiscs++;
	}

	public DigitalVideoDisc(int id, String title, String category, float cost) {
		super(id, title, category, cost);
		nbDigitalVideoDiscs++;
	}


	public DigitalVideoDisc(String title, String category, String director, float cost, int length) {
		super(title, category, director, cost, length);
        nbDigitalVideoDiscs++;
	}

	public DigitalVideoDisc(String category) {
		super(category);
		nbDigitalVideoDiscs++;
	}

	public void play1() throws PlayerException {
	    if (this.getLength() <= 0) {
	        throw new PlayerException("ERROR: The duration of the DVD must be positive!");
	    } else {
	        System.out.println("Currently playing: " + this.getTitle());
	        System.out.println("Duration: " + this.getLength() + " minutes");

	        JDialog mediaWindow = new JDialog();
	        JPanel infoPanel = new JPanel();
	        JLabel titleLabel = new JLabel("Playing: " + this.getTitle());
	        JLabel durationLabel = new JLabel("Duration: " + this.getLength() + " minutes");
	        JButton closeButton = new JButton("Close");

	        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
	        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        durationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

	        closeButton.addActionListener(e -> mediaWindow.dispose());

	        infoPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
	        infoPanel.add(titleLabel);
	        infoPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
	        infoPanel.add(durationLabel);
	        infoPanel.add(Box.createRigidArea(new Dimension(0, 15)));
	        infoPanel.add(closeButton);
	        infoPanel.add(Box.createRigidArea(new Dimension(0, 10))); 

	        mediaWindow.setTitle("DVD Player");
	        mediaWindow.setSize(300, 150); 
	        mediaWindow.setLocationRelativeTo(null);
	        mediaWindow.add(infoPanel);
	        mediaWindow.setModal(true);
	        mediaWindow.setVisible(true);
	    }
	}

	
	public void play() {
		System.out.println("Playing DVD: " + this.getTitle());
		System.out.println("DVD length: " + this.getLength());
	}
}