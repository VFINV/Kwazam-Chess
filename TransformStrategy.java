// Leticia Lai Suh Hooi 

// Defines the TransformStrategy interface
public interface TransformStrategy{
    // Abstract method to be implemented by classes that implement TransformStrategy
    // This method handles the transformation logic for the given piece
    public void executeTransform(GameBoard board, Piece piece);
}
