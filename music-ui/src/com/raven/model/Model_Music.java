package com.raven.model;

import com.raven.component.ListMusic;

public class Model_Music {
    ListMusic lm = new ListMusic();
    public boolean already = false;

    public boolean isAlready() {
        return already;
    }

    public void setAlready(boolean already) {
        this.already = already;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Model_Music(String no, String name, String rating, String artist, String album, boolean is_trending) {
        this.no = no;
        this.name = name;
        this.rating = rating;
        Artist = artist;
        Album = album;
        this.is_trending = is_trending;
    }



    private String no;
    private String name;
    private String rating;
    private  String Artist;
    private  String Album;
    private  boolean is_trending;

    public boolean liked;

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean isIs_trending() {
        return is_trending;
    }

    public ListMusic getLm() {
        return lm;
    }

    public void setLm(ListMusic lm) {
        this.lm = lm;
    }

    public void setIs_trending(boolean is_trending) {
        this.is_trending = is_trending;
    }
}
