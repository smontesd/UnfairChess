package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commons.Constants;
import entities.ChessPiece;

/**
 * Action Listener to allow for Pawn Promotion
 * 
 * @author Stephen Montes De Oca
 */
public class PromotionAction implements ActionListener {
	// instance variables
	private ChessBoard board;
	private int xPos;
	private int yPos;
	private ChessPiece piece;
	
	/**
	 * Class Constructor for a Promotion Action
	 * 
	 * @param board - instance of chess board at play
	 * @param piece - piece to promote to
	 * @param xPos - x position on board
	 * @param yPos - y position on board
	 */
	public PromotionAction(ChessBoard board, ChessPiece piece,
							int xPos, int yPos) {
		this.board = board;
		this.piece = piece;
		
		if (xPos < 0 || xPos >= Constants.SIZE ||
				yPos < 0 || yPos >= Constants.SIZE) {
			throw new IllegalArgumentException();
		} else {
			this.xPos = xPos;
			this.yPos = yPos;
		}
	}
	
	/**
	 * This method allows for the Graphical User Interface to
	 * Promote a pawn on a given location on the board to become
	 * the specified ChessPiece object given
	 * 
	 * @param e - ActionEvent object that triggered this action to be performed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (board == null) {
			return;
		}
		
		board.setPiece(xPos, yPos, piece);
		board.repaint();
	}

}
