import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class FileManager {
    private GameBoard board;
    private GameView gameView;
    private static final int MAX_SAVES = 3; // Maximum number of save files
    
    // Constructor that initializes the FileManager with references to the GameBoard and GameView
    FileManager(GameBoard board, GameView gameView) {
        this.board = board;
        this.gameView = gameView;
    }
    
    // Check if save limit has been reached (all save files full)
    private boolean isSaveLimitReached() {
        for (int i = 1; i <= MAX_SAVES; i++) {
            File file = new File("Save" + i + ".txt");
            if (!file.exists()) {
                return false; // There's space for more saves
            }
        }
        return true; // Save limit reached
    }
    
    // Save method now checks if the limit is reached and prompts the user to delete a file if needed
    public void save(int saveIndex) {
        // If the limit is reached and the file already exists, ask to delete
        if (isSaveLimitReached() && !new File("Save" + saveIndex + ".txt").exists()) {
            gameView.gameDialog("Save limit of " + MAX_SAVES + " files reached. Please delete an existing save or load a game.");
            return;
        }

        // Ask for confirmation if the user tries to overwrite an existing save
        String fileName = "Save" + saveIndex + ".txt"; 
        File file = new File(fileName);
        if (file.exists()) {
            int choice = JOptionPane.showConfirmDialog(null, 
                "Do you want to overwrite " + fileName + "?", 
                "Overwrite Save", JOptionPane.YES_NO_OPTION);
            
            if (choice == JOptionPane.NO_OPTION) {
                return; // User chose not to overwrite
            }
        }

        try {
            FileWriter writer = new FileWriter(fileName);
            savePiece(writer);
            saveStepCounts(writer);
            writer.close();
            gameView.gameDialog(fileName + " saved successfully!");
            System.out.println("\n\nSave Successful to " + fileName + "...\n\n");
        } catch (Exception e) {
            System.out.println(e);
            gameView.gameDialog("Save Failed");
        }
    }
    
    // Load method remains the same, but feedback on file loading success or failure
    public void load(String fileName) {
        ArrayList<String> dataList = new ArrayList<String>();
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                gameView.gameDialog("Error: The selected file does not exist.");
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                dataList.add(scanner.nextLine());
            }
            scanner.close();
            loadStepCounts(dataList);
            loadPieces(dataList);
            gameView.initialize();
            dataList.clear();
            System.out.println("Load Successful from " + fileName + "...");
        } catch (Exception e) {
            System.out.println(e);
            gameView.gameDialog("Error: Failed to load the file.");
        }
    }
    
    // Saves the information of the pieces to the file
    private void savePiece(FileWriter writer) throws Exception {
        // Iterates through each piece on the board and writes its team, type, direction, and coordinates to the file
        for (int i = 0; i < 20; i++) {
            // Writes the team and type of the piece
            writer.write(board.getPiece(i).getTeam() + " " + board.getPiece(i).getClass().getSimpleName());
            writer.write("\nDirection: " + board.getPiece(i).getDirection() + "\n");//save direction of piece
            
            // If the piece is eaten, writes its position as "Piece Eaten"
            if (board.getPiece(i).getX() == -1) {
                writer.write("X Coordinate: Piece Eaten\nY Coordinate: Piece Eaten\n\n");
            }
            else {
                // Saves the coordinates of the piece
                writer.write("X Coordinate: " + Integer.toString(board.getPiece(i).getX()) + "\nY Coordinate: " + Integer.toString(board.getPiece(i).getY()) + "\n\n");
            }
        }
    }
    
    // Saves the current step counts for the players to the file
    private void saveStepCounts(FileWriter writer) throws Exception {
        // Writes the current step count for each player to the file
        writer.write("Current Red Step Count: " + board.getRedCount());
        writer.write("\nCurrent Blue Step Count: " + board.getBlueCount());
    }
    
    // Loads the step counts from the dataList
    private void loadStepCounts(ArrayList<String> datalist) {
        // Extracts and sets the red and blue step counts from the data list
        board.setRedCount(Integer.parseInt(datalist.get(100).substring(24)));
        board.setBlueCount(Integer.parseInt(datalist.get(101).substring(25)));
    }
    
    // Loads the pieces' information from the dataList
    private void loadPieces(ArrayList<String> datalist) {
        String[] loadData;
        
        // Iterates through each piece in the data list, extracting its type, team, direction and coordinates
        for (int i = 0; i < 20; i++) {
            loadData = (datalist.get(i * 5)).split("\\s+"); // Splits the piece type and team
            if (datalist.get(i * 5 + 2).substring(14).equals("Piece Eaten")) {
                board.getPiece(i).setPosition(-1, -1); // Set position for eaten pieces
            }
            else {
                // Sets the position of the pieces
                board.getPiece(i).setPosition(Integer.parseInt(datalist.get(i * 5 + 2).substring(14)), Integer.parseInt(datalist.get(i * 5 + 3).substring(14)));
            }
            // Sets the direction of the pieces
            board.getPiece(i).setDirection(datalist.get(i * 5 + 1).substring(11).toString()); // set  direction
            if (!board.getPiece(i).getClass().getSimpleName().equals(loadData[1])){
                // If the piece type doesn't match, determines the correct transformation
                board.getPiece(i).transform(board);   
            }
        }
    }
    
    // Deletes the save file at the given index (Save1, Save2, or Save3)
    // Method to delete a save file with feedback and error handling
    public void deleteSaveFile(int saveIndex) {
        String fileName = "Save" + saveIndex + ".txt"; // Get the file name
        File file = new File(fileName);

        if (file.exists()) {
            int choice = JOptionPane.showConfirmDialog(null, 
                "Are you sure you want to delete " + fileName + "?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                if (file.delete()) {
                    gameView.gameDialog(fileName + " has been deleted successfully.");
                    System.out.println(fileName + " deleted successfully.");
                } else {
                    gameView.gameDialog("Error: Could not delete " + fileName);
                    System.out.println("Failed to delete " + fileName);
                }
            }
        } else {
            gameView.gameDialog(fileName + " does not exist.");
            System.out.println(fileName + " does not exist.");
        }
    }
}
