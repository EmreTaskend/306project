package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsView extends JFrame implements Window {

    private List<String> playlistNames; // Your list of playlist names
    private JPanel playlistPanel;
    public void createAndShowUI() {
        // Initialize the playlist names (replace this with your actual list of playlist names)
        playlistNames = new ArrayList<>();
        playlistNames.add("Playlist 1");
        playlistNames.add("Playlist 2");
        playlistNames.add("Playlist 3");
        playlistNames.add("Playlist 4");
        playlistNames.add("Playlist 5");
        playlistNames.add("Playlist 6");
        playlistNames.add("Playlist 7");
        playlistNames.add("Playlist 8");
        playlistNames.add("Playlist 9");
        playlistNames.add("Playlist 10");
        playlistNames.add("Playlist 11");
        playlistNames.add("Playlist 12");
        playlistNames.add("Playlist 13");
        playlistNames.add("Playlist 14");
        playlistNames.add("Playlist 15");

        // Create and configure the JFrame
        setTitle("My Playlists");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(241, 241, 241)); // Set background color

        // Create the top panel with search components
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setBackground(new Color(52, 73, 134)); // Dark blue background
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(41, 128, 185)); // Blue button color
        searchButton.setForeground(Color.BLACK); // White text color
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // Create the playlist panel to display playlist buttons
        playlistPanel = new JPanel();
        playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.Y_AXIS)); // Vertical box layout
        playlistPanel.setBackground(new Color(241, 241, 241)); // Light gray background

        for (String playlist : playlistNames) {
            JButton playlistButton = new JButton(playlist);
            playlistButton.setBackground(new Color(46, 204, 113)); // Green button color
            playlistButton.setForeground(Color.BLACK); // White text color
            playlistButton.setFocusPainted(false); // Remove focus border
            playlistButton.setBorderPainted(false); // Remove border
            playlistButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
            playlistButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Full width, fixed height
            playlistButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WindowManager.getInstance().showWindow(Windows.MusicsPl);
                    JOptionPane.showMessageDialog(null, "Clicked playlist: " + playlist);
                }
            });
            playlistPanel.add(playlistButton);
        }

        // Create a scroll pane for the playlist panel
        JScrollPane scrollPane = new JScrollPane(playlistPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar

        // Add the panels to the frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Use a scroll pane for playlist panel

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                // Perform search based on searchText
                JOptionPane.showMessageDialog(null, "Search for: " + searchText);
            }
        });
        this.setVisible(true);
    }


}

