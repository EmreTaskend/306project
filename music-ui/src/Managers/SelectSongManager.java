package Managers;

import com.raven.model.Model_Music;

public class SelectSongManager {
    private static SelectSongManager instance;
    public Model_Music cur_Song;

    public String ind = "";

    public String src;

    public static SelectSongManager getInstance()  {
        if (instance == null) {
            instance = new SelectSongManager();
        }
        return instance;
    }

    public Model_Music getCur_Song() {
        return cur_Song;
    }

    public void setCur_Song(Model_Music cur_Song) {
        this.cur_Song = cur_Song;
    }
}
