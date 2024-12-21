package hust.soict.dsai.aims.media;

public class Disc extends Media {
    private String director;
    private float length;
	private String artist;

    public Disc(int id, String title, String category, float cost, String director, float length) {
        super(id, title, category, cost);
        this.director = director;
        this.length = length;
    }

    public Disc(int id, String title, String category, float cost) {
        super(id, title, category, cost);
    }

    public Disc(String title, String category, String director, float cost, float length) {
    	super(title, category, cost);
        this.director = director;
        this.length = length;
	}

	public Disc(String title, String category, String director, float cost, String artist) {
		super(title, category, cost);
        this.director = director;
        this.artist= artist;
	}

	public Disc(String category) {
		super(category);
	}

	public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Disc - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getCost() + " - " + this.getDirector() + " - " + this.getLength();
    }
}