package views;

import Managers.DatabaseController;
import Managers.SelectSongManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChoosePlaylistView extends JFrame implements Window {
    private JComboBox<String> playlistComboBox;
    private JButton saveButton;
    public List<String> playlists = new ArrayList<>();


    @Override
    public void createAndShowUI() {
        this.playlists = DatabaseController.getInstance().fetchUserPlaylists();
        setTitle("Choose Playlist");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        // Create and add the JComboBox
        playlistComboBox = new JComboBox<>(playlists.toArray(new String[0]));
        playlistComboBox.setBackground(Color.WHITE); // Set background color
        playlistComboBox.setForeground(Color.BLACK); // Set text color
        playlistComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        comboBoxPanel.add(playlistComboBox);
        add(comboBoxPanel, BorderLayout.CENTER);

        // Create and add the save button
        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(46, 204, 113)); // Set background color
        saveButton.setForeground(Color.BLACK); // Set text color
        saveButton.setFocusPainted(false); // Remove focus border
        saveButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set font
        saveButton.setPreferredSize(new Dimension(100, 40)); // Set button size
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listener to the save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlaylist = (String) playlistComboBox.getSelectedItem();
                if (selectedPlaylist != null) {
                    DatabaseController.getInstance().insertSongToPlaylist((String) playlistComboBox.getSelectedItem(), SelectSongManager.getInstance().cur_Song.getName());
                    JOptionPane.showMessageDialog(ChoosePlaylistView.this, "Saved Successfully!");
                    dispose();
                }
            }
        });

        this.setVisible(true);
    }

}
