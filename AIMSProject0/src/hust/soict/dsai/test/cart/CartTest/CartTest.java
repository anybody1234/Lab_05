package hust.soict.dsai.test.cart.CartTest;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
public class CartTest{
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19);
        cart.addMedia(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24);
        cart.addMedia(dvd2);
        cart.displayCart();
        System.out.println("\nSearching by ID...");
        System.out.println("\nSearching by Title...");
        cart.searchByTitle("Star Wars");
        cart.searchByTitle("Avatar"); 
    }
}
