package views;

import Managers.DatabaseController;
import Managers.MenuListsController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsView extends JFrame implements Window {

    private List<String> playlistNames; // Your list of playlist names
    private JPanel playlistPanel;

    public void createAndShowUI() {
        // Initialize the playlist names (replace this with your actual list of playlist names)
        playlistNames = new ArrayList<>();
        playlistNames.addAll(DatabaseController.getInstance().fetchUserPlaylists());
        MenuListsController.getInstance().list1.clearSelection();

        // Create and configure the JFrame
        setTitle("My Playlists");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 380); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen

        // Set a custom content pane to draw the gradient background
        JPanel contentPane = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(62, 83, 134);
                Color color2 = new Color(146, 78, 170);
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        setContentPane(contentPane);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MenuListsController.getInstance().list1.clearSelection();
                dispose();
            }
        });

        // Create the top panel with search components
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.setOpaque(false); // Make panel transparent to show gradient background

        JTextField searchField = new JTextField(20);
        searchField.setBorder(BorderFactory.createLineBorder(new Color(52, 73, 134), 1));
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(41, 128, 185)); // Blue button color
        searchButton.setForeground(Color.BLACK); // White text color
        searchButton.setFocusPainted(false);

        topPanel.add(searchField);
        topPanel.add(searchButton);

        // Create the playlist panel to display playlist buttons
        playlistPanel = new JPanel();
        playlistPanel.setLayout(new BoxLayout(playlistPanel, BoxLayout.Y_AXIS)); // Vertical box layout
        playlistPanel.setOpaque(false); // Make panel transparent to show gradient background

        for (String playlist : playlistNames) {
            createPlaylistButton(playlist); // Create playlist buttons
        }

        // Create a scroll pane for the playlist panel
        JScrollPane scrollPane = new JScrollPane(playlistPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Always show vertical scroll bar
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        // Create a button to add new playlist
        JButton addButton = new JButton("Create New Playlist");
        addButton.setBackground(new Color(46, 204, 113)); // Set background color to green
        addButton.setForeground(Color.WHITE); // White text color
        addButton.setFocusPainted(false); // Remove focus border
        addButton.setBorderPainted(false); // Remove border
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        addButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Full width, fixed height
        addButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform action when new playlist button is clicked
                String newPlaylistName = JOptionPane.showInputDialog(null, "Enter new playlist name:");
                if (newPlaylistName != null && !newPlaylistName.isEmpty()) {
                    createPlaylistButton(newPlaylistName);
                    playlistNames.add(newPlaylistName);
                    DatabaseController.getInstance().createPlaylist(newPlaylistName);
                    dispose();
                    createAndShowUI();
                }
            }
        });

        // Add the panels and button to the frame
        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER); // Use a scroll pane for playlist panel
        contentPane.add(addButton, BorderLayout.SOUTH); // Add the new playlist button at the bottom

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
        playlistButton.setForeground(Color.WHITE); // White text color
        playlistButton.setFocusPainted(false); // Remove focus border
        playlistButton.setBorderPainted(false); // Remove border
        playlistButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        playlistButton.setMaximumSize(new Dimension(Short.MAX_VALUE, 40)); // Full width, fixed height
        playlistButton.setBorder(new EmptyBorder(10, 10, 10, 10));
        playlistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseController.setChoosedPlaylist(playlistName);
                System.out.println(DatabaseController.getChoosedPlaylist());
                WindowManager.getInstance().showWindow(Windows.MusicsPl);
            }
        });
        playlistPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Add some space between buttons
        playlistPanel.add(playlistButton);
    }
}
