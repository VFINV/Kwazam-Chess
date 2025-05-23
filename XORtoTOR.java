public class XORtoTOR implements TransformStrategy {
    // Implements the abstract method from the TransformStrategy interface
    @Override
    public void executeTransform(GameBoard board, Piece piece) {
        // Iterates through all pieces on the board to find the one matching the given piece's coordinates
        for(int i = 0; i < 20; i++) {
            if(board.getPiece(i).getX() == piece.getX() && board.getPiece(i).getY() == piece.getY()) {
                // Creates a new TOR piece with the same team and direction as the original piece
                Piece XORtoTOR = new TOR(piece.getTeam(),piece.getDirection());
                // Sets the position of the new TOR piece to match the original piece's position
                XORtoTOR.setPosition(piece.getX(), piece.getY());
                // Replaces the original piece with the new TOR piece
                board.modifyPieceType(XORtoTOR, i);
                break;
            }
        }
    }
}
