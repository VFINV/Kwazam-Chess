import java.awt.event.*;
import javax.swing.JOptionPane;

public class GameExitHandler implements ActionListener {
    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {
        // Displays a confirmation dialog box to confirm the user's intent to exit the game
        int confirmExit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Game", 
        JOptionPane.YES_NO_OPTION);

        // Terminates the application and closes the game window
        if(confirmExit == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}
