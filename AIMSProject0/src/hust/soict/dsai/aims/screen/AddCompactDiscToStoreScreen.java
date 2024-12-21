package hust.soict.dsai.aims.screen;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.store.Store;

import java.awt.event.ActionEvent;

public class AddCompactDiscToStoreScreen  extends AddItemToStoreScreen{ 
    private JTextField artistField;
    private JTextField directorField;


    public AddCompactDiscToStoreScreen (Store store) {
        super(store);
       
        JLabel artistLabel = new JLabel("Artist:");
        artistField = new JTextField();
        JLabel directorLabel = new JLabel("Director:");
        directorField = new JTextField();
        

        

        JButton addButton = new JButton("Add DVD");
        addButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String category = categoryField.getText();
                float cost = Float.parseFloat(costField.getText());
                String director = directorField.getText();
                String artist = artistField.getText();
                
                store.addMedia(
                    new CompactDisc(title, category, director, cost, artist)
                );
                dispose();
                
            }
        });

        add(artistLabel);
        add(artistField);
        add(directorLabel);
        add(directorField);
        add(addButton);
    }

    
}