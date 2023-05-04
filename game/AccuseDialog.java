package game;

import javax.swing.*;

public class AccuseDialog extends JDialog {

    private JComboBox<String> personCombo;
    private JComboBox<String> weaponCombo;
    private JComboBox<String> roomCombo;
    private JButton okButton;
    private JButton cancelButton;

    Envelope envelope = Envelope.getInstance(CardDeck.getInstance());

    public AccuseDialog(JFrame parent) {
        super(parent, "Make a Guess", true);

        // Initialize components
        personCombo = new JComboBox<>(new String[] {"Miss Scarlett", "Professor Plum", "Reverend Green", "Colonel Mustard", "Mrs Peacock", "Dr Orchid"});
        weaponCombo = new JComboBox<>(new String[] {"Candlestick", "Knife", "Lead Piping", "Revolver", "Rope", "Wrench"});
        roomCombo = new JComboBox<>(new String[] {"Kitchen", "Library", "Study", "Dining Room", "Lounge", "Hall", "Billiard Room", "Conservatory", "Ballroom"});
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
            checkAccusation();
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

    void checkAccusation() {
        boolean isValid = envelope.checkAccusation(getPerson(), getWeapon(), getRoom());
        if(isValid) {
            JOptionPane.showMessageDialog(this, "Congratulations! You are the winner!");
        }
        else {
            JOptionPane.showMessageDialog(this, "Wrong Accusation! You Lose!");
        }
    }

}

