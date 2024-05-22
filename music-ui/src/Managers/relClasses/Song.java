package Managers.relClasses;

import java.sql.Time;

public class Song {
    public String name;
    public String artist;
    public double rating;
    public Boolean is_trending;
    public String Album;
    public boolean liked = false;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Song(String name, String artist, double rating, Boolean is_trending) {
        this.name = name;
        this.artist = artist;
        this.rating = rating;
        this.is_trending = is_trending;
        this.Album = "asdasd";
    }
}
