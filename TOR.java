import java.lang.Math;
import javax.swing.ImageIcon;

public class TOR extends Piece {

    // Constructor for the TOR class, sets the direction and icon based on the team
    // Also sets the transformation strategy to TORtoXOR
    public TOR(String team, String direction) {
        super(team);
        setDirection(direction);
        setIcon(team + "_TOR_" + getDirection() + ".png"); // Set the icon for the TOR piece
        setStrategy(new TORtoXOR()); // Set the transformation strategy to TORtoXOR
    }
    
    // Overrides the abstract move() method from Piece class
    @Override
    public boolean move(int x, int y, GameBoard board, boolean isChecking) {
        
        // Flag indicating whether the TOR might capture a piece
        boolean canCapture = false;
        
        // Iterate through all pieces to check if the TOR can capture a piece at the target location
        for(int i = 0; i < 20; i++) {
            if(board.getPiece(i).getY() == y && board.getPiece(i).getX() == x) {
                if(board.getPiece(i).getTeam() == this.getTeam() ){
                    return false; // Cannot capture a piece from the same team
                }
                else {
                    canCapture = true; // Will capture an opponent's piece
                }
            }
        }
        
        // Check for valid movement based on the direction (vertical or horizontal)
        // Ensure no obstacles are in the path
        if(((Math.abs(x - getX()) <= 8 && y - getY() == 0) || (Math.abs(y - getY()) <= 8 && x - getX() == 0))) {
            // Up
            if(y - getY() < 0) {
                for(int i = 1; i < (getY() - y); i++) {
                    for(int j = 0; j < 20; j++) {
                        if(board.getPiece(j).getY() == (getY() - i) && board.getPiece(j).getX() == x) {
                            return false;
                        }
                    }
                }
            }
            // Down
            else if(y - getY() > 0) {
                for(int i = 1; i < y - getY(); i++) {
                    for(int j = 0; j < 20; j++) {
                        if(board.getPiece(j).getY() == (getY() + i) && board.getPiece(j).getX() == x) {
                            return false;
                        }
                    }
                }
            }
            // Left
            else if(x - getX() < 0) {
                for(int i = 1; i < getX() - x; i++) {
                    for(int j = 0; j < 20; j++) {
                        if(board.getPiece(j).getY() == y && board.getPiece(j).getX() == getX() - i) {
                            return false;
                        }
                    }
                }
            }
            // Right
            else if(x - getX() > 0) {
                for(int i = 1; i < x - getX(); i++) {
                    for(int j = 0; j < 20; j++) {
                        if(board.getPiece(j).getY() == y && board.getPiece(j).getX() == getX() + i) {
                            return false;
                        }
                    }
                }
            }
            if(!isChecking) {
                if(canCapture) {
                    capture(x, y, board); // Captures the piece after checking
                }
                setPosition(x, y); // Sets the new position of the TOR
            }
            return true;
        }
        else{
            return false;
        }
    }
    
    // Overrides the getIcon() method from the Piece class to provide the icon for the TOR piece
    @Override
    public ImageIcon getIcon() {
        return loadImage(getTeam() + "_TOR_" + getDirection() + ".png"); // Get the appropriate icon for the TOR
    }
}
