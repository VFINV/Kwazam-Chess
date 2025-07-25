import javax.swing.ImageIcon;
import java.awt.*;

public abstract class Piece {
    
    private int x; // x-coordinate of the piece's position on the board
    private int y; // y-coordinate of the piece's position on the board
    private String team; // Team of the piece
    private String direction = "up"; // Direction the piece is facing
    private TransformStrategy strategy = new TransformNothing(); // Default transformation strategy, no transformation
    ImageIcon icon = null; // Icon representing the piece on the board
    
    // Load an image from the specified path and return it as a scaled ImageIcon
    protected static ImageIcon loadImage(String path) { 
        Image image = new ImageIcon(Piece.class.getResource(path)).getImage(); //
        Image scaledImage = image.getScaledInstance(230, 230, java.awt.Image.SCALE_SMOOTH); //load image
        return new ImageIcon(scaledImage); // Return the scaled ImageIcon
    }
    
    // Constructor for the Piece class, initializes the team attribute
    public Piece(String team) {
        this.team = team; //
    }

    // Abstract method that should be implemented by each subclass to define piece-specific movement behavior
    public abstract boolean move(int x, int y, GameBoard board, boolean isChecking);
    
    // Handles the logic when a piece is captured
    // If the captured piece is the SAU, it sets the game to a win state
    // Sets the position of captured pieces to (-1, -1), effectively removing them from the board
    private void handleCapturedPiece(GameBoard board, int i) {
        if (board.getPiece(i).getClass() == SAU.class) {
            board.setWin(true); // Game is won if the SAU is captured
        }
        board.getPiece(i).setPosition(-1, -1); //
    }
    
    // Iterates through all pieces on the board to check if any piece is at the specified position
    // Calls handleCapturedPiece() if a piece is found at the given position
    public void capture(int x, int y, GameBoard board) {
        for(int i = 0; i < 20; i++) {
            if(board.getPiece(i).getX() == x && board.getPiece(i).getY() == y) {
                handleCapturedPiece(board, i); // Handles the captured piece
            }
        }
    }

    // Changes the direction of the piece (up or down)
    public void turnAround() {
        if(direction.equals("up")) {
            direction = "down"; // Turn the piece around
        }
        else if(direction.equals("down")) {
            direction = "up"; // Turn the piece around
        }
    }
    
    // Sets the icon for the piece using the specified image path.
    public void setIcon(String path) {
        icon = loadImage(path); // Load and set the piece's icon
    }
    
    // Get the current icon of the piece
    public ImageIcon getIcon() {
        return icon;
    }
    
    // Assign the team to the piece
    public void setTeam(String team) {
        this.team = team;
    }
    
    // Get the team of the piece
    public String getTeam() {
        return team;
    }

    // Assign the direction to the piece
    public void setDirection(String direction) {
        this.direction = direction;
    }
    
    // Get the direction of the piece
    public String getDirection() {
        return direction;
    }
    
    // Sets the strategy for the piece's transformation (used for transformation between piece types)
    public void setStrategy(TransformStrategy strategy) {
        this.strategy = strategy; // Assign a transformation strategy to the piece
    }
    
    // Executes the transformation strategy (used when the piece needs to transform, like TOR to XOR)
    public void transform(GameBoard board) {
        strategy.executeTransform(board, this); // Perform the transformation based on the strategy
    }
    
    // Sets the position (x, y) of the piece on the board
    public void setPosition(int x,int y) {
        this.x = x; // Set the x-coordinate
        this.y = y; // Set the y-coordinate
    }
    
    // Get the x-coordinate
    public int getX() {
        return x;
    }
    
    // Get the y-coordinate
    public int getY() {
        return y;
    }
}
