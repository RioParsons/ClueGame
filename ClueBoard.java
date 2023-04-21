import javax.swing.*;
import java.awt.*;

public class ClueBoard extends JPanel {
    private static final int TILE_SIZE = 38;
    private static final int NUM_ROWS = 22;
    private static final int NUM_COLS = 20;
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color BORDER_COLOR = new Color(0, 0, 0);
    private static final Color ROOM_COLOR = new Color(17, 147, 194);
    private static final Color HALLWAY_COLOR = new Color(252, 174, 61);

    private JPanel[][] tiles;
    private JPanel playerPanel;

    public ClueBoard() {
        setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        tiles = new JPanel[NUM_ROWS][NUM_COLS];
        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLS; col++) {
                JPanel tile = new JPanel();
                tile.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                tile.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

                // Set color based on tile type
                if (isRoomTile(row, col)) {
                    tile.setBackground(ROOM_COLOR);
                    // Add label for room
                    JLabel label = new JLabel(getRoomName(row, col));
                    label.setForeground(Color.WHITE);
                    label.setFont(new Font("SansSerif", Font.PLAIN, 12));
                    label.setHorizontalAlignment(JLabel.CENTER);
                    tile.add(label);
                } else {
                    tile.setBackground(HALLWAY_COLOR);
                }

                add(tile);
                tiles[row][col] = tile;
            }
        }
    }


    private boolean isRoomTile(int row, int col) {
        if((col==6 || col==7 || col==12 || col==13))
            return false;
        if(row==4 && col!=8 && col!=9 && col!=10 && col!=11 && col<13)
            return false;
        if(row==5 && col!=8 && col!=9 && col!=10 && col!=11)
            return false;
        if(row==6 && col>7)
            return false;
        if(row==7 && col>7 && col<13)
            return false;
        if(row==10 && col<7)
            return false;
        if(row==11 && col<7)
            return false;
        if(row==13 && col>7)
            return false;
        if(row==14 && col>7)
            return false;
        if(row==16 && col<7)
            return false;
        if(row==17 && col<7)
            return false;

        return true;
    }

    private String getRoomName(int row, int col) {
        // Define the names of the rooms based on their positions
        if (row == 2 && col == 3) {
            return "Study";
        } else if (row == 8 && col == 3) {
            return "Library";
        } else if (row == 13 && col == 3) {
            return "Billiard";
        } else if (row == 21 && col == 3) {
            return "Conserv";
        } else if (row == 2 && col == 10) {
            return "Hall";
        } else if (row == 16 && col == 10) {
            return "Ball";
        } else if (row == 3 && col == 18) {
            return "Lounge";
        } else if (row == 10 && col == 18) {
            return "Dining";
        } else if (row == 18 && col == 18) {
            return "Kitchen";
        } else if (row == 10 && col == 9) {
            return "Clue";
        } else {
            return null;
        }
    }

    public void highlightTile(int row, int col, Color color) {
        tiles[row][col].setBackground(color);
    }

    public void initializeBoard() {
        JFrame frame = new JFrame("Clue Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((TILE_SIZE * NUM_COLS + 2) + 500, TILE_SIZE * NUM_ROWS + 2);
        frame.setResizable(false);

        // Create a new JPanel to hold both the ClueBoard and the player image
        JPanel contentPane = new JPanel(new BorderLayout());

        // Add the ClueBoard to the content pane
        ClueBoard board = new ClueBoard();
        contentPane.add(board, BorderLayout.CENTER);

        // Load the player image
        ImageIcon playerIcon = new ImageIcon(new ImageIcon("resources/scarlet.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH ));
        JLabel playerLabel = new JLabel(playerIcon);
        contentPane.add(playerLabel, BorderLayout.EAST);

        // Create a new JPanel to hold the card icons
        JPanel cardPanel = new JPanel(new GridLayout(3, 2));
        cardPanel.setPreferredSize(new Dimension(200, 300));
        contentPane.add(cardPanel, BorderLayout.WEST);

        // Add the card icons to the panel
        ImageIcon cardIcon1 = new ImageIcon(new ImageIcon("resources/peacock_card.jpg").getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel1 = new JLabel(cardIcon1);
        cardPanel.add(cardLabel1);

        ImageIcon cardIcon2 = new ImageIcon(new ImageIcon("resources/peacock_card.jpg").getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel2 = new JLabel(cardIcon2);
        cardPanel.add(cardLabel2);

        ImageIcon cardIcon3 = new ImageIcon(new ImageIcon("resources/peacock_card.jpg").getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel3 = new JLabel(cardIcon3);
        cardPanel.add(cardLabel3);

        ImageIcon cardIcon4 = new ImageIcon(new ImageIcon("resources/peacock_card.jpg").getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel4 = new JLabel(cardIcon4);
        cardPanel.add(cardLabel4);

        ImageIcon cardIcon5 = new ImageIcon(new ImageIcon("resources/peacock_card.jpg").getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel5 = new JLabel(cardIcon5);
        cardPanel.add(cardLabel5);

        ImageIcon cardIcon6 = new ImageIcon(new ImageIcon("resources/peacock_card.jpg").getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
        JLabel cardLabel6 = new JLabel(cardIcon6);
        cardPanel.add(cardLabel6);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }
}
