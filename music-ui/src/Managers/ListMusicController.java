package Managers;

import com.raven.component.ListMusic;

import java.util.ArrayList;

public class ListMusicController {
    private static ListMusicController instance;

    public int index = -1;

    public com.raven.component.ListProfile list = new com.raven.component.ListProfile<>();

    public static ListMusicController getInstance()  {
        if (instance == null) {
            instance = new ListMusicController();
        }
        return instance;
    }

}
