package com.raven.component;

import Managers.DatabaseController;
import Managers.ListMusicController;
import Managers.MenuListsController;
import com.raven.model.Model_Menu;
import views.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.Objects;

public class ItemMenu extends javax.swing.JPanel {

    public boolean isSelected() {
        return selected;
    }

    private final Model_Menu data;
    private boolean selected;



    public void setSelected(boolean selected) throws SQLException {
        this.selected = selected;
        if (selected) {
            lbText.setFont(new java.awt.Font("sansserif", 1, 14));
            lbText.setForeground(Color.WHITE);
            lbIcon.setIcon(data.toIconSelected());
            if(Objects.equals(data.getMenuName(), "Playlist")) {
                WindowManager.getInstance().showWindow(Windows.Playlists);
            }
            if(Objects.equals(data.getMenuName(), "Liked")) {
                DatabaseController.getInstance().choosedPlaylist = "Liked";
                WindowManager.getInstance().showWindow(Windows.MusicsPl);
                MenuListsController.getInstance().list1.clearSelection();
            }
            if(Objects.equals(data.getMenuName(), "Albums")){
                HighestRatedAlbumsView hv = new HighestRatedAlbumsView();
                hv.createAndShowUI();
                MenuListsController.getInstance().list1.clearSelection();
            }
            if(Objects.equals(data.getMenuName(), "Artists")){
                HighestRatedArtistsView hv = new HighestRatedArtistsView();
                hv.createAndShowUI();
                MenuListsController.getInstance().list1.clearSelection();
            }
            if(Objects.equals(data.getMenuName(), "Radio")){
                MenuListsController.getInstance().list2.clearSelection();
                if(!ListMusicController.getInstance().x) {
                    ForYouView f = new ForYouView();
                    f.createAndShowUI();
                    ListMusicController.getInstance().x = true;
                }
                MenuListsController.getInstance().list2.clearSelection();
            }
            if(Objects.equals(data.getMenuName(), "Browse")){
                searchView s = new searchView();
                s.createAndShowUI();
                MenuListsController.getInstance().list2.clearSelection();
            }
        } else {
            lbText.setFont(new java.awt.Font("sansserif", 0, 14));
            lbText.setForeground(new Color(204, 204, 204));
            lbIcon.setIcon(data.toIcon());
        }
    }

    public ItemMenu(Model_Menu data) {
        this.data = data;
        initComponents();
        setOpaque(false);
        lbIcon.setIcon(data.toIcon());
        lbText.setText(data.getMenuName());
        MenuListsController.getInstance().list2.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        lbText = new javax.swing.JLabel();

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/albums_selected.png"))); // NOI18N

        lbText.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lbText.setForeground(new java.awt.Color(255, 255, 255));
        lbText.setText("Item Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(lbText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (selected) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setColor(Color.WHITE);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fillRect(0, 0, 2, getHeight());
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbText;
    // End of variables declaration//GEN-END:variables
}
