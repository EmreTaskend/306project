package Managers;

import com.raven.component.ListProfile;
import com.raven.component.Music;

public class ListMusicController {
    private static ListMusicController instance;

    public int index = -1;

    public ListProfile<Object> list = new com.raven.component.ListProfile<>();
    public ListProfile<Object> listBestPLs = new com.raven.component.ListProfile<>();
    public ListProfile<Object> listCompatible = new com.raven.component.ListProfile<>();

    public boolean x = false;

    public Music music1;

    public static ListMusicController getInstance()  {
        if (instance == null) {
            instance = new ListMusicController();
        }
        return instance;
    }



}
