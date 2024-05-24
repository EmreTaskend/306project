package com.raven.component;

import Managers.DatabaseController;
import Managers.relClasses.Song;
import com.raven.model.Model_Music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class ItemMusic extends JPanel {
    private final Model_Music data;
    private boolean play;
    private JLabel lbIcon;
    private JLabel lbText;
    private JLabel lbTime;
    private JButton btnLike;

    public ItemMusic(Model_Music data) {
        this.data = data;
        initComponents();
        setOpaque(false);
        lbText.setText(data.getName());
        lbTime.setText(data.getRating());
    }

    public void setPlay(boolean play) {
        if (this.play != play) {
            this.play = play;
            if (play) {
                lbIcon.setText("");
            } else {
                lbIcon.setIcon(null);
                lbIcon.setText(data.getNo());
                lbText.setFont(new Font("sansserif", Font.PLAIN, 14));
                lbText.setForeground(new Color(51, 51, 51));
                lbTime.setFont(new Font("sansserif", Font.PLAIN, 14));
                lbTime.setForeground(new Color(51, 51, 51));
            }
        }
    }


    private void initComponents() {
        lbIcon = new JLabel();
        lbText = new JLabel();
        lbTime = new JLabel();
        btnLike = new JButton();

        lbIcon.setHorizontalAlignment(SwingConstants.CENTER);

        lbText.setFont(new Font("sansserif", Font.PLAIN, 14));
        lbText.setForeground(new Color(51, 51, 51));
        lbText.setText("Music Name");

        lbTime.setFont(new Font("sansserif", Font.PLAIN, 14));
        lbTime.setForeground(new Color(51, 51, 51));
        lbTime.setHorizontalAlignment(SwingConstants.RIGHT);
        lbTime.setText("03:00");

        if(data.liked) {
            btnLike.setIcon(new ImageIcon(getClass().getResource("/com/raven/icon/test/heart.png")));
        }else{
            btnLike.setIcon(new ImageIcon(getClass().getResource("/com/raven/icon/love.png")));
        }

        btnLike.setBorderPainted(false);
        btnLike.setContentAreaFilled(false);
        btnLike.setFocusPainted(false);
        btnLike.setOpaque(false);
        btnLike.setEnabled(true); // Ensure the button is enabled
        btnLike.setVisible(true); // Ensure the button is visible


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbIcon, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15)
                                .addComponent(lbText)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTime, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnLike, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lbIcon, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(lbTime, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLike, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(246, 246, 246));
        g2.fillRect(0, getHeight() - 2, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }
}
