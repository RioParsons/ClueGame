package game;

import game.CardDeck;
import player.Player;
import player.UserPlayer;

import javax.swing.*;
import javax.swing.border.Border;

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
    private static final Color POSSIBLE_MOVE_COLOR = new Color(144, 238, 144);
    Player userPlayer;
    boolean userTurn;
    ClueGame game;
    ArrayList<Player> players;

    JButton rollDice;
    JButton endTurn;
    JButton accuseButton;
    JButton guessButton;

    JPanel buttonPanel;
    JPanel dicePanel;
    JPanel contentPane;

    ArrayList<JPanel> playerPanels;

    private JPanel[][] tiles;
    private JButton clueSheet;

    public ClueBoard(ArrayList<Player> players, ClueGame game) {
        this.game = game;
        this.players=players;
        this.userPlayer = players.get(0);
        userPlayer.registerObserver(this);

        setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

        addTiles();
        addPlayerPanels();
    }

    public void addTiles(){
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
                    if (isRoomTitle(row, col)){
                        JLabel label = new JLabel(getRoomTitle(row, col));
                        label.setForeground(Color.WHITE);
                        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
                        label.setHorizontalAlignment(JLabel.CENTER);
                        tile.add(label);
                    }
                    
                } else {
                    tile.setBackground(HALLWAY_COLOR);
                }
                add(tile);
                tiles[row][col] = tile;
            }
        }
    }

    public void addPlayerPanels(){
        playerPanels = new ArrayList<JPanel>();
        for (Player player : players) {
            int[] pos = player.getPos();
            JPanel panel = tiles[pos[0]][pos[1]];
            
            ImageIcon playerIcon = new ImageIcon(new ImageIcon(player.getImage())
                .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));

            panel.add(new JLabel(playerIcon));
            playerPanels.add(panel);
        }
    }

    public boolean isRoomTile(int row, int col) {
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

    public boolean isRoomTitle(int row, int col) {
        // Names of the rooms based on their positions
        if (row == 2 && col == 3) {
            return true;
        } else if (row == 8 && col == 3) {
            return true;
        } else if (row == 13 && col == 3) {
            return true;
        } else if (row == 20 && col == 3) {
            return true;
        } else if (row == 2 && col == 10) {
            return true;
        } else if (row == 16 && col == 10) {
            return true;
        } else if (row == 3 && col == 18) {
            return true;
        } else if (row == 10 && col == 18) {
            return true;
        } else if (row == 18 && col == 18) {
            return true;
        } else if (row == 10 && col == 9) {
            return true;
        } else {
            return false;
        }
    }

    public String getRoomName(int row, int col) {
        // Names of the rooms based on their positions
        if (row < 4 && col < 6) {
            return "Study";
        } else if (row < 10 && row > 5 && col < 6) {
            return "Library";
        } else if (row > 11 && row < 16 && col < 6) {
            return "Billiard Room";
        } else if (row > 17 && col < 6) {
            return "Conservatory";
        } else if (row < 6 && col < 12 && col > 7) {
            return "Hall";
        } else if (row > 14 && col < 12 && col > 7) {
            return "Ball";
        } else if (row < 5 && col > 13) {
            return "Lounge";
        } else if (row > 6 && row < 13 && col > 13) {
            return "Dining Room";
        } else if (row > 14 && col > 13) {
            return "Kitchen";
        } else if (row < 13 && row > 7 && col < 12 && col > 7) {
            return "Clue";
        } else {
            return null;
        }
    }

    public String getRoomTitle(int row, int col) {
        // Names of the rooms based on their positions
        if (row < 4 && col < 6) {
            return "Study";
        } else if (row < 10 && row > 5 && col < 6) {
            return "Library";
        } else if (row > 11 && row < 16 && col < 6) {
            return "Billiard";
        } else if (row > 17 && col < 6) {
            return "Conserv";
        } else if (row < 6 && col < 12 && col > 7) {
            return "Hall";
        } else if (row > 14 && col < 12 && col > 7) {
            return "Ball";
        } else if (row < 5 && col > 13) {
            return "Lounge";
        } else if (row > 6 && row < 13 && col > 13) {
            return "Dining";
        } else if (row > 14 && col > 13) {
            return "Kitchen";
        } else if (row < 13 && row > 7 && col < 12 && col > 7) {
            return "Clue";
        } else {
            return null;
        }
    }

    public void highlightTile(int row, int col, Color color) {
        tiles[row][col].setBackground(color);
    }

    public void initializeBoard() {

        addBoardToPlayers();

        //Board is rendered ready for user to take their turn

        JFrame frame = new JFrame("Clue Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((TILE_SIZE * NUM_COLS + 2) + 500, TILE_SIZE * NUM_ROWS + 2);
        frame.setResizable(false);

        // JPanel to hold both the ClueBoard and the player image
        this.contentPane = new JPanel(new BorderLayout());
        contentPane.add(this, BorderLayout.CENTER);

        addButtonPanel();
        addDicePanel();
        addCardPanel();

        frame.setLocationRelativeTo(null);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void addButtonPanel(){
        // JPanel to hold the buttons for making accusations and guesses
        this.buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.setPreferredSize(new Dimension(100, 100));

        // JButton to make an accusation
        this.accuseButton = new JButton("Make Accusation");
        accuseButton.setSize(150, 70);
        accuseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerMakeAccusation();
            }
        });
        buttonPanel.add(accuseButton);

        // JButton to make a guess
        this.guessButton = new JButton("Make Guess");
        guessButton.setSize(150, 100);
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guessButton.setEnabled(false);
                userPlayer.makeSuggestion();
            }
        });
        buttonPanel.add(guessButton);

        contentPane.add(buttonPanel, BorderLayout.NORTH);
    }

    public void addDicePanel(){
        this.dicePanel = new JPanel();
        dicePanel.setPreferredSize(new Dimension(200, 100));
        ImageIcon diceIcon1 = new ImageIcon(new ImageIcon("resources/Dice_3.png").getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH ));
        JLabel diceLabel1 = new JLabel(diceIcon1);
        dicePanel.add(diceLabel1);
        ImageIcon diceIcon2 = new ImageIcon(new ImageIcon("resources/Dice_5.png").getImage().getScaledInstance(50,50,java.awt.Image.SCALE_SMOOTH ));
        JLabel diceLabel2 = new JLabel(diceIcon2);
        dicePanel.add(diceLabel2);
        this.rollDice = new JButton("Roll Dice");
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

                rollDice.setEnabled(false); //So that user can only roll dice once

                userPlayer.move(dice1 + dice2);
            }
        });

        dicePanel.add(rollDice);
        this.endTurn = new JButton("End Turn");
        endTurn.setSize(150, 70);
        dicePanel.add(endTurn);
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTurn.setEnabled(false);
                game.endPlayerTurn();
            }
        });
        dicePanel.add(endTurn);

        this.clueSheet = new JButton("Clue Sheet");
        clueSheet.setSize(150, 70);
        clueSheet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Clue Sheet");
                frame.setSize(800, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ClueSheet clueSheet = new ClueSheet();
                frame.getContentPane().add(clueSheet);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
        dicePanel.add(clueSheet);

        contentPane.add(dicePanel, BorderLayout.SOUTH);
    }

    public void addCardPanel(){
        //JPanel to hold the card icons
        JPanel cardPanel = new JPanel(new GridLayout(3, 2));
        cardPanel.setPreferredSize(new Dimension(200, 300));
        contentPane.add(cardPanel, BorderLayout.WEST);

        for(String card : userPlayer.cards) {
            ImageIcon cardIcon1 = new ImageIcon(new ImageIcon(CardDeck.getCardImage(card)).getImage().getScaledInstance(100, 150, java.awt.Image.SCALE_SMOOTH));
            JLabel cardLabel1 = new JLabel(cardIcon1);
            cardPanel.add(cardLabel1);
        }
    }

    @Override
    public void update(Player player, String message) {
        System.out.println(player.getName() + message);
    }

    public void renderUserTurn(){
        guessButton.setEnabled(true);
        rollDice.setEnabled(true);
        endTurn.setEnabled(true);
    }

    public ArrayList<int[]> generatePossibleMoves(int[] pos, int roll){
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        int j = roll;

        int current_row = pos[0];
        int current_col = pos[1];

        for (int i = 0; i < roll + 1; i++){
            int[] move1 = new int[]{current_row + i, current_col + j};
            int[] move2 = new int[]{current_row + i, current_col - j};
            int[] move3 = new int[]{current_row - i, current_col + j};
            int[] move4 = new int[]{current_row - i, current_col - j};

            possibleMoves.add(move1);
            possibleMoves.add(move2);
            possibleMoves.add(move3);
            possibleMoves.add(move4);
            j--;
        }

        possibleMoves = validateMoves(possibleMoves);
        return possibleMoves;

    }

    public ArrayList<int[]> validateMoves(ArrayList<int[]> potentialMoves){
        ArrayList<int[]> possibleMoves = new ArrayList<int[]>();
        for (int[] i: potentialMoves){
            String room = getRoomName(i[0], i[1]);
            if (i[0] < NUM_ROWS & i[0] > -1 & i[1] < NUM_COLS & i[1] > -1 ){
                if (isRoomTile(i[0], i[1])){
                    if (room.equals("Clue") == false){
                        possibleMoves.add(i);
                    }  
                } else {
                    possibleMoves.add(i);
                }
            }
        }
        return possibleMoves;
    }

    public void showPossibleMoves(ArrayList<int[]> possibleMoves){

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        ArrayList<JPanel> panels = new ArrayList<JPanel>();
        
        for (int[] i : possibleMoves){
            
            JPanel panel = tiles[i[0]][i[1]];
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));;
            panel.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
            panel.setBackground(POSSIBLE_MOVE_COLOR);
            
            JButton posMove = new JButton();
            posMove.setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
            posMove.setMargin(new Insets(0,0,0,0));
            posMove.setBorder(BorderFactory.createEmptyBorder());
            posMove.setBackground(POSSIBLE_MOVE_COLOR);

            panel.add(posMove);
            panel.revalidate();
            panel.repaint();
            buttons.add(posMove);
            panels.add(panel);

            posMove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int[] pos = new int[]{i[0], i[1]};
                    userPlayer.setPosition(pos);

                    deleteButtons(panels, buttons);
                    movePlayerToken(userPlayer);

                    for (int[] m : possibleMoves) {
                        int row = m[0];
                        int col = m[1];
                        // Set color based on tile type
                        if (isRoomTile(row, col)) {
                            tiles[row][col].setBackground(ROOM_COLOR);
                            // Add label for room
                            if (isRoomTitle(row, col)) {
                                JLabel label = new JLabel(getRoomName(row, col));
                                label.setForeground(Color.WHITE);
                                label.setFont(new Font("SansSerif", Font.PLAIN, 12));
                                label.setHorizontalAlignment(JLabel.CENTER);
                                tiles[row][col].add(label);
                            }

                        } else {
                            tiles[row][col].setBackground(HALLWAY_COLOR);
                        }
                    }

                    if((isRoomTile(pos[0], pos[1])) != true){
                        guessButton.setEnabled(false);
                    } 
                }
            });
        }
    }

    public void deleteButtons(ArrayList<JPanel> panels, ArrayList<JButton> buttons){
        for (int j = 0; j < buttons.size(); j++){
            panels.get(j).remove(buttons.get(j));
            panels.get(j).revalidate();
            panels.get(j).repaint();
        }
    }

    public void movePlayerToken(Player p){
        //Find Player index
        int ind = -1;
        for (int i = 0; i < players.size(); i++){
            if (p.getName().equals(players.get(i).getName())){
                ind = i;  
            }
        }

        int pos[] = p.getPos();

        // Remove the old player token
        JPanel playerPanel = playerPanels.get(ind);
        Component[] components = playerPanel.getComponents();
        for (Component c : components) {
            playerPanel.remove(c);
        }
        playerPanel.revalidate();
        playerPanel.repaint();
        playerPanel = tiles[pos[0]][pos[1]];

        ImageIcon playerIcon = new ImageIcon(new ImageIcon(p.getImage())
            .getImage().getScaledInstance(TILE_SIZE, TILE_SIZE, Image.SCALE_SMOOTH));
        playerPanel = tiles[pos[0]][pos[1]];
        playerPanel.add(new JLabel(playerIcon));
        playerPanel.revalidate();
        playerPanel.repaint();

        playerPanels.set(ind, playerPanel);
    }

    public void addBoardToPlayers(){
        for (Player p : players){
            p.setBoard(this);
        }
    }

    public void playerMakeAccusation(){
        AccuseDialog dialog = new AccuseDialog((JFrame) SwingUtilities.getWindowAncestor(this), this, game);
        dialog.setVisible(true);
    }

}
