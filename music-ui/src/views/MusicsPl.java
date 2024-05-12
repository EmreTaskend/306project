package views;

import com.raven.component.Music;

import javax.swing.*;
import java.awt.*;

public class MusicsPl extends JFrame implements Window {

    public Music m = new Music();
    @Override
    public void createAndShowUI() {
        setSize(600, 400); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(241, 241, 241)); // Set background color
        add(m, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
