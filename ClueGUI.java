import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class ClueGUI extends JFrame {
    private JPanel window;
    public static final Image HOME_SCREEN = loadImage("Clue-home-page.png");
    public static final Image LOGO = loadImage("CLUE_logo.png");
    private static final String IMAGE_PATH = "resources/";
    private Command currentCommand;

    public ClueGUI() {
        currentCommand = new StartGameCommand(this);
        currentCommand.execute();
        setLocationRelativeTo(null);
    }

    public void homeScreen() {

        this.setTitle("Clue Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window = new JPanel() {
            protected void paintComponent(Graphics g) {
                g.drawImage(HOME_SCREEN, 0, 0, this);
                g.drawImage(LOGO,0,0,this);
            }
        };
        window.setPreferredSize(new Dimension(965, 800) );

        JButton startButton = new JButton("Start Game");
        startButton.setBounds(400, 450, 150, 50);
        startButton.addActionListener(e -> {
            currentCommand = new ShowSetupDialogCommand(this);
            currentCommand.execute();
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

    public void showSetupDialog() {
        // Dialog box to get the number of players
        int numPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of players (3-6):"));

        // Dialog box to select a character for each player
        String[] characters = {"Miss Scarlet", "Professor Plum", "Reverend Green", "Colonel Mustard", "Mrs. Peacock", "Dr. Orchid" };
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
