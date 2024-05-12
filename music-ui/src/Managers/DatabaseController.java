package Managers;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private final Connection connection;
    private static DatabaseController instance;
    private static String username;
    public static String choosedPlaylist;

    public DatabaseController() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11706047",
                "sql11706047",
                "VPnqxV813U"
        );
    }
    public static DatabaseController getInstance()  {
        try {
            if (instance == null) {
                instance = new DatabaseController();
            }
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(String username, String password) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO User (username, password) VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public boolean loginUser(String username, String password) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            DatabaseController.username = username;
            return resultSet.next();
        }
    }
    public ArrayList<Song> fetchPlaylist() {
        ArrayList<Song> trendingSongs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * 
            FROM Playlist P, Song S
            WHERE P.owner = ? AND S.name = P.songname AND P.albumname = ? 
            """)) {
            pstmt.setString(1, DatabaseController.username);
            pstmt.setString(2, DatabaseController.choosedPlaylist);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                Time duration = resultSet.getTime("duration");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending, duration);
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trendingSongs;
    }
    public ArrayList<Song> fetchTrendings() {
        ArrayList<Song> trendingSongs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * FROM Song 
            WHERE is_trending = ?
            """)) {
            pstmt.setBoolean(1, true);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                Time duration = resultSet.getTime("duration");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending, duration);
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trendingSongs;
    }

    public static String getChoosedPlaylist() {
        return choosedPlaylist;
    }

    public static void setChoosedPlaylist(String choosedPlaylist) {
        DatabaseController.choosedPlaylist = choosedPlaylist;
    }
}





