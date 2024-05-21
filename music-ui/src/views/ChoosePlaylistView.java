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

        // Create and add the JComboBox
        playlistComboBox = new JComboBox<>(playlists.toArray(new String[0]));
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        comboBoxPanel.add(playlistComboBox);
        add(comboBoxPanel, BorderLayout.CENTER);

        // Create and add the save button
        saveButton = new JButton("Save");
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
                    JOptionPane.showMessageDialog(ChoosePlaylistView.this, "Saved Succesfully!");
                    dispose();

                }
            }
        });
        this.setVisible(true);
    }

}
