// Chow Shu Ling

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class GameLoadHandler implements ActionListener {
    private FileManager saveFile;

    // Constructor, // Initializes the LoadListener by setting up the FileManager instance
    GameLoadHandler(GameBoard board, GameView gameView) {
        saveFile = new FileManager(board, gameView);
    }

    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent event) {
        // Show a dialog box for the user to choose a file to load
        String[] options = {"Save 1", "Save 2", "Save 3"};
        int choice = JOptionPane.showOptionDialog(null, "Select Save File", "Load Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // Map choice to the corresponding file name
        String fileName = "Save" + (choice + 1) + ".txt";

        if (choice != -1) {
            saveFile.load(fileName); // Load the selected file
        }
    }
}
