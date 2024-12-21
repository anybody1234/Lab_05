package hust.soict.dsai.aims.media;

import java.util.*;

public abstract class Media {
    private int id;
    private String title;
    private String category;
    private float cost;
    private String director;
    private float length;
    private String artist;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
        
    public Media(int id, String title, String category, float cost) {
        this.setId(id);
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
    }

    public Media(String title){
        this.setTitle(title);
    }

    public Media(String title, String category, float cost){
        this(title);
        this.setCategory(category);
        this.setCost(cost);
    }
    
    public Media(String title, String category,String director, float cost, float length) {
    	this(title);
        this.setCategory(category);
        this.setDirector(director);
        this.setCost(cost);
        this.setLength(length);
    }
    
    public Media(String title, String category,String director, float cost, float length, String artist) {
    	this(title);
        this.setCategory(category);
        this.setDirector(director);
        this.setCost(cost);
        this.setLength(length);
        this.setArtist(artist);
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    public float getLength() {
        return length;
    }
    public void setLength(float length) {
        this.length = length;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public float getCost() {
        return cost;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean equals(Media obj) {
        return this.getTitle().equals(obj.getTitle());
    }

    @Override
    public String toString() {
        return "Media - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost();
    }
}