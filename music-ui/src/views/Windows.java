package views;

import Managers.LogInController;

public enum Windows{
    MainView(new MainView()),
    LogIn(new LogInView(LogInController.getInstance())),
    Signup(new SignUpView()),
    Playlists(new PlaylistsView()),
    MusicsPl(new MusicsPl());
    final Window window;

    Windows(Window window){
        this.window = window;
    }
}

