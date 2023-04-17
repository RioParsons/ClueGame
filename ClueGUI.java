import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ClueGUI extends JFrame {
    private JPanel window;
    public static final Image HOME_SCREEN = loadImage("Clue-home-page.png");
    private static final String IMAGE_PATH = "resources/";

    public ClueGUI() {
        welcomeScreen();
        setLocationRelativeTo(null);
    }

    private void welcomeScreen() {

        this.setTitle("Clue Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ============ then the welcome screen =====================
        window = new JPanel() {
            protected void paintComponent(Graphics g) {
                g.drawImage(HOME_SCREEN, 0, 0, this);
            }
        };
        window.setPreferredSize(new Dimension(965, 800) );

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(400, 450, 150, 50);
        startButton.addActionListener(e -> {
            showSetupDialog();
        });
        add(startButton);

        this.add(window);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public static Image loadImage(String filename){
        try {
            URL imageURL = new URL("file://"+ new File(IMAGE_PATH + filename).getAbsolutePath());
            System.out.println(imageURL);
            Image img = ImageIO.read(imageURL);
            return img;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ClueGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showSetupDialog() {
        // Display a dialog box to get the number of players
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of players (3-6):"));

        // Display a dialog box to select a character for each player
        String[] characters = {"Miss Scarlet", "Professor Plum", "Mr. Green", "Colonel Mustard", "Mrs. Peacock", "Mrs. Orchid" };
        String[] options = {"OK", "Cancel"};
        ImageIcon[] icons = {
                new ImageIcon(new ImageIcon("resources/scarlet.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH )),
                new ImageIcon(new ImageIcon("resources/plum.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH )),
                new ImageIcon(new ImageIcon("resources/green.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH )),
                new ImageIcon(new ImageIcon("resources/mustard.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH )),
                new ImageIcon(new ImageIcon("resources/peacock.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH )),
                new ImageIcon(new ImageIcon("resources/orchid.png").getImage().getScaledInstance(150,350,java.awt.Image.SCALE_SMOOTH ))
        };
        JRadioButton[] buttons = new JRadioButton[6];
        ButtonGroup group = new ButtonGroup();
        JPanel panel = new JPanel(new GridLayout(2, 3));
        for (int i = 0; i < 6; i++) {
            buttons[i] = new JRadioButton(characters[i], icons[i], i == 0);
            group.add(buttons[i]);
            panel.add(buttons[i]);
        }
        int result = JOptionPane.showOptionDialog(null, panel, "Select a character",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    }
}
