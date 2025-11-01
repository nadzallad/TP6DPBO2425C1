import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    //geme window dimension
    int frameWidth = 360;
    int frameHeight = 640;

    //player initial position & size
    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    // pipe initial position and size
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 34;
    int pipeHeight = 512;

    //reference to view
    View view;

    // bird image and object
    Image birdImage;
    Player player;

    // pipe image and lst of pipes
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    //timer for main loop and pipe
    Timer gameLoop;
    Timer pipesCooldown;

    // game value
    int gravity = 1;
    int pipeVelocityX = -2;

    // Game state flags and score counter
    boolean gameOver = false;
    boolean started = false;
    int score;

    // constructor
    public Logic () {
        //load bird image and create player object
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        //load pipe images
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        // Timer for generating new pipes every 1.5 seconds
        pipesCooldown = new Timer(1500, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa");
                placePipes(); //create new pair of pipe
            }
        } );
        pipesCooldown.start();

        // Main game loop running at 60 frames per second
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    // Setters and getters
    public void setView(View view ){this.view = view;}
    public Player getPlayer(){return player;}
    public ArrayList<Pipe> getPipes() {return pipes;}
    public boolean isGameOver() {return gameOver;}
    public int getScore() {return score;}

    // Method to create a pair of upper and lower pipe
    public void placePipes(){

        int randomPosY = (int)(pipeStartPosY - pipeHeight/4 -Math.random()*(pipeHeight/2));
        int openingSpace = frameHeight /4;

        //create upper pipe
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        //create lower pipe
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
        System.out.println(lowerPipe.getPosX());
    }

    // Main movement logic (bird and pipes)
    public void move(){
        if(!started || gameOver)return;

        // Apply gravity to the bird
        player.setVelocityY(player.getVelocityY()+ gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));
        player.setPosX(Math.min(player.getPosX(), 600));

        //pipe move to the left
        for(int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i);
                pipe.setPosX(pipe.getPosX() + pipeVelocityX);
        }

        // Detection between bird and pipes
        Rectangle birdRect = new Rectangle(player.getPosX(), player.getPosY(), playerWidth, playerHeight);
        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipeWidth, pipeHeight);
            if (birdRect.intersects(pipeRect)) {
                gameOver = true;
                return;
            }
        }

        //check if bird hits the ground
        if (player.getPosY() + playerHeight >= frameHeight - 50) {
            gameOver = true;
        }

        // score increases when the bird passes the pipes
        for (Pipe pipe : pipes) {
            if (!pipe.isPassed() && pipe.getPosX() + pipeWidth < player.getPosX() && pipe.getImage() == lowerPipeImage) {
                pipe.setPassed(true);
                score++;
            }
        }

    }

    //restart the game
    public void restartGame() {
        pipes.clear();
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        score = 0;
        gameOver = false;
        started = false;
        pipesCooldown.restart();
    }

    // Called automatically by Timer every frame (60 FPS
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        if (view != null) {
            view.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    // Keyboard input handler
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            started = true; // start the game
            player.setVelocityY(-10); //bird fly
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            restartGame(); //restart
        }
    }
    public void keyReleased(KeyEvent e){}


}
