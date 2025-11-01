import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    //window size
    int width = 360;
    int height = 640;

    //game components
    private Logic logic;
    private  Image backgroundImage;

    //constructor
    public View(Logic logic) {
        this.logic = logic;

        // Set the preferred size of the game window
        setPreferredSize(new Dimension(width, height));

        // Load the background image from the assets folder
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // Enable keyboard input
        setFocusable(true);
        addKeyListener(logic);

    }

    //Painting area
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    // Draw all game elements
    public void draw(Graphics g) {
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, width, height, null);
        } else {
            // Fallback color if image is missing
            g.setColor(Color.cyan);
            g.fillRect(0, 0, width, height);
        }

        // Draw Player (the bird)
        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        //Draw pipes
        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null){
            for(int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        // display score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + logic.getScore(), 20, 50);

        // show game over massage
        if (logic.isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 80, 250);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.setColor(Color.WHITE);
            g.drawString("Press R to Restart", 90, 290);
        }

    }
}

