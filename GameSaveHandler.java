import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import java.io.*;

public class GameSaveHandler implements ActionListener {
    private FileManager saveFile;

    // Constructor initializes the FileManager for saving the game data
    GameSaveHandler(GameBoard board, GameView gameView) {
        saveFile = new FileManager(board, gameView);
    }

    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent event) {
        // Check if all save files exist
        if (allSaveFilesExist()) {
            int choice = JOptionPane.showConfirmDialog(null,
                    "All save slots are full. Do you want to delete an existing save file?",
                    "Delete Save File", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                String[] options = {"Save 1", "Save 2", "Save 3"};
                int fileChoice = JOptionPane.showOptionDialog(null,
                        "Choose which save file to delete",
                        "Delete Save File",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]);
                
                // Delete the selected save file
                if (fileChoice != -1) {
                    saveFile.deleteSaveFile(fileChoice + 1);
                }
            } else {
                return; // User chose not to delete any file
            }
        }
        
        // Show a dialog box for the user to select a save file (1, 2, or 3)

        String[] options = {"Save 1", "Save 2", "Save 3"};
        int choice = JOptionPane.showOptionDialog(null, "Select Save File", "Save Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        int saveIndex = choice + 1;

        if (choice != -1) {
            saveFile.save(saveIndex); // Save to the selected file
        }
    }

    // Check if all save files exist (i.e., all are full)
    private boolean allSaveFilesExist() {
        for (int i = 1; i <= 3; i++) {
            File file = new File("Save" + i + ".txt");
            if (!file.exists()) {
                return false; // If any file doesn't exist, return false
            }
        }
        return true; // All save files exist
    }
}
