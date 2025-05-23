import java.awt.event.*;


class GamePauseHandler implements ActionListener {
    private GameView boardView;
    
    // Constructor to initialize the boardView
    GamePauseHandler(GameView boardView){
        this.boardView = boardView;
    }

    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Game Paused <All pieces cannot move>");
        // Resets the board to its initial state by calling the initialize() method on the boardView
        boardView.initialize();
    }
}

