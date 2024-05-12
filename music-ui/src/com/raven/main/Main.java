package com.raven.main;

import com.raven.model.Model_Menu;
import com.raven.swing.ScrollBar;
import views.WindowManager;
import views.Windows;

import java.awt.Color;

public class Main  {
    static WindowManager wm = WindowManager.getInstance();
    public static void main(String args[]) {
        wm.showWindow(Windows.MainView);
    }

}
