package com.raven.component;

import Managers.ArtistController;
import Managers.DatabaseController;
import Managers.ListMusicController;
import Managers.MenuListsController;
import com.raven.model.Model_Popular;
import com.raven.model.Model_Profile;
import views.AlbumSongsView;
import views.OthersLikedMusicsView;
import views.OthersMusicsView;

import java.awt.Color;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ItemProfile extends JPanel {

    private final Model_Profile data;

    public ItemProfile(Model_Profile data) {
        this.data = data;
        initComponents();
        setOpaque(false);
        lbName.setText(data.getName());
        lbDescription.setText(data.getDescription());
        if (data.getImage() != null) {
            imageAvatar.setImage(data.getImage());
        }
    }




    public void setSelected(boolean selected) throws SQLException {
        if (selected) {
            setBackground(new Color(220, 220, 220)); // Light gray background for selection
            setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150))); // Darker gray border for selection

            // Set chosen playlist and liked name
            DatabaseController.choosedPlaylist = "Liked";
            DatabaseController.choosedLikedName = data.getName();
            // Perform actions based on profile data
            if (!DatabaseController.getInstance().fetchUsers(data.getName())) {
                ArtistController.getInstance().selectedArtist = new Model_Popular(data.getImage(), data.getName(), data.getDescription());
                ArtistController.getInstance().selectedAlbum = data.getDescription();
                ListMusicController.getInstance().listCompatible.clearSelection();
                new AlbumSongsView().createAndShowUI();
            } else if (!data.getDescription().contains("Rating:")) {
                MenuListsController.getInstance().owner = data.getName();
                MenuListsController.getInstance().plname = data.getDescription();
                ListMusicController.getInstance().listBestPLs.clearSelection();
                new OthersMusicsView().createAndShowUI();
            } else {
                new OthersLikedMusicsView().createAndShowUI();
            }

            // Clear selection to prevent multiple windows opening
            ListMusicController.getInstance().list.clearSelection();
        } else {
            setBackground(Color.WHITE); // Default background color
            setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Default border color
        }
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        lbDescription = new javax.swing.JLabel();
        imageAvatar = new com.raven.swing.ImageAvatar();

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(2, 1));

        lbName.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(51, 51, 51));
        lbName.setText("Name");
        jPanel1.add(lbName);

        lbDescription.setForeground(new java.awt.Color(115, 115, 115));
        lbDescription.setText("Description");
        jPanel1.add(lbDescription);

        imageAvatar.setBorderSize(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(imageAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ImageAvatar imageAvatar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbDescription;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
