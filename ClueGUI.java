import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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
}
