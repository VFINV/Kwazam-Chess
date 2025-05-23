import java.awt.Color;
import java.awt.event.*;

public class PlayerClickHandler implements ActionListener {
    private static boolean hasSelectedPiece = false;
    private static int currentPiece;
    private GameBoard board;
    private GameView gameView;

    // Constructor initializes the PlayerClickListener with board and gameView references
    PlayerClickHandler(GameBoard board, GameView gameView) {
        this.board = board;
        this.gameView = gameView;
    }

    // Sets the flag to indicate whether a piece has been selected
    public static void setHasSelectedPiece(boolean selected) {
        hasSelectedPiece = selected;
    }
    
    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] position = e.getActionCommand().toString().split("\\s+");
        int currentX = Integer.parseInt(position[1]);
        int currentY = Integer.parseInt(position[0]);
    
        System.out.println(position[0] + " " + position[1] + " was clicked");
    
        // Determines whether a piece is selected or if the player wants to make a move
        if (!hasSelectedPiece) {
            handlePieceSelection(currentX, currentY);
        } else {
            handlePieceMove(currentX, currentY);
        }
    
        System.out.println("Has Selected: " + hasSelectedPiece + "\n");
    }
    
    // Handles the selection of a piece based on the clicked cell
    private void handlePieceSelection(int currentX, int currentY) {
        String currentTeam = determineCurrentTeam();
    
        // Checks if the clicked cell contains a valid piece
        for (int i = 0; i < 20; i++) {
            if (isValidPieceSelection(i, currentX, currentY, currentTeam)) {
                performPieceSelectionActions(i);
                break;
            }
        }
    
        if (!hasSelectedPiece) {
            gameView.gameDialog("Please choose a " + currentTeam + " piece");
        }
    }
    
    // Handles the movement of the selected piece
    private void handlePieceMove(int currentX, int currentY) {
        System.out.println("Now selected , currentX : " + currentX + "   currentY : " + currentY);
    
        // Checks if the move is valid and performs the corresponding actions
        if (isValidPieceMove(currentX, currentY)) {
            performValidPieceMoveActions();
        } else {
            gameView.gameDialog("Invalid move!");
        }
    
        resetBoardColors();
        hasSelectedPiece = false;
    }
    
    // Determines the current player's team based on the step count
    private String determineCurrentTeam() {
        return (board.getRedCount() + board.getBlueCount()) % 2 == 0 ? "red" : "blue";
    }
    
    // Checks if the selected piece belongs to the current team
    private boolean isValidPieceSelection(int pieceIndex, int currentX, int currentY, String currentTeam) {
        return board.getPiece(pieceIndex).getX() == currentX &&
               board.getPiece(pieceIndex).getY() == currentY &&
               board.getPiece(pieceIndex).getTeam().equals(currentTeam);
    }
    
    // Performs actions when a piece is successfully selected
    private void performPieceSelectionActions(int selectedPieceIndex) {
        System.out.println("Now Choosen: " + selectedPieceIndex + "   Class: " + board.getPiece(selectedPieceIndex).getClass());
        System.out.println("Direction:" + board.getPiece(selectedPieceIndex).getDirection());
    
        // Sets the selected piece
        currentPiece = selectedPieceIndex;
        // Updates the hasSelectedPiece flag
        hasSelectedPiece = true;
        // Change the cell color
        gameView.changeBoardColor(board.getPiece(selectedPieceIndex).getX(), board.getPiece(selectedPieceIndex).getY(), new Color(162, 195, 243));
        // Highlight the cells of valid moves
        highlightValidMoves();
        // Updates the board view
        gameView.updateView();
    }
    
    // Highlights valid moves for the selected piece
    private void highlightValidMoves() {
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 5; k++) {
                if (board.getPiece(currentPiece).move(k, j, board, true)) {
                    gameView.changeBoardColor(k, j, new Color(248, 236, 124));
                }
            }
        }
    }
    
    // Checks if the move to the clicked cell is valid for the selected piece
    private boolean isValidPieceMove(int currentX, int currentY) {
        return board.getPiece(currentPiece).move(currentX, currentY, board, false);
    }
    
    // Executes actions when a valid piece move occurs
    private void performValidPieceMoveActions() {
        System.out.println("Selected piece Move successfully");
        String currentTeam = determineCurrentTeam();
        
        // Updates the player step counts
        updatePlayerStepCount();
        
        // Update the view
        gameView.updateStepCounts(); // Update the red and blue step counts in the UI

        // Checks for win condition
        if (board.getWin()) {
            handleGameWin(currentTeam);
        } else {
            handleAdditionalChecks();
        }
        
        // Updates the board view
        gameView.updateView();
    }
    
    // Updates the step count for the current player and sets the label color
    private void updatePlayerStepCount() {
        if ((board.getRedCount() + board.getBlueCount()) % 2 == 0) {
            board.setRedCount(board.getRedCount() + 1);
            gameView.setLabelTeam("BLUE", new Color(0, 0, 255));
        } else {
            board.setBlueCount(board.getBlueCount() + 1);
            gameView.setLabelTeam("RED", new Color(255, 0, 0));
        }
    }
    
    // Handles the case where the current player wins the game
    private void handleGameWin(String currentTeam) {
        gameView.gameDialog("Congratulations, " + currentTeam + " team is the winner!");
        board.initialize();
        gameView.initialize();
    }
    
    // Performs additional checks after a valid move
    private void handleAdditionalChecks() {
        // Checks for a transformation
        board.checkTransform();
        // Checks if a piece needs to change direction
        board.checkTurnAround(currentPiece, board.getPiece(currentPiece).getX(), board.getPiece(currentPiece).getY());
        // Flips the board
        board.flip();
    }
    
    // Resets the background color of all cells to white
    private void resetBoardColors() {
        for (int j = 0; j < 8; j++) {
            for (int k = 0; k < 5; k++) {
                gameView.changeBoardColor(k, j, Color.white);
            }
        }
    }
}


