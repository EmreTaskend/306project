package views;

import Managers.DatabaseController;
import com.raven.component.Music;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MusicsPl extends JFrame implements Window {
    public Music m;

    @Override
    public void createAndShowUI() {
        if(m != null){
            resetUI();
        }
        m = new Music(DatabaseController.getInstance().fetchPlaylist(), DatabaseController.getChoosedPlaylist());
        setSize(600, 400); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        add(m, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public Music getM() {
        return m;
    }

    public void setM(Music m) {
        this.m = m;
    }

    public void resetUI() {
        // Remove the current Music component from the frame
        getContentPane().remove(m);

        // Fetch updated playlist data and create a new Music component
        m = new Music(DatabaseController.getInstance().fetchPlaylist(), DatabaseController.getChoosedPlaylist());

        // Update the frame's content with the new Music component
        getContentPane().add(m, BorderLayout.CENTER);

        // Repaint and validate the frame to reflect changes
        revalidate();
        repaint();
    }
}
