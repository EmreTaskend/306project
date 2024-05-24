package Managers.relClasses;

import javax.swing.*;

public class HighestRatedAlbum {
    public String ArtistName;
    public String AlbumName;
    public double rating;

    public String Image;

    public HighestRatedAlbum(String artistName, String albumName, double rating, String image) {
        ArtistName = artistName;
        AlbumName = albumName;
        this.rating = rating;
        this.Image = image;
    }
}
