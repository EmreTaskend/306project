package com.raven.form;

import Managers.DatabaseController;
import Managers.relClasses.HighestRatedAlbum;
import com.raven.model.Model_Popular;

import javax.swing.ImageIcon;
import java.sql.SQLException;
import java.util.ArrayList;

public class Form_Albums extends javax.swing.JPanel {

    public Form_Albums() throws SQLException {
        initComponents();
        init();
    }

    private void init() throws SQLException {
        ArrayList<HighestRatedAlbum> hl = DatabaseController.getInstance().fetchHighestRatedAlbums();
        ArrayList<String> topsongs = new ArrayList<>();
        for(int i = 0;i <hl.size();i++){
            topsongs.add(DatabaseController.getInstance().fetchTopSongOfAlbum(hl.get(i).ArtistName,hl.get(i).AlbumName));
            System.out.println(hl.get(i).ArtistName + " " + hl.get(i).AlbumName);
            System.out.println(DatabaseController.getInstance().fetchTopSongOfAlbum(hl.get(i).ArtistName,hl.get(i).AlbumName));
        }
        mostPopular.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/com/raven/icon/test/kendricLamar.jpg")), hl.get(0).ArtistName + " "+String.format("%.1f", hl.get(0).rating), hl.get(0).AlbumName + " | Top Song: " + topsongs.get(0)));
        mostPopular.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/com/raven/icon/test/beyonce.jpg")), hl.get(1).ArtistName + " "+String.format("%.1f", hl.get(1).rating), hl.get(1).AlbumName + " | Top Song: " + topsongs.get(1)));
        mostPopular.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/com/raven/icon/test/taylorswift.jpg")), hl.get(2).ArtistName + " "+String.format("%.1f", hl.get(2).rating), hl.get(2).AlbumName + " | Top Song: " + topsongs.get(2)));
        mostPopular.addImage(new Model_Popular(new ImageIcon(getClass().getResource("/com/raven/icon/test/taylorswift.jpg")), hl.get(3).ArtistName + " "+String.format("%.1f", hl.get(3).rating), hl.get(3).AlbumName + " | Top Song: " + topsongs.get(3)));
        }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mostPopular = new com.raven.component.MostPopular();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(169, 29, 177));
        jLabel1.setText("Albums");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(80, 80, 80));
        jLabel2.setText("Best Rated");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1)
                                        .addComponent(mostPopular, javax.swing.GroupLayout.PREFERRED_SIZE, 950, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mostPopular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.raven.component.MostPopular mostPopular;
    // End of variables declaration//GEN-END:variables
}
