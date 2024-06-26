package com.raven.component;

import Managers.ArtistController;
import Managers.DatabaseController;
import com.raven.model.Model_Popular;
import views.AlbumSongsView;
import views.ArtistView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MostPopular extends javax.swing.JLayeredPane {

    public MostPopular() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        scrollBar1 = new com.raven.swing.ScrollBar();

        sp.setBorder(null);
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        sp.setHorizontalScrollBar(scrollBar1);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        sp.setViewportView(panel);

        scrollBar1.setOrientation(javax.swing.JScrollBar.HORIZONTAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addImage(Model_Popular data) {
        ItemImage item = new ItemImage();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArtistController.getInstance().selectedArtist = data;
                if(DatabaseController.getInstance().fetchArtists().contains(ArtistController.getInstance().selectedArtist.getTitle())) {
                    ArtistView artistView = new ArtistView();
                    artistView.createAndShowUI();
                }else{
                    ArtistController.getInstance().selectedAlbum = data.getTitle();
                    String[] parts = data.getDescription().split("  ");
                    ArtistController.getInstance().selectedArtist = new Model_Popular(data.getImage(),parts[0],"asd");
                    AlbumSongsView a = new AlbumSongsView();
                    a.createAndShowUI();
                }
            }
        });
        panel.add(item);
        panel.repaint();
        panel.revalidate();
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.raven.swing.ScrollBar scrollBar1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
