package Managers;

import com.raven.model.Model_Menu;

public class MenuListsController {
    public com.raven.component.ListMenu<String> list1;
    public com.raven.component.ListMenu<String> list2;

    public String owner;

    public String plname;
    private static MenuListsController instance;

    public static MenuListsController getInstance()  {
        if (instance == null) {
            instance = new MenuListsController();
        }
        return instance;
    }

    public MenuListsController() {
        list1 = new com.raven.component.ListMenu<>();
        list2 = new com.raven.component.ListMenu<>();
        list1.addItem(new Model_Menu("Playlist", "playlist"));
        list1.addItem(new Model_Menu("Artists", "artists"));
        list1.addItem(new Model_Menu("Albums", "albums"));
        //list1.addItem(new Model_Menu("Songs", "song"));
        list1.addItem(new Model_Menu("Liked", "love"));

        list2.addItem(new Model_Menu("Radio", "radio"));
        //list2.addItem(new Model_Menu("For You", "love"));
        list2.addItem(new Model_Menu("Browse", "browse"));
    }
}
