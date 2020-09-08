package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to create a Pass Turn button for the Unfair Chess
 * java program.
 * 
 * @author Stephen Montes De Oca
 */
public class PassAction implements ActionListener {
	private ChessBoard board;
	
	/**
	 * Pass Action constructor
	 * 
	 * @param board - chess board to call the next turn
	 */
	public PassAction(ChessBoard board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (board == null) {
			return;
		}
		
		board.nextTurn();
		board.repaint();
	}
}
