// Nurul Izzati Afina Binti Sukri 

import javax.swing.ImageIcon;

public class XOR extends Piece {
    
    // Constructor for the XOR class, sets the direction and icon based on the team
    // Sets the transformation strategy to XORtoTOR
    public XOR(String team, String direction) {
        super(team);
        setDirection(direction);
        setIcon(team + "_XOR_" + getDirection() + ".png"); // Set the icon for the XOR piece
        setStrategy(new XORtoTOR()); // Set the transformation strategy to XORtoTOR
    }
    
    // Overrides the abstract move() method from Piece class
    @Override
    public boolean move(int x, int y, GameBoard board, boolean isChecking) {
        
        // Flag indicating whether the XOR might capture a piece
        boolean canCapture = false;
        
        
        // Checks if the movement is along a diagonal
        if(Math.abs(x - getX()) == Math.abs(y - getY())) {
            // Iterate through all pieces to check if the XOR can capture a piece at the target location
            for(int j = 0; j < 20; j++) {
                if(x == board.getPiece(j).getX() && y == board.getPiece(j).getY()) {
                    if(board.getPiece(j).getTeam() != this.getTeam()) {
                        canCapture = true; // Will capture an opponent's piece
                     }
                     else {
                         return false; // Cannot capture a piece from the same team
                     }
                }
            }
            
            // Check for valid movement along the diagonal and ensure no obstacles are in the path
            for(int i = 1; i < Math.abs(y - getY()); i++) {
                if(x - getX() < 0) {
                    // Upper left
                    if(y - getY() < 0) {
                        for(int j = 0; j < 20; j++) {
                            if(getX() - i == board.getPiece(j).getX() && getY() - i == board.getPiece(j).getY()) {
                                return false;
                            }
                        }
                    }
                    // Lower left
                    else if(y - getY() > 0) { 
                        for(int j = 0; j < 20; j++) {
                            if(getX() - i == board.getPiece(j).getX() && getY() + i == board.getPiece(j).getY()) {
                                return false;
                            }
                        }
                    }
                }
                else if(x - getX() > 0) {
                    // Upper right
                    if(y - getY() < 0) { 
                        for(int j = 0; j < 20; j++) {
                            if(getX() + i == board.getPiece(j).getX() && getY() - i == board.getPiece(j).getY()) {
                                return false;
                            }
                        }
                    }
                    // Lower right
                    else if(y - getY() > 0) { 
                        for(int j = 0; j < 20; j++) {
                            if(getX() + i == board.getPiece(j).getX() && getY() + i == board.getPiece(j).getY()) {
                                return false;
                            }
                        }
                    }
                }
            }
            if(!isChecking) {
                if(canCapture) { 
                    // Capture the piece after checking
                    capture(x, y, board);
                }
                setPosition(x, y); // Sets the new position of the XOR
            }
            return true;
        }
        else {
            return false;
        }
    }
        
    // Overrides the getIcon() method from the Piece class to provide the icon for the XOR piece
    @Override
    public ImageIcon getIcon() {
        return loadImage(getTeam() + "_XOR_" + getDirection() + ".png"); // Get the appropriate icon for the XOR
    }
}
