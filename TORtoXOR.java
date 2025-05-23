public class TORtoXOR implements TransformStrategy {
    // Implements the abstract method from the TransformStrategy interface
    @Override
    public void executeTransform(GameBoard board, Piece piece) {
        // Iterates through all pieces on the board to find the one matching the given piece's coordinates
        for(int i = 0; i < 20; i++) {
            if(board.getPiece(i).getX() == piece.getX() && board.getPiece(i).getY() == piece.getY()) {
                // Creates a new XOR piece with the same team and direction as the original piece
                Piece TORtoXOR = new XOR(board.getPiece(i).getTeam(),piece.getDirection());
                // Sets the position of the new XOR piece to match the original piece's position
                TORtoXOR.setPosition(piece.getX(), piece.getY());
                // Replaces the original piece with the new XOR piece
                board.modifyPieceType(TORtoXOR, i);
                break;
            }
        }
    }
}
