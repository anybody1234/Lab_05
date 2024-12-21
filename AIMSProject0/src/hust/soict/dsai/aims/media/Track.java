package hust.soict.dsai.aims.media;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hust.soict.dsai.aims.exception.PlayerException;

public class Track {
    private String title;
    private float length;

    public void play1() throws PlayerException {
        if (this.getLength() <= 0) {
            throw new PlayerException("ERROR: Track length is non-positive!");
        } else {
            System.out.println("Playing track: " + this.getTitle());
            System.out.println("Track length: " + this.getLength() + " seconds");

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

            JPanel panel = new JPanel();
            JDialog dialog = new JDialog();
            JLabel titleLabel = new JLabel("Now playing: " + this.getTitle());
            JLabel lengthLabel = new JLabel("Track length: " + this.getLength() + " seconds");
            JButton closeButton = new JButton("OK");

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            lengthLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        
            closeButton.addActionListener(e -> dialog.dispose());

            panel.add(Box.createVerticalGlue());
            panel.add(titleLabel);
            panel.add(lengthLabel);
            panel.add(Box.createVerticalStrut(10));
            panel.add(closeButton);
            panel.add(Box.createVerticalGlue());

            dialog.setTitle("Media Player");
            dialog.add(panel);
            dialog.setSize(300, 150);
            int width = dialog.getSize().width;
            int height = dialog.getSize().height;
            int x = (dim.width - width) / 2;
            int y = (dim.height - height) / 2;
            dialog.setLocation(x, y);
            dialog.setModal(true);
            dialog.setVisible(true);
        }
    }

    
    public float getLength() {
        return length;
    }

    public String getTitle() {
        return title;
    }

    public Track(String title, float length) {
        this.title = title;
        this.length = length;
    }

    public void play() {
        System.out.println("Playing track: " + this.getTitle());
        System.out.println("Track length: " + this.getLength());
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Track track = (Track) obj;
        return title.equals(track.title) && length == track.length;
    }
}