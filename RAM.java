// Nurul Izzati Afina Binti Sukri 

import javax.swing.ImageIcon;

public class RAM extends Piece {

    // Constructor for the RAM class, sets the direction and icon based on the team
    public RAM(String team, String direction) {
        super(team);
        setDirection(direction);
        setIcon(team + "_RAM_" + getDirection() + ".png"); // Set the icon for the RAM piece
    }

    // Overrides the abstract move() method from the Piece class
    @Override
    public boolean move(int x, int y, GameBoard board, boolean isChecking) {
        
        // Flag indicating whether the RAM might capture a piece
        boolean canCapture = false; 
        
        int deltaY = y - getY();        
        int deltaX = x - getX(); 
 
        // Iterate through all pieces to check if the RAM can capture a piece at the target location
        for (int i = 0; i < 20; i++) {
            if (board.getPiece(i).getY() == y && board.getPiece(i).getX() == x) {
                if (board.getPiece(i).getTeam() == this.getTeam()) {
                    return false; // Cannot move to a square occupied by own team
                } else {
                    canCapture = true; // Might capture an opponent's piece
                }
            }
        }

        // If the direction is up, check if moving up one step is valid
        if (getDirection().equals("up")) {
            if (deltaY == -1 && deltaX == 0) { // Only allow one step
                if (!isChecking) {
                    if (canCapture) {
                        capture(x, y, board); // Capture the piece after checking
                    }
                    setPosition(x, y); // Set the new position of the RAM
                }
                return true; // Move is valid
            }
        }
        
        // If the direction is down, check if moving down one step is valid
        else {
            if (deltaY == 1 && deltaX == 0) { // Only allow one step
                if (!isChecking) {
                    if (canCapture) {
                        capture(x, y, board); // Capture the piece after checking
                    }
                    setPosition(x, y); // Set the new position of the RAM
                }
                return true; // Move is valid
            }
        }
        return false; // Return false if the move is not valid
    }

    // Overrides the getIcon() method from the Piece class to provide the icon for the RAM piece
    @Override
    public ImageIcon getIcon() {
        return loadImage(getTeam() + "_RAM_" + getDirection() + ".png"); // Get the appropriate icon for the RAM
    }
}
