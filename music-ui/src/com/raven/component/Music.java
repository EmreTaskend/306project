package com.raven.component;

import Managers.Song;
import com.raven.model.Model_Music;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Music extends javax.swing.JPanel {
    public ArrayList<Song> songlist;
    public String name;
    public Music(ArrayList<Song> sl, String name) {
        songlist = sl;
        this.name = name;
        initComponents();
        init();
    }

    private void init() {
        System.out.println(list.model.elements());
        // Create a JScrollPane and add the ListMusic component to it
        JScrollPane scrollPane = new JScrollPane(list);

        // Set the preferred size of the JScrollPane
        scrollPane.setPreferredSize(new Dimension(285, 320));

        // Set the vertical scroll bar policy to always show
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the scroll pane to the layout
        GroupLayout layout = (GroupLayout) this.getLayout();
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        list = new com.raven.component.ListMusic<>();
        for(int i = 0; i < songlist.size(); i++){
            list.addItem(new Model_Music(String.valueOf(i+1), songlist.get(i).name, String.valueOf(songlist.get(i).rating)));
        }


        jLabel1 = new javax.swing.JLabel();


        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText(name);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(list, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(list, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
    }
    private javax.swing.JLabel jLabel1;
    private com.raven.component.ListMusic<String> list;

}