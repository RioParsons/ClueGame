package game;

import javax.swing.*;

import player.Player;

public class AccuseDialog extends JDialog {

    private JComboBox<String> personCombo;
    private JComboBox<String> weaponCombo;
    private JComboBox<String> roomCombo;
    private JButton okButton;
    private JButton cancelButton;

    Envelope envelope = Envelope.getInstance(CardDeck.getInstance());
    ClueBoard board;
    ClueGame game;

    public AccuseDialog(JFrame parent, ClueBoard board, ClueGame game) {
        super(parent, "Make an Accusation", true);
        this.board = board;
        this.game = game;

        Player player = board.userPlayer;
        String Room = getPlayerRoom(player);
        if (Room == null){
            dispose();
            System.out.println("User was not in a room so they could not make a guess");
        }

        String[] suspects = player.getSheet().getSuspects().toArray(new String[0]);
        String[] weapons = player.getSheet().getWeapons().toArray(new String[0]);
        String[] rooms = player.getSheet().getRooms().toArray(new String[0]);

        // Initialize components
        personCombo = new JComboBox<>(suspects);
        weaponCombo = new JComboBox<>(weapons);
        roomCombo = new JComboBox<>(rooms);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        // Add components to dialog box
        JPanel contentPane = new JPanel();
        contentPane.add(new JLabel("Person:"));
        contentPane.add(personCombo);
        contentPane.add(new JLabel("Weapon:"));
        contentPane.add(weaponCombo);
        contentPane.add(new JLabel("Room:"));
        contentPane.add(roomCombo);
        contentPane.add(okButton);
        contentPane.add(cancelButton);
        setContentPane(contentPane);

        // Set dialog box properties
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Add action listeners to buttons
        okButton.addActionListener(e -> {
            if(player.makeAccusation(getPerson(), getWeapon(), getRoom(), envelope)){
                game.Winner=player;
            }
            dispose();
        });
        cancelButton.addActionListener(e -> {
            // Handle cancel button action
            dispose();
        });
    }

    public String getPerson() {
        return (String) personCombo.getSelectedItem();
    }

    public String getWeapon() {
        return (String) weaponCombo.getSelectedItem();
    }

    public String getRoom() {
        return (String) roomCombo.getSelectedItem();
    }

    // void checkAccusation() {
    //     boolean isValid = envelope.checkAccusation(getPerson(), getWeapon(), getRoom());
    //     if(isValid) {
    //         JOptionPane.showMessageDialog(this, "Congratulations! You are the winner!");
    //     }
    //     else {
    //         JOptionPane.showMessageDialog(this, "Wrong Accusation! You Lose!");
    //     }
    // }

    public String getPlayerRoom(Player player){
        String room;
        int[] pos = player.getPos();

        if((board.isRoomTile(pos[0], pos[1])) == true){
            room = board.getRoomName(pos[0], pos[1]);
        } else {
            room = null;
        }

        return room;
    }

}

