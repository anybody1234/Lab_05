package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

public class Cart {
    private ObservableList<Media> itemsOrdered =  FXCollections.observableArrayList();

    public void addMedia(Media item) {
        if(itemsOrdered.contains(item)) {
            System.out.println(item.getTitle() + " is already in the cart");
        } else {
            itemsOrdered.add(item);
            System.out.println("Added " + item.getTitle() + " to cart");
        }
    }

    public void removeMedia(Media item) {
        if(itemsOrdered.contains(item)) {
            itemsOrdered.remove(item);
            System.out.println("Removed " + item.getTitle() + " from cart");
        } else {
            System.out.println(item.getTitle() + "is not in cart");
        }
    }

    public float totalCost() {
        float result = 0f;
        for(Media item : itemsOrdered) {
            result += item.getCost();
        }
        return result;
    }

    public void searchById(int id) {
        if(id < 0 || id >= itemsOrdered.size()) {
            System.out.println("Invalid id");
            return;
        } else {
            System.out.println("Disc found: " + itemsOrdered.get(id).getTitle() 
            + " - " + itemsOrdered.get(id).getCategory() 
            + " - " + itemsOrdered.get(id).getCost() + "$\n");
        }
    }

    public void searchByTitle(String title) {
        for(Media item : itemsOrdered) {
            if(item.getTitle().equals(title)) {
                System.out.println("Disc found: " + item.getTitle() 
                + " - " + item.getCategory() 
                + " - " + item.getCost() + "$\n");
                return;
            }
        }
        System.out.println("Disc not found\n");
    }

    public void displayCart() {
        System.out.println("***********************CART***********************");
        for(Media item : itemsOrdered) {
            System.out.println(item.toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("**************************************************");
    }

    public Media findMedia(String title) {
        for(Media item : itemsOrdered) {
            if(item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }

    public void emptyCart() {
        itemsOrdered.removeAll(itemsOrdered);
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }
    public ObservableList<Media> getItemsOrdered(){
		return this.itemsOrdered;
	} 
	public ObservableList<Media> filterMedia(String filterText,String filterType){
		ObservableList<Media> filteredList = FXCollections.observableArrayList();
		for(Media media : itemsOrdered){
			if(filterType.equalsIgnoreCase("Title")&& media.getTitle().toLowerCase().contains(filterText.toLowerCase())){
				filteredList.add(media);
			}
			else if(filterType.equalsIgnoreCase("ID")){
				try{
					int filterId=Integer.parseInt(filterText);
					if(media.getId()==filterId){
						filteredList.add(media);
					}
				}
				catch(NumberFormatException e){
					System.out.println("Invalid ID format"+filterText);
				}
			}
		}
		return filteredList;
	}
}