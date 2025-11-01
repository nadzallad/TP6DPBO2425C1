import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    //atributes for storing image
    private Image backgroundImage;
    private Image lowerPipeImage;
    private Image upperPipeImage;

    //constructor to build the main menu window
    public MainMenu() {
        //basic window setup
        setTitle("Flappy Bird - Main Menu");
        setSize(360, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // load images form the assets folder
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Custom panel to draw the background and pipes
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image to fill the window
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

                // Set pipe dimensions
                int pipeWidth = 60;
                int pipeHeight = 400;

                // Draw the upper pipe
                g.drawImage(upperPipeImage, 200, -200, pipeWidth, pipeHeight, null);

                // Draw the lower pipe
                g.drawImage(lowerPipeImage, 200, 400, pipeWidth, pipeHeight, null);

                // Game title
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 36));
                g.drawString("FLAPPY BIRD", 65, 170);
            }
        };
        backgroundPanel.setLayout(new GridBagLayout());  // center buttons on the panel
        backgroundPanel.setOpaque(false); // make panel transparent


        // Play button
        JButton playButton = new JButton("Play Game");
        playButton.setFont(new Font("Arial", Font.BOLD, 20));
        playButton.setBackground(new Color(255, 200, 0));
        playButton.setFocusPainted(false);

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setBackground(new Color(255, 100, 100));
        exitButton.setFocusPainted(false);

        // add action listener for play button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close the main menu and open the game window
                dispose();
                openGame();
            }
        });

        //add action listener for the exit button
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Panel to hold both buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 20)); // 2 rows, 1 column, vertical spacing
        buttonPanel.setOpaque(false); // transparent so background is visible
        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);

        // Add button panel to the background panel
        backgroundPanel.add(buttonPanel);
        // Add the background panel to the main frame
        add(backgroundPanel);

        // Show the window
        setVisible(true);
    }

    // Method to open the actual Flappy Bird game window
    private void openGame() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Connect the logic and view components of the game
        Logic logic = new Logic();
        View view = new View(logic);
        logic.setView(view);

        // Add the view to the frame
        frame.add(view);
        frame.pack();
        frame.setVisible(true);

        // Request focus so keyboard input works properly
        view.requestFocus();
    }

    // Main method to launch the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}
