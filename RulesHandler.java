// Foo Yau Yun

import java.awt.event.*;
import javax.swing.JOptionPane;

public class RulesHandler implements ActionListener{
    // Implements the actionPerformed() method
    @Override
    public void actionPerformed(ActionEvent e){
        // Displays a dialog box showing the rules and piece movements for playing Kwazam Chess
        JOptionPane.showMessageDialog
        (null, "Kwazam Chess Rules: \n1. The game is played on a 5x8 board. \n2. Each player starts with 5 pieces: RAM, BIZ, SAU, TOR and XOR. \n3. The game ends when a player's SAU piece is captured. \n4. Players take turns moving one piece at a time. \n5. Pieces cannot move off the board or pass over other pieces, except the BIZ. \n6. TOR and XOR can transform to each other after two turns. \n7. Players can capture the opponent’s pieces by landing on their valid move area. \n\n Piece Movements: \n1. RAM: Moves one step forward at a time and turns around when it reaches the edge of the board, unable to skip over other pieces. \n2. BIZ: Moves in a 3x2 L shape, like a knight in chess and can skip over other pieces. \n3. TOR: Moves any distance in a straight line, either horizontally or vertically, but cannot skip over other pieces. After two turns, it transforms into the XOR. \n4. XOR: Moves diagonally for any distance, without skipping over other pieces. After two turns, it transforms back into the TOR. \n5. SAU: Moves one square in any direction and is the key piece that ends the game when captured. ");
    }
}
