// Nurul Izzati Afina Binti Sukri 

import javax.swing.*;
import java.awt.*;

public class KwazamChess extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    //private Clip gameMusicClip; // Store the Clip reference for game music

    public KwazamChess() {
        // Set up the frame
        setTitle("Kwazam Chess");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Initialize CardLayout and the main panel to hold different pages
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Attempt to play the game music
        //playGameMusic();

        // Create the game view
        JPanel gamePage = new JPanel(new BorderLayout());

        // Create and initialize the GameBoard
        GameBoard board = new GameBoard();
        board.initialize();

        // Create the GameView with the title "Kwazam Chess"
        GameView gameView = new GameView("Kwazam Chess", board);
        gameView.initialize();

        // Add the GameView to the gamePage
        gamePage.add(gameView, BorderLayout.CENTER);

        // Add the gamePage to the cardPanel
        cardPanel.add(gamePage, "gamePage");

        // Add the cardPanel to the frame
        add(cardPanel);

        // Make the frame visible
        setVisible(true);
    }


    public static void main(String[] args) {
        // Show the Welcome View first
        SwingUtilities.invokeLater(() -> new WelcomeView());
    }
}
