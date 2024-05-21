package views;

import com.raven.form.Form_Albums;
import com.raven.form.Form_RatedArtist;

import javax.swing.*;
import java.awt.*;

public class HighestRatedArtistsView extends JFrame implements Window {
    public void createAndShowUI() {
        setSize(1000, 450); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        Form_RatedArtist f = new Form_RatedArtist();
        add(f, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
