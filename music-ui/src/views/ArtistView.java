package views;

import Managers.ArtistController;
import Managers.DatabaseController;
import com.raven.component.ItemImage;
import com.raven.model.Model_Popular;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ArtistView extends JFrame implements Window {
    private ItemImage artistPhotoLabel;
    private JLabel artistNameLabel;
    private JList<String> albumsList;

    public ArtistView() {
    }


    @Override
    public void createAndShowUI() {
        setTitle("Artist View");
        String artistName = ArtistController.getInstance().selectedArtist.getTitle();
        System.out.println("Artist view: " + artistName);
        ArrayList<String> albums = DatabaseController.getInstance().fetchAlbumsOfArtist(ArtistController.getInstance().selectedArtist.getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);  // Set the initial size wider
        setResizable(false);  // Make it non-resizable
        setLocationRelativeTo(null);

        // Create and add the artist info panel
        JPanel artistInfoPanel = new JPanel();
        artistInfoPanel.setLayout(new BorderLayout(10, 10));
        artistInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        artistInfoPanel.setBackground(new Color(44, 62, 80));  // Set background color directly

        // Artist photo
        artistPhotoLabel = new ItemImage();
        artistPhotoLabel.setData(new Model_Popular(ArtistController.getInstance().selectedArtist.getImage(), ArtistController.getInstance().selectedArtist.getTitle(), ArtistController.getInstance().selectedArtist.getDescription()));
        artistInfoPanel.add(artistPhotoLabel, BorderLayout.WEST);

        // Artist name
        artistNameLabel = new JLabel(artistName);
        artistNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        artistNameLabel.setForeground(Color.WHITE);  // Set text color to white for visibility
        artistInfoPanel.add(artistNameLabel, BorderLayout.CENTER);

        add(artistInfoPanel, BorderLayout.NORTH);

        // Create and add the albums list
        albumsList = new JList<>(albums.toArray(new String[0]));
        albumsList.setFont(new Font("Arial", Font.PLAIN, 16));
        albumsList.setBorder(new EmptyBorder(10, 10, 10, 10));
        albumsList.setBackground(new Color(240, 240, 240));  // Light gray background for contrast

        JScrollPane scrollPane = new JScrollPane(albumsList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Albums"));

        // Custom list cell renderer for a modern look
        albumsList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setBorder(new EmptyBorder(5, 10, 5, 10));  // Adjust cell padding
                label.setBackground(isSelected ? new Color(184, 207, 229) : new Color(240, 240, 240));  // Set selected and non-selected background colors
                label.setForeground(Color.BLACK);  // Set text color to black
                return label;
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //resetUI();
                dispose();
            }
        });
        albumsList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single click
                    int index = albumsList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selectedAlbum = albumsList.getModel().getElementAt(index);
                        ArtistController.getInstance().selectedAlbum = selectedAlbum;
                        AlbumSongsView aw = new AlbumSongsView();
                        aw.createAndShowUI();
                    }
                }
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
        revalidate();
        repaint();
    }
}
