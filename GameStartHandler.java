import java.awt.*;
import java.awt.event.*;

public class GameStartHandler implements ActionListener {
    // Constants for team names and colors for clarity and reuse
    private static final String TEAM_RED = "RED";
    private static final String TEAM_BLUE = "BLUE";
    private static final Color COLOR_RED = Color.RED;
    private static final Color COLOR_BLUE = Color.BLUE;

    private GameView gameView;
    private GameBoard board;

    // Constructor initializes the gameView and board
    GameStartHandler(GameView gameView, GameBoard board) {
        this.gameView = gameView;
        this.board = board;
    }

    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Start Game <All pieces can move now>");
        // Adds listeners to enable click interactions on the game board
        gameView.addListener();
        // Updates the instruction label with the current team's turn
        gameView.setLabelInstruction("Current Team to move: ");
        // Updates the label to show which team is currently playing
        gameView.setLabelTeam((board.getRedCount() + board.getBlueCount()) % 2 == 0 ? TEAM_RED : TEAM_BLUE, (board.getRedCount() + board.getBlueCount()) % 2 == 0 ? COLOR_RED : COLOR_BLUE);
    }
}

