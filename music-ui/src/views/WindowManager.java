package views;
import javax.swing.*;
import java.sql.SQLException;

public class WindowManager {

    private JFrame frame;
    private static WindowManager instance;
    private WindowManager(){

    }
    public void showWindow(Windows window) throws SQLException {
        window.window.createAndShowUI();
    }
    public void repWindow(Windows window){
        window.window.repaint();
    }

    public static WindowManager getInstance(){
        if(instance == null){
            instance = new WindowManager();
        }
        return instance;
    }


    public JFrame getFrame() {
        return frame;
    }
}

