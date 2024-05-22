package views;

import Managers.DatabaseController;
import Managers.SelectSongManager;
import com.raven.component.Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class searchView extends JFrame implements Window {
    private JTextField searchField;
    private JButton searchButton;

    @Override
    public void createAndShowUI() throws SQLException {
        setTitle("Search Songs");
        setSize(600, 400); // Set initial window size
        setResizable(false); // Make the frame not resizable
        setLocationRelativeTo(null); // Center the frame on the screen
        setLayout(new BorderLayout());

        // Create the search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());

        // Create the search field and button
        searchField = new JTextField();
        searchButton = new JButton("Search");

        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performSearch();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle exception appropriately
                }
            }
        });

        // Add search field and button to the search panel
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        // Add the search panel to the top of the frame
        add(searchPanel, BorderLayout.NORTH);

        // Initial display of search results
        displaySearchResults(SelectSongManager.getInstance().src);

        this.setVisible(true);
    }

    private void performSearch() throws SQLException {
        String searchQuery = searchField.getText();
        SelectSongManager.getInstance().src = searchQuery;
        displaySearchResults(searchQuery);
    }

    private void displaySearchResults(String searchQuery) throws SQLException {
        // Remove existing components
        getContentPane().removeAll();

        // Re-add the search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        add(searchPanel, BorderLayout.NORTH);

        // Fetch and display the search results
        Music m = new Music(DatabaseController.getInstance().fetchSearched(searchQuery), "Search Results");
        add(m, BorderLayout.CENTER);

        // Refresh the frame
        revalidate();
        repaint();
    }
}
