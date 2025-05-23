import javax.swing.ImageIcon;

public class BIZ extends Piece {

    // Constructor for the BIZ class, sets the direction and icon based on the team
    public BIZ(String team, String direction) {
        super(team);
        setDirection(direction);
        setIcon(team + "_BIZ_" + getDirection() + ".png"); // Set the icon for the BIZ piece
    }

    // Overrides the abstract move() method from the Piece class
    @Override
    public boolean move(int x, int y, GameBoard board, boolean isChecking) {
        
        int horizontalDistance = Math.abs(x - getX()); // Calculate the horizontal distance
        int verticalDistance = Math.abs(y - getY()); // Calculate the vertical distance

        // Check if the movement forms a valid "L" shape (1 step in one direction, 2 in the other)
        if ((horizontalDistance == 2 && verticalDistance == 1) || (horizontalDistance == 1 && verticalDistance == 2)) {
            // Check if the destination is empty or contains an opponent's piece
            Piece destinationPiece = null;

            // Iterate through all pieces to find the piece at the destination
            for (int j = 0; j < 20; j++) {
                if (x == board.getPiece(j).getX() && y == board.getPiece(j).getY()) {
                    destinationPiece = board.getPiece(j);
                    break;
                }
            }
            // If the destination is empty or contains an opponent's piece, the move is valid
            if (destinationPiece == null || destinationPiece.getTeam() != getTeam()) {
                if (!isChecking) {
                    if (destinationPiece != null) {
                        capture(x, y, board); // Capture the opponent's piece if present
                    }
                    setPosition(x, y); // Set the new position of the BIZ piece
                }
                return true; // Move is valid
            }
        }
        return false; // Return false if the move is not valid
    }
    
    // Overrides the getIcon() method from the Piece class to provide the icon for the BIZ piece
    @Override
    public ImageIcon getIcon() {
        return loadImage(getTeam() + "_BIZ_" + getDirection() + ".png"); // Get the appropriate icon for the BIZ
    }
}


