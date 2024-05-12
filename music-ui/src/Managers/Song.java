package Managers;

import java.sql.Time;

public class Song {
    public String name;
    public String artist;
    public double rating;
    public Boolean is_trending;
    public Time duration;

    public Song(String name, String artist, double rating, Boolean is_trending, Time duration) {
        this.name = name;
        this.artist = artist;
        this.rating = rating;
        this.is_trending = is_trending;
        this.duration = duration;
    }
}
