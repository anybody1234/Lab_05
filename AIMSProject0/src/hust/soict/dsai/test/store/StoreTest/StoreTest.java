package hust.soict.dsai.test.store.StoreTest;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.store.Store;
public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19);
        store.addMedia(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24);
        store.addMedia(dvd2);
        store.displayStore();
        store.removeMedia(dvd2);
        store.displayStore();
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Avatar", "Sci-fi", "James Cameron", 162, 29);
        store.removeMedia(dvd4);
        store.removeMedia(null);
    }
}