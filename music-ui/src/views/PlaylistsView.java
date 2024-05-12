package views;

import Managers.DatabaseController;

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

        // Create and configure the JFrame
        setTitle("My Playlists");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
            createPlaylistButton(playlist); // Create playlist buttons
        }

        // Create a scroll pane for the playlist panel
        JScrollPane scrollPane = new JScrollPane(playlistPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scroll bar

        // Create a button to add new playlist
        JButton addButton = new JButton("Create New Playlist");
        addButton.setBackground(Color.GREEN); // Set background color to green
        addButton.setForeground(Color.GREEN); // White text color
        addButton.setFocusPainted(false); // Remove focus border
        addButton.setBorderPainted(false); // Remove border
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        addButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Full width, fixed height
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when new playlist button is clicked
                String newPlaylistName = JOptionPane.showInputDialog(null, "Enter new playlist name:");
                if (newPlaylistName != null && !newPlaylistName.isEmpty()) {
                    createPlaylistButton(newPlaylistName);
                    playlistNames.add(newPlaylistName);
                    // Perform any additional actions like adding to database, etc.
                }
            }
        });

        // Add the panels and button to the frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER); // Use a scroll pane for playlist panel
        add(addButton, BorderLayout.SOUTH); // Add the new playlist button at the bottom

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

    private void createPlaylistButton(String playlistName) {
        JButton playlistButton = new JButton(playlistName);
        playlistButton.setBackground(new Color(46, 204, 113)); // Green button color
        playlistButton.setForeground(Color.BLACK); // White text color
        playlistButton.setFocusPainted(false); // Remove focus border
        playlistButton.setBorderPainted(false); // Remove border
        playlistButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        playlistButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Full width, fixed height
        playlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseController.setChoosedPlaylist(playlistName);
                System.out.println(DatabaseController.getChoosedPlaylist());
                WindowManager.getInstance().showWindow(Windows.MusicsPl);
            }
        });
        playlistPanel.add(playlistButton);
    }
}
