public class GameBoard {
    
    private int RedCount = 0; // Tracks the step count for the red player
    private int BlueCount = 0; // Tracks the step count for the blue player
    private static final int PIECE_COUNT = 20; // Total number of pieces in the game
    // Array to store all 20 game pieces (pieces on the board)
    private Piece[] piece = new Piece[PIECE_COUNT];
    // Flag to indicate if the game has been won
    private boolean win = false;
    
    
    // Getters and Setters for game state and step counts
    public int getRedCount() {
        return RedCount;
    }
    
    public int getBlueCount() {
        return BlueCount;
    }
    
    public void setRedCount(int step) {
        RedCount = step;
    }
    
    public void setBlueCount(int step) {
        BlueCount = step;
    }
    
    // Retrieve a specific piece from the array
    public Piece getPiece(int index) {
        return piece[index];
    }
    
    // Get the win status
    public boolean getWin() {
        return win;
    }
    
    // Set the win status
    public void setWin(boolean win) {
        this.win = win;
    }
    
    // Modify the type of a piece in the array (XOR to TOR and vice versa)
    public void modifyPieceType(Piece newPiece,int index) {
        piece[index] = newPiece;
    }

    
    // Initializes the game pieces and sets their initial positions
    public void initialize() {
        RedCount = 0;
        BlueCount = 0;
        win = false;
        System.out.println("Initializing pieces...");
        
        // Initialize blue team pieces
        piece[0] = new TOR("blue", "down");
        piece[0].setPosition(0, 0);
        piece[1] = new BIZ("blue", "down");
        piece[1].setPosition(1, 0);
        piece[2] = new SAU("blue", "down");
        piece[2].setPosition(2, 0);
        piece[3] = new BIZ("blue", "down");
        piece[3].setPosition(3, 0);
        piece[4] = new XOR("blue", "down");
        piece[4].setPosition(4, 0);

        // Initialize blue team's RAM pieces
        for(int i = 0; i < 5; i++) {
            piece[5+i] = new RAM("blue", "down");
            piece[5+i].setPosition(i, 1);
        }
        
        
        // Initialize red team pieces
        piece[10] = new XOR("red", "up");
        piece[10].setPosition(0, 7);
        piece[11] = new BIZ("red", "up");
        piece[11].setPosition(1, 7);
        piece[12] = new SAU("red", "up");
        piece[12].setPosition(2, 7);
        piece[13] = new BIZ("red", "up");
        piece[13].setPosition(3, 7);
        piece[14] = new TOR("red", "up");
        piece[14].setPosition(4, 7);
        
        // Initialize red team's RAM pieces
        for(int i = 0; i < 5; i++) {
            piece[15+i] = new RAM("red", "up");
            piece[15+i].setPosition(i, 6);
        }
        
        System.out.println("Piece initialization complete.");
    }

    // Flips the entire board, changing the positions and directions of all pieces after a player's turn
    public void flip() {
        for(int i = 0; i < PIECE_COUNT; i++) {
            int oriX = piece[i].getX();
            int oriY = piece[i].getY();
            piece[i].setPosition(4-oriX, 7-oriY); // Invert the position
            piece[i].turnAround(); // Change the piece's direction
        }
    }
    
    // Checks if a piece has reached the board's edge and should turn around
    public void checkTurnAround(int currentPiece, int currentX, int currentY) {
        if ((currentY == 0 && piece[currentPiece].getDirection().equals("up"))||(currentY == 7 && piece[currentPiece].getDirection().equals("down"))) { 
            piece[currentPiece].turnAround();
        }
    }
    
    // Checks if XOR and TOR should transform based on the step count
    public void checkTransform() {
        if ((RedCount + BlueCount) % 4 == 0 && (RedCount + BlueCount) != 0) {
            for (int i = 0; i < PIECE_COUNT; i++) {
                if (piece[i] instanceof XOR || piece[i] instanceof TOR) {
                    piece[i].transform(this);
                }
            }
        }
    }
}
