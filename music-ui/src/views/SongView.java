package views;

import Managers.DatabaseController;
import Managers.ListMusicController;
import Managers.LogInController;
import Managers.SelectSongManager;
import com.raven.component.Music;
import com.raven.form.Form_Artists;
import com.raven.model.Model_Music;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.border.EmptyBorder;

public class SongView extends JFrame implements Window {

    private JLabel lblSongName;
    private JLabel lblArtistName;
    private JLabel lblRating;
    private JButton btnLike;
    private JButton btnAddToPlaylist;
    private Model_Music s;

    public SongView() {
    }

    @Override
    public void createAndShowUI() {
        if (s == null) {
            s = new Model_Music("", "", "", "", "", false);
        }
        this.s = SelectSongManager.getInstance().getCur_Song();
        String songName = s.getName();
        String artistName = s.getArtist();
        String rating = s.getRating();

        setTitle("Song View");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(450, 300)); // Set preferred size
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = Color.BLACK;
                Color color2 = Color.RED;
                GradientPaint gp = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, width, height);
            }
        };
        setContentPane(contentPane);

        // Create labels for song info with larger and bold white font
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Color labelColor = Color.WHITE;
        lblSongName = new JLabel("Song: " + songName);
        lblSongName.setBorder(new EmptyBorder(20, 10, 20, 10)); // Add padding
        lblSongName.setFont(labelFont);
        lblSongName.setForeground(labelColor);
        lblArtistName = new JLabel("Artist: " + artistName);
        lblArtistName.setBorder(new EmptyBorder(20, 10, 20, 10)); // Add padding
        lblArtistName.setFont(labelFont);
        lblArtistName.setForeground(labelColor);
        lblRating = new JLabel("Rating: " + rating + "/5");
        lblRating.setBorder(new EmptyBorder(20, 10, 20, 10)); // Add padding
        lblRating.setFont(labelFont);
        lblRating.setForeground(labelColor);

        // Create buttons with custom styles
        btnLike = new JButton("Like");
        btnLike.setPreferredSize(new Dimension(100, 40)); // Set button size
        btnAddToPlaylist = new JButton("Add to Playlist");
        btnAddToPlaylist.setPreferredSize(new Dimension(150, 40)); // Set button size

        // Add labels to the top panel
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.setOpaque(false); // Make panel transparent
        topPanel.add(lblSongName);
        topPanel.add(lblArtistName);
        topPanel.add(lblRating);
        contentPane.add(topPanel, BorderLayout.NORTH);

        // Add buttons to the bottom panel with vertical spacing
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false); // Make panel transparent
        buttonPanel.add(btnLike);
        buttonPanel.add(btnAddToPlaylist);
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
        setupActions();
    }

    private void setupActions() {
        // Add action listeners to buttons
        btnLike.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(s.getName());
                DatabaseController.getInstance().insertSongToPlaylist("Liked", s.getName());
                LogInController.getInstance().ma.resetUI();
                JOptionPane.showMessageDialog(SongView.this, "Liked!");
            }
        });

        btnAddToPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WindowManager.getInstance().showWindow(Windows.ChoosePlaylistView);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
