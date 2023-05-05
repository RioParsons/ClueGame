package game;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProveWrongDialog extends JDialog {
    private JComboBox<String> possibleProof;
    private JButton okButton;
    private JButton cancelButton;
    private String proof;

    public ProveWrongDialog(JFrame parent, ArrayList<String> proof ){
        super(parent, "Prove Guess Wrong", true);
        this.proof = null;

        String[] proofs = proof.toArray(new String[0]);

        possibleProof = new JComboBox<>(proofs);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        // Add components to dialog box
        JPanel contentPane = new JPanel();
        contentPane.add(new JLabel("Possible Proof:"));
        contentPane.add(possibleProof);
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
            this.proof = (String) possibleProof.getSelectedItem();
            dispose();
        });

        cancelButton.addActionListener(e -> {
            // Handle cancel button action
            dispose();
        });

    }

    public String getProof(){
        return this.proof;
    }
    
}
