package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to create a Resign action for the Unfair Chess
 * java program.
 * 
 * @author Stephen Montes De Oca
 */
public class ResignAction implements ActionListener {
	private ChessBoard board;
	
	public ResignAction(ChessBoard board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (board == null) {
			return;
		}
		
		board.resign();
	}

}
