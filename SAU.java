import javax.swing.ImageIcon;

public class SAU extends Piece {
    
    // Constructor for the SAU class, sets the direction and icon based on the team
    public SAU(String team, String direction) {
        super(team);
        setDirection(direction);
        setIcon(team + "_SAU_" + getDirection() + ".png"); // Set the icon for the SAU piece
    }
    
    
    // Overrides the abstract move() method from the Piece class
    @Override
    public boolean move(int x, int y, GameBoard board, boolean isChecking) {
        
        // Flag indicating whether the SAU might capture a piece
        boolean canCapture = false;
        
        // Iterate through all pieces to check if the SAU can capture a piece at the target location
        for(int i = 0; i < 20; i++) {
            if(board.getPiece(i).getX() == x && board.getPiece(i).getY() == y) {
                if(board.getPiece(i).getTeam() == this.getTeam()) {
                    return false; // Cannot capture a piece from the same team
                }
                else {
                    canCapture = true; // Will capture an opponent's piece
                }
            }
        }
        
        // Check if the movement is within 1 step in any direction
        if(Math.abs(x - getX()) <= 1 && Math.abs(y - getY()) <= 1) {
            if(!isChecking) {
                if(canCapture) {
                    capture(x, y, board); // Capture the opponent's piece if present
                }
                setPosition(x, y); // Set the new position of the SAU piece
            }
            
            return true; // Move is valid
        }
        else {
            return false; // Return false if the move is not valid
        }
    }
    
    // Overrides the getIcon() method from the Piece class to provide the icon for the SAU piece
    @Override
    public ImageIcon getIcon() {
        return loadImage(getTeam() + "_SAU_" + getDirection() + ".png"); // Get the appropriate icon for the SAU
    }
}
