package com.raven.component;

import Managers.SelectSongManager;
import com.raven.model.Model_Music;
import views.WindowManager;
import views.Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ListMusic<E extends Object> extends JList<E> {
    public final DefaultListModel<E> model;
    private int playIndex = -1;

    public ListMusic() {
        model = new DefaultListModel<>();
        setModel(model);
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me) && me.getClickCount() == 2) {
                    int index = locationToIndex(me.getPoint());
                    if (index != playIndex) {
                        playIndex = index;
                        Model_Music selectedMusic = (Model_Music) getModel().getElementAt(playIndex);
                        SelectSongManager.getInstance().setCur_Song(selectedMusic);
                        SelectSongManager.getInstance().ind = selectedMusic.getNo();
                        try {
                            WindowManager.getInstance().showWindow(Windows.SongView);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Model_Music data = value instanceof Model_Music ? (Model_Music) value : new Model_Music("1", "No Music", "00:00", " ", " ", false);
                ItemMusic item = new ItemMusic(data);
                item.setPlay(index == playIndex);
                return item;
            }
        };
    }

    public void addItem(Model_Music data) {
        model.addElement((E) data);
    }


}
