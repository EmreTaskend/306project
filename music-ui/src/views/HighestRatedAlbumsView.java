package views;

import com.raven.form.Form_Albums;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HighestRatedAlbumsView extends JFrame implements Window {

    @Override
    public void createAndShowUI() throws SQLException {
        setSize(1000, 450); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        Form_Albums f = new Form_Albums();
        add(f, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
