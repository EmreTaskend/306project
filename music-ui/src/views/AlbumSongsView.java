package views;

import Managers.ArtistController;
import Managers.DatabaseController;
import Managers.ListMusicController;
import Managers.MenuListsController;
import com.raven.component.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AlbumSongsView extends JFrame implements Window {
    public Music m;

    @Override
    public void createAndShowUI() {
        ListMusicController.getInstance().listCompatible.clearSelection();
        if(m != null){
            resetUI();
        }
        m = new Music(DatabaseController.getInstance().fetchAlbumSongs(ArtistController.getInstance().selectedArtist.getTitle(),ArtistController.getInstance().selectedAlbum), ArtistController.getInstance().selectedAlbum);
        setSize(600, 400); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        add(m, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MenuListsController.getInstance().list1.clearSelection();
                ListMusicController.getInstance().listBestPLs.clearSelection();
                ListMusicController.getInstance().listCompatible.clearSelection();
                DatabaseController.setChoosedPlaylist("");
                dispose();
            }
        });
    }

    public Music getM() {
        return m;
    }

    public void setM(Music m) {
        this.m = m;
    }

    public void resetUI() {
        MenuListsController.getInstance().list1.clearSelection();
        getContentPane().remove(m);

        // Fetch updated playlist data and create a new Music component
        m = new Music(DatabaseController.getInstance().fetchPlaylistSongs(), DatabaseController.getChoosedPlaylist());

        // Update the frame's content with the new Music component
        getContentPane().add(m, BorderLayout.CENTER);

    }
}
