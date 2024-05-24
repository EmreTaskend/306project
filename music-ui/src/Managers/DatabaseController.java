package Managers;

import Managers.relClasses.HighestRatedAlbum;
import Managers.relClasses.Song;
import com.raven.model.Model_Popular;
import com.raven.model.Model_Profile;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private final Connection connection;
    private static DatabaseController instance;
    public static String username;
    public static String choosedPlaylist;
    public static String choosedLikedName;

    public static boolean check = true;

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
        }
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO Playlist (owner, albumname) VALUES (?, ?)")){
            pstmt.setString(1, username);
            pstmt.setString(2, "Liked");
            int rowsAffected = pstmt.executeUpdate();
            return true;
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
    public boolean fetchUsers(String username) throws SQLException {
        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM User WHERE username = ?")) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next();
        }
    }
    public ArrayList<Song> fetchPlaylistSongs() {
        ArrayList<Song> trendingSongs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * 
            FROM PlaylistSongs P, Song S
            WHERE P.owner = ? AND S.name = P.songname AND P.plname = ? 
            """)) {
            pstmt.setString(1, DatabaseController.username);
            pstmt.setString(2, DatabaseController.choosedPlaylist);
            ResultSet resultSet = pstmt.executeQuery();
            ArrayList<String> liked = DatabaseController.getInstance().fetchPlaylistSongs("Liked");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending);
                if(liked.contains(name)) {
                    song.setLiked(true);
                }
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trendingSongs;
    }
    public ArrayList<Song> fetchSearched(String src) {
        ArrayList<Song> trendingSongs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                    SELECT *\s
                    FROM Song S
                    WHERE S.name LIKE ?;
                                
                """)) {
            pstmt.setString(1, "%"+src+"%");
            ResultSet resultSet = pstmt.executeQuery();
            ArrayList<String> liked = DatabaseController.getInstance().fetchPlaylistSongs("Liked");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending);
                if(liked.contains(name)) {
                    song.setLiked(true);
                }
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trendingSongs;
    }
    public ArrayList<Song> fetchPlaylistSongs(String owner, String plname) {
        ArrayList<Song> trendingSongs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * 
            FROM PlaylistSongs P, Song S
            WHERE P.owner = ? AND S.name = P.songname AND P.plname = ? 
            """)) {
            pstmt.setString(1, owner);
            pstmt.setString(2, plname);
            ResultSet resultSet = pstmt.executeQuery();
            ArrayList<String> liked = DatabaseController.getInstance().fetchPlaylistSongs("Liked");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending);
                if(liked.contains(name)) {
                    song.setLiked(true);
                }
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trendingSongs;
    }
    public ArrayList<String> fetchPlaylistSongs(String plname) {
        ArrayList<String> trendingSongs = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * 
            FROM PlaylistSongs P, Song S
            WHERE P.owner = ? AND S.name = P.songname AND P.plname = ? 
            """)) {
            pstmt.setString(1, DatabaseController.username);
            pstmt.setString(2, plname);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                // Create a new Song object and add it to the list
                trendingSongs.add(name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trendingSongs;
    }
    public ArrayList<Song> fetchOtherLikes() {
        ArrayList<Song> trendingSongs = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * 
            FROM PlaylistSongs P, Song S
            WHERE P.owner = ? AND S.name = P.songname AND P.plname = ? 
            """)) {
            pstmt.setString(1, DatabaseController.choosedLikedName);
            pstmt.setString(2, DatabaseController.choosedPlaylist);
            System.out.println(DatabaseController.choosedLikedName + " " + DatabaseController.choosedPlaylist);
            ResultSet resultSet = pstmt.executeQuery();
            ArrayList<String> liked = DatabaseController.getInstance().fetchPlaylistSongs("Liked");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending);
                if(liked.contains(song.name)){
                    song.setLiked(true);
                }
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trendingSongs;
    }
    public ArrayList<Song> fetchAlbumSongs(String artistname, String albumname) {
        ArrayList<Song> trendingSongs = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT * 
            FROM Album A, Song S
            WHERE A.artistname = ? AND S.name = A.songname AND A.albumname = ? 
            """)) {
            pstmt.setString(1, artistname);
            pstmt.setString(2, albumname);
            ResultSet resultSet = pstmt.executeQuery();
            ArrayList<String> liked = DatabaseController.getInstance().fetchPlaylistSongs("Liked");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending);
                if(liked.contains(name)){
                    song.setLiked(true);
                }
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
            ArrayList<String> liked = DatabaseController.getInstance().fetchPlaylistSongs("Liked");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String artist = resultSet.getString("artist");
                double rating = resultSet.getDouble("rating");
                boolean isTrending = resultSet.getBoolean("is_trending");
                // Create a new Song object and add it to the list
                Song song = new Song(name, artist, rating, isTrending);
                if(liked.contains(name)){
                    song.setLiked(true);
                }
                trendingSongs.add(song);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trendingSongs;
    }
    public ArrayList<String> fetchUserPlaylists() {
        ArrayList<String> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT albumname 
            FROM Playlist P
            WHERE P.owner = ? AND P.albumname <> "Liked"
            """)) {
            pstmt.setString(1, DatabaseController.username);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String plname = resultSet.getString("albumname");
                playlists.add(plname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public ArrayList<String> fetchAlbumsOfArtist(String artistname) {
        ArrayList<String> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT DISTINCT albumname 
            FROM Album A
            WHERE A.artistname = ? 
            """)) {
            pstmt.setString(1, artistname);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String plname = resultSet.getString("albumname");
                playlists.add(plname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public ArrayList<String> fetchArtists() {
        ArrayList<String> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT DISTINCT artistname
            FROM Artist A
           
            """)) {
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String plname = resultSet.getString("artistname");
                playlists.add(plname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public void createPlaylist(String plName) {
        ArrayList<String> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
            INSERT INTO Playlist (owner, albumname) VALUES (?, ?) 
            """)) {
            pstmt.setString(1, DatabaseController.username);
            pstmt.setString(2, plName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertSongToPlaylist(String pName, String songname){
        try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO PlaylistSongs (plname, songname, owner) VALUES (?, ?, ?)")) {
            pstmt.setString(1, pName);
            pstmt.setString(2, songname);
            pstmt.setString(3, username);
            int rowsAffected = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<HighestRatedAlbum> fetchHighestRatedAlbums(){
        ArrayList<HighestRatedAlbum> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                    SELECT
                        a.artistname,
                        a.albumname,
                        AVG(s.rating) AS average_rating,
                        ar.image -- Assuming the image column is named 'image' in the Artist table
                    FROM
                        Album a
                    JOIN
                        Song s
                    ON
                        a.songname = s.name
                        AND a.artistname = s.artist
                    JOIN
                        Artist ar
                    ON
                        a.artistname = ar.artistname
                    GROUP BY
                        a.artistname,
                        a.albumname,
                        ar.image
                    ORDER BY
                        average_rating DESC
                    LIMIT 4;
                    
                """)) {
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String albumname = resultSet.getString("albumname");
                String artistname = resultSet.getString("artistname");
                String image = resultSet.getString("image");
                Double rating = resultSet.getDouble("average_rating");
                playlists.add(new HighestRatedAlbum(artistname,albumname,rating,"/"+image));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public String fetchTopSongOfAlbum(String artistname, String albumname) throws SQLException {
        String song = "asdasd";
        try (PreparedStatement pstmt = connection.prepareStatement("""
            SELECT
                s.name
            FROM
                Album a
            JOIN
                Song s
            ON
                a.songname = s.name
                AND a.artistname = s.artist
            WHERE
                a.artistname = ?
                AND a.albumname = ?
                AND s.rating = (
                    SELECT MAX(s2.rating)
                    FROM Song s2
                    JOIN Album a2
                    ON s2.name = a2.songname
                    AND s2.artist = a2.artistname
                    WHERE a2.artistname = a.artistname
                    AND a2.albumname = a.albumname
                );
                """)) {
            pstmt.setString(1, artistname);
            pstmt.setString(2, albumname);
            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()) {
                String plname = resultSet.getString("name");
                song = plname;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(song);
        return song;
    }
    public ArrayList<Model_Popular> fetchHighestRatedArtists(){
        ArrayList<Model_Popular> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                    SELECT a.artistname, a.popularity, a.image, AVG(s.rating) as average_rating
                    FROM Artist a
                    JOIN Song s ON a.artistname = s.artist
                    GROUP BY a.artistname, a.popularity, a.image
                    ORDER BY average_rating DESC
                    LIMIT 5;  
                """)) {
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String artistname = resultSet.getString("artistname");
                String pop = resultSet.getString("popularity");
                String image = resultSet.getString("image");
                Double rating = resultSet.getDouble("average_rating");
                playlists.add(new Model_Popular(new ImageIcon(getClass().getResource("/"+image)),artistname,"Rating: " + String.format("%.1f", rating)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public ArrayList<Model_Popular> fetchPopularArtists(){
        ArrayList<Model_Popular> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                    SELECT artistname, popularity, image
                    FROM Artist
                    ORDER BY popularity DESC
                    LIMIT 6;
                  
                """)) {
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String artistname = resultSet.getString("artistname");
                int popularity = resultSet.getInt("popularity");
                String image = resultSet.getString("image");
                playlists.add(new Model_Popular(new ImageIcon(getClass().getResource("/"+image)),artistname,"Popularity: " + String.valueOf(popularity)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public ArrayList<Model_Profile> fetchLikedLists(){
        ArrayList<Model_Profile> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                    SELECT ps.owner, AVG(s.rating) as average_rating
                    FROM PlaylistSongs ps
                    JOIN Song s ON ps.songname = s.name
                    WHERE ps.plname = 'Liked' AND ps.owner <> ?
                    GROUP BY ps.plname, ps.owner
                    ORDER BY average_rating DESC
                    LIMIT 5;
                    
                """)) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String name = resultSet.getString("owner");
                Double rating = resultSet.getDouble("average_rating");
                Model_Profile a = new Model_Profile(name, "Rating: " + String.format("%.1f", rating),new ImageIcon(getClass().getResource("/com/raven/icon/test/LOVED.jpg")));
                playlists.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        check = false;
        return playlists;
    }
    public ArrayList<Model_Profile> fetchCompatibleAlbums(){
        ArrayList<Model_Profile> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                    SELECT ar.artistname, al.albumname, ar.image, COUNT(ps.songname) AS song_count FROM PlaylistSongs ps JOIN Album al ON ps.songname = al.songname JOIN Artist ar ON al.artistname = ar.artistname JOIN Song s ON ps.songname = s.name JOIN User u ON ps.owner = u.username WHERE u.username = ? AND ps.plname = 'Liked' GROUP BY ar.artistname, al.albumname, ar.image ORDER BY song_count DESC LIMIT 5\s
                """)) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String artistname = resultSet.getString("artistname");
                String albumname = resultSet.getString("albumname");
                String image = resultSet.getString("image");
                Model_Profile a = new Model_Profile(artistname,albumname,new ImageIcon(getClass().getResource("/"+image)));
                playlists.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }
    public ArrayList<Model_Profile> fetchBestPlaylists(){
        ArrayList<Model_Profile> playlists = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement("""
                 SELECT\s
                     ps.plname,\s
                     ps.owner,\s
                     COUNT(ps.songname) AS song_count
                 FROM\s
                     PlaylistSongs ps
                 WHERE\s
                     ps.owner <> ? AND ps.plname <> "Liked"
                 GROUP BY\s
                     ps.plname, ps.owner
                 ORDER BY\s
                     song_count DESC
                 LIMIT 5;
                         
                """)) {
            pstmt.setString(1, username);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String artistname = resultSet.getString("owner");
                String albumname = resultSet.getString("plname");
                Model_Profile a = new Model_Profile(artistname,albumname,new ImageIcon(getClass().getResource("/com/raven/icon/test/LOVED.jpg")));
                playlists.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }



    public static String getChoosedPlaylist() {
        return choosedPlaylist;
    }

    public static void setChoosedPlaylist(String choosedPlaylist) {
        DatabaseController.choosedPlaylist = choosedPlaylist;
    }
}





