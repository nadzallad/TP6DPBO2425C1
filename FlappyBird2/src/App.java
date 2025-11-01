import javax.swing.*;

public class App  {
    //main method
    public static void main(String[] args) {

        //main game window
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        //Connect the game logic and visual components
        Logic logika = new Logic();
        View tampilan = new View(logika);
        logika.setView(tampilan);

        //add view to the frame
        frame.add(tampilan);
        frame.pack();
        frame.setVisible(true);

        // ensure keyboard input is directed to the View component
        tampilan.requestFocus();
    }
}
