// Foo Yau Yun

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameHomeHandler implements ActionListener {
    private GameView gameView; // Reference to the current game view

    public GameHomeHandler(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to go back to the main menu?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            // Stop background music
            gameView.stopBackgroundMusic();

            // Dispose of the current GameView
            gameView.dispose();

            // Open the WelcomeView
            new WelcomeView();
        }
    }
}
