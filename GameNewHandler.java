import java.awt.event.*;
import javax.swing.JOptionPane;

public class GameNewHandler implements ActionListener {
    private GameView GameView;
    private GameBoard board;
    
    // Constructor initializes the GameBoard and GameView
    GameNewHandler(GameBoard board, GameView GameView) {
        this.board = board;
        this.GameView = GameView;
    }

    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {
        int confirmNewGame = JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new game? Current progress will lost.", "New Game",
        JOptionPane.YES_NO_OPTION);
        // Resets the board and the board view to start a new game
        if (confirmNewGame == JOptionPane.YES_OPTION) {
            board.initialize();
            GameView.initialize();
        }
    }
}
