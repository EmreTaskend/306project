package Managers.relClasses;

import javax.swing.*;

public class HighestRatedAlbum {
    public String ArtistName;
    public String AlbumName;
    public double rating;

    public HighestRatedAlbum(String artistName, String albumName, double rating) {
        ArtistName = artistName;
        AlbumName = albumName;
        this.rating = rating;
    }
}
