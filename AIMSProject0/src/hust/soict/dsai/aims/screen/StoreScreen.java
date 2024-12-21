package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.cart.Cart;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Track;

public class StoreScreen extends JFrame{
	private Store store;
	private Cart cart;
    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
}
    JPanel createNorth(){
    	JPanel north = new JPanel();
    	north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
    	north.add(createMenuBar());
    	north.add(createHeader());
    	return north;
    }
    JMenuBar createMenuBar() {
    	JMenu menu = new JMenu("Options");
    	
    	JMenu smUpdateStore = new JMenu("Update Store");
    	smUpdateStore.add(new JMenuItem("Add Book"));
    	smUpdateStore.add(new JMenuItem("Add CD"));
    	smUpdateStore.add(new JMenuItem("Add DVD"));
    	
    	menu.add(smUpdateStore);
    	menu.add(new JMenuItem("View store"));
    	menu.add(new JMenuItem("View cart"));
    	
    	JMenuBar menuBar = new JMenuBar();
    	menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
    	menuBar.add(menu);
    	
    	return menuBar;
    }
    JPanel createHeader() {
    	JPanel header = new JPanel();
    	header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
    	
    	JLabel title = new JLabel("AIMS");
    	title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
    	title.setForeground(Color.CYAN);
    	
    	JButton cart = new JButton("View cart");
    	cart.setPreferredSize(new Dimension(100,50));
    	cart.setMaximumSize(new Dimension(100,50));
    	
    	header.add(Box.createRigidArea(new Dimension(10, 10)));
    	header.add(title);
    	header.add(Box.createHorizontalGlue());
    	header.add(cart);
    	header.add(Box.createRigidArea(new Dimension(10, 10)));
    	
    	return header;
    }
    JPanel createCenter() {
    	JPanel center = new JPanel();
    	center.setLayout(new GridLayout(3, 3, 2, 2));
    	
    	ArrayList<Media> mediaInStore = store.getItemsInStore();
    	for(int i=0;i<9;i++) {
    		MediaStore cell = new MediaStore(mediaInStore.get(i), this.cart);
    		center.add(cell);
    	}
    	
    	return center;
    }
    public static void main(String[] args) throws Exception {
        Store store = new Store();
        Cart cart = new Cart();
        
        CompactDisc cd = new CompactDisc(1, "Summer Vibes", "Pop", 18.5f);  
        CompactDisc cd1 = new CompactDisc(2, "The Rock Essentials", "Rock", 20.5f);  
        CompactDisc cd2 = new CompactDisc(5, "Jazz Nights", "Jazz", 22.0f);
        
        DigitalVideoDisc dvd = new DigitalVideoDisc(4, "Inception", "Sci-Fi", 250.0f);  
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(6, "Interstellar", "Sci-Fi", 220.0f);  
        
        Book book = new Book(3, "Clean Code", "Programming", 45.0f);  
        
        Track track1 = new Track("Good Vibes", 12);  
        Track track2 = new Track("Rock Anthem", 18);  
        Track track3 = new Track("Smooth Jazz", 15);  
        
        cd1.addTrack(track3);
        cd2.addTrack(track1);
        cd.addTrack(track2);
        
        store.addMedia(cd);
        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(dvd);
        store.addMedia(dvd1);
        store.addMedia(book);

        new StoreScreen(store, cart);
    }

    
}