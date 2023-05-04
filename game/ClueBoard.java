package game;

import game.CardDeck;
import player.Player;
import player.UserPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/* Clue game board UI */
public class ClueBoard extends JPanel implements GameObserver {
    private static final int TILE_SIZE = 38;
    private static final int NUM_ROWS = 22;
    private static final int NUM_COLS = 20;
    private static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private static final Color BORDER_COLOR = new Color(0, 0, 0);
    private static final Color ROOM_COLOR = new Color(17, 147, 194);
    private static final Color HALLWAY_COLOR = new Color(252, 174, 61);
    UserPlayer userPlayer;
    private ArrayList<Player> players;

    private JPanel[][] tiles;
    private JPanel playerPanel;
    private JPanel computer1Panel;
    private JPanel computer2Panel;

    private int[] currentPlayerPos;
    private int[] currentPlayer2Pos;
    private int[] currentPlayer3Pos;

    public ClueBoard(UserPlayer userPlayer) {
        this.userPlayer = userPlayer;
        players = new ArrayList<>();
        players.add(userPlayer);
        for (Player player : players) {
            player.registerObserver(this);
        }
        currentPlayerPos = new int[]{21, 6};
        currentPlayer2Pos = new int[]{5, 19};
        currentPlayer3Pos = new int[]{13, 19};
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
        playerPanel = tiles[currentPlayerPos[0]][currentPlayerPos[1]];
        ImageIcon playerIcon = new ImageIcon(new ImageIcon(userPlayer.getImage())
                .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
        playerPanel.add(new JLabel(playerIcon));

        computer1Panel = tiles[currentPlayer2Pos[0]][currentPlayer2Pos[1]];
        ImageIcon player2Icon = new ImageIcon(new ImageIcon("resources/green.png")
                .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
        computer1Panel.add(new JLabel(player2Icon));

        computer2Panel = tiles[currentPlayer3Pos[0]][currentPlayer3Pos[1]];
        ImageIcon player3Icon = new ImageIcon(new ImageIcon("resources/peacock.png")
                .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
        computer2Panel.add(new JLabel(player3Icon));
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
        // Names of the rooms based on their positions
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

        // JPanel to hold both the ClueBoard and the player image
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(this, BorderLayout.CENTER);

        // Load the player image
        String userImage = userPlayer.getImage();
        ImageIcon playerIcon = new ImageIcon(new ImageIcon(userImage).getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH ));
        JLabel playerLabel = new JLabel(playerIcon);
        contentPane.add(playerLabel, BorderLayout.EAST);

        // JPanel to hold the buttons for making accusations and guesses
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.setPreferredSize(new Dimension(100, 100));

        // JButton to make an accusation
        JButton accuseButton = new JButton("Make Accusation");
        accuseButton.setSize(150, 70);
        accuseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeAccusation();
            }
        });
        buttonPanel.add(accuseButton);

        // JButton to make a guess
        JButton guessButton = new JButton("Make Guess");
        guessButton.setSize(150, 100);
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeGuess();
            }
        });
        buttonPanel.add(guessButton);

        contentPane.add(buttonPanel, BorderLayout.NORTH);

        JPanel dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(200, 100));
        ImageIcon diceIcon1 = new ImageIcon(new ImageIcon("resources/Dice_3.png").getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH ));
        JLabel diceLabel1 = new JLabel(diceIcon1);
        dicePanel.add(diceLabel1);
        ImageIcon diceIcon2 = new ImageIcon(new ImageIcon("resources/Dice_5.png").getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH ));
        JLabel diceLabel2 = new JLabel(diceIcon2);
        dicePanel.add(diceLabel2);
        JButton rollDice = new JButton("Roll Dice");
        rollDice.setSize(150, 70);
        // rollDice button
        rollDice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Generate random dice rolls
                int dice1 = (int) (Math.random() * 6) + 1; // generate a number between 1 and 6
                int dice2 = (int) (Math.random() * 6) + 1; // generate a number between 1 and 6

                // Set diceIcon1 and diceIcon2 to new values based on dice rolls
                diceIcon1.setImage(new ImageIcon("resources/Dice_" + dice1 + ".png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
                diceLabel1.repaint();
                diceIcon2.setImage(new ImageIcon("resources/Dice_" + dice2 + ".png").getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
                diceLabel2.repaint();
                movePlayerToken(dice1+dice2);
            }
        });

        dicePanel.add(rollDice);
        JButton endTurn = new JButton("End Turn");
        endTurn.setSize(150, 70);
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveAI1Token();
                moveAI2Token();
            }
        });
        dicePanel.add(endTurn);

        contentPane.add(dicePanel, BorderLayout.SOUTH);

        //JPanel to hold the card icons
        JPanel cardPanel = new JPanel(new GridLayout(3, 2));
        cardPanel.setPreferredSize(new Dimension(200, 300));
        contentPane.add(cardPanel, BorderLayout.WEST);

        CardDeck cards = CardDeck.getInstance();
        cards.shuffleCards();
        userPlayer.cards = new ArrayList<>(cards.cardNames.subList(0, 6));
        for(String card : userPlayer.cards) {
            ImageIcon cardIcon1 = new ImageIcon(new ImageIcon(cards.getCardImage(card)).getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
            JLabel cardLabel1 = new JLabel(cardIcon1);
            cardPanel.add(cardLabel1);
        }

        frame.setLocationRelativeTo(null);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    @Override
    public void update(Player player, String message) {
        System.out.println(player.getName() + message);
    }

    public void makeGuess() {
        GuessDialog dialog = new GuessDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
    }

    public void makeAccusation() {
        AccuseDialog dialog = new AccuseDialog((JFrame) SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
    }
    public void movePlayerToken(int rollCount) {
        // Calculate the new position based on the roll count
        int[] newPos = new int[]{
                currentPlayerPos[0] - (rollCount),
                currentPlayerPos[1]
        };
            currentPlayerPos = newPos;
            // Remove the old player token
            Component[] components = playerPanel.getComponents();
            for (Component c : components) {
                playerPanel.remove(c);
            }
        playerPanel.revalidate();
        playerPanel.repaint();

            playerPanel = tiles[currentPlayerPos[0]][currentPlayerPos[1]];
            // Add the new player token
            ImageIcon playerIcon = new ImageIcon(new ImageIcon(userPlayer.getImage())
                    .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
            playerPanel = tiles[currentPlayerPos[0]][currentPlayerPos[1]];
            playerPanel.add(new JLabel(playerIcon));
            playerPanel.revalidate();
            playerPanel.repaint();
    }

    public void moveAI1Token() {
        // Calculate the new position based on the roll count
        int[] newPos = new int[]{
                currentPlayer2Pos[0] ,
                currentPlayer2Pos[1] - (int) (Math.random() * 12) + 1
        };
        currentPlayer2Pos = newPos;
        // Remove the old player token
        Component[] components = computer1Panel.getComponents();
        for (Component c : components) {
            computer1Panel.remove(c);
        }
        computer1Panel.revalidate();
        computer1Panel.repaint();

        computer1Panel = tiles[currentPlayer2Pos[0]][currentPlayer2Pos[1]];
        // Add the new player token
        ImageIcon playerIcon = new ImageIcon(new ImageIcon("resources/green.png")
                .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
        computer1Panel = tiles[currentPlayer2Pos[0]][currentPlayer2Pos[1]];
        computer1Panel.add(new JLabel(playerIcon));
        computer1Panel.revalidate();
        computer1Panel.repaint();
    }

    public void moveAI2Token() {
        // Calculate the new position based on the roll count
        int[] newPos = new int[]{
                currentPlayer3Pos[0] ,
                currentPlayer3Pos[1] - (int) (Math.random() * 12) + 1
        };
        currentPlayer3Pos = newPos;
        // Remove the old player token
        Component[] components = computer2Panel.getComponents();
        for (Component c : components) {
            computer2Panel.remove(c);
        }
        computer2Panel.revalidate();
        computer2Panel.repaint();

        computer2Panel = tiles[currentPlayer3Pos[0]][currentPlayer3Pos[1]];
        // Add the new player token
        ImageIcon playerIcon = new ImageIcon(new ImageIcon("resources/peacock.png")
                .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
        computer2Panel = tiles[currentPlayer3Pos[0]][currentPlayer3Pos[1]];
        computer2Panel.add(new JLabel(playerIcon));
        computer2Panel.revalidate();
        computer2Panel.repaint();
    }

}
