class TransformNothing implements TransformStrategy{
    // Implements the abstract method from the TransformStrategy interface
    @Override
    public void executeTransform(GameBoard board, Piece piece) {
        // Do nothing
        // This strategy represents a scenario where no transformation occurs
    }
}
