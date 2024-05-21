package Managers;

import com.raven.model.Model_Popular;

public class ArtistController {
    public Model_Popular selectedArtist;

    public String selectedAlbum;
    private static ArtistController instance;

    public static ArtistController getInstance()  {
        if (instance == null) {
            instance = new ArtistController();
        }
        return instance;
    }

}
