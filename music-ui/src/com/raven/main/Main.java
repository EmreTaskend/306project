package com.raven.main;

import com.raven.model.Model_Menu;
import com.raven.swing.ScrollBar;
import views.WindowManager;
import views.Windows;

import java.awt.Color;
import java.sql.SQLException;

public class Main  {
    static WindowManager wm = WindowManager.getInstance();
    public static void main(String args[]) throws SQLException {
        wm.showWindow(Windows.LogIn);
    }

}
