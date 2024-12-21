package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import hust.soict.dsai.aims.media.*;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    private boolean checkMedia(Media disc) {
        for(Media item : itemsInStore) {
            if(item.equals(disc)) {
                return true;
            }
        }
        return false;
    }

    public Media findMedia(String title) {
        for(Media item : itemsInStore) {
            if(item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void addMedia(Media disc) {
        if(checkMedia(disc)) {
            System.out.println("The disc " + disc.getTitle() + " is already in store!");
        } else {
            itemsInStore.add(disc);
            System.out.println("The disc " + disc.getTitle() + " has been added!");
        }
    }

    public void removeMedia(Media disc) {
        if(itemsInStore.remove(disc)) {
            System.out.println("The disc " + disc.getTitle() + " has been removed!");
        } else {
            System.out.println("Could not find " + disc.getTitle() + " in store!");
        }
    }
    public void displayStore() {
        System.out.println("***********************STORE***********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            System.out.println("Available DVDs in store:");
            for (int i = 0; i < itemsInStore.size(); i++) {
                System.out.println((i + 1) + ". " + itemsInStore.get(i).toString());
            }
        }
        System.out.println("***************************************************");
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("\n*********STORE*********\nItems in the store: \n");
        if(itemsInStore.isEmpty()) {
            string.append("Empty\n");
        } else {
            for(Media item : itemsInStore) {
                string.append(item.toString()).append("\n");
            }
        }
        string.append("************************\n");
        return string.toString();
    }
}