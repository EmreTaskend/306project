package views;

import Managers.ListMusicController;
import Managers.MenuListsController;
import com.raven.component.ListProfile;
import com.raven.component.Profile;
import com.raven.component.ProfileBestPlaylists;
import com.raven.component.ProfileCompatible;
import com.raven.model.Model_Profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class ForYouView extends JFrame implements Window {
    @Override
    public void createAndShowUI() throws SQLException {
        MenuListsController.getInstance().list2.clearSelection();
        MenuListsController.getInstance().list2.clearSelection();
        setTitle("For You View"); // Optional: Set the window title
        setSize(900, 600); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Ensure the application exits when the window is closed

        // Set layout to GridLayout with 1 row and 2 columns
        setLayout(new GridLayout(1, 2, 10, 10)); // The last two parameters are horizontal and vertical gaps

        ProfileCompatible m1 = new ProfileCompatible();
        ProfileBestPlaylists m2 = new ProfileBestPlaylists();

        // Add the profiles to the frame
        add(m1);
        add(m2);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MenuListsController.getInstance().list2.clearSelection();
                ListMusicController.getInstance().x = false;
                ListMusicController.getInstance().listBestPLs = new ListProfile<>();
                ListMusicController.getInstance().listCompatible = new ListProfile<>();
                dispose();
            }
        });

        // Make the frame visible
        this.setVisible(true);

    }
}
