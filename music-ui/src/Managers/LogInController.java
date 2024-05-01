package Managers;

import java.sql.SQLException;
import java.util.List;

public class LogInController {
    private static LogInController instance;
    private DatabaseController dbController;

    private String username;

    public static LogInController getInstance()  {
        try {
            if (instance == null) {
                instance = new LogInController();
            }
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private LogInController() throws SQLException {
        this.dbController = new DatabaseController();
    }

    public boolean addUser(String username, String password) throws SQLException {
        return dbController.addUser(username, password);
    }

    public boolean loginUser(String username, String password) throws SQLException {
        this.username = username;
        return dbController.loginUser(username, password);
    }


    public String getUsername() {
        return username;
    }
}

