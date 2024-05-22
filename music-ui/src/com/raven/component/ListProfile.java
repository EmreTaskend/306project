package com.raven.component;

import com.raven.model.Model_Profile;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import java.awt.Component;
import java.sql.SQLException;

public class ListProfile<E extends Object> extends JList<E> {

    private final DefaultListModel<E> model;

    public ListProfile() {
        model = new DefaultListModel<>();
        setModel(model);
        setOpaque(false);
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Model_Profile data;
                if (value instanceof Model_Profile) {
                    data = (Model_Profile) value;
                } else {
                    data = new Model_Profile("Name", "Description", new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/artists_selected.png")));
                }
                ItemProfile item = new ItemProfile(data);
                try {
                    item.setSelected(isSelected);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return item;
            }
        };
    }

    public void addItem(Model_Profile data) {
        model.addElement((E) data);
    }
}
