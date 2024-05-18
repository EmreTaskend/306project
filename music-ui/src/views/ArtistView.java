package views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ArtistView extends JFrame implements Window {
    public ArtistView() {

    }

    @Override
    public void createAndShowUI() {
        // Set up the frame
        setTitle("Artist Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }
}
