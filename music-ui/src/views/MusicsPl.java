package views;

import Managers.DatabaseController;
import Managers.MenuListsController;
import com.raven.component.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MusicsPl extends JFrame implements Window {
    public Music m;

    @Override
    public void createAndShowUI() {
        MenuListsController.getInstance().list1.clearSelection();
        if(m != null){
            resetUI();
        }
        System.out.println("aasdasd" + DatabaseController.getChoosedPlaylist());
        m = new Music(DatabaseController.getInstance().fetchPlaylistSongs(), DatabaseController.getChoosedPlaylist());
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
