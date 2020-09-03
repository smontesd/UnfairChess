package entities;

import commons.Constants;
import game.ChessBoard;

/**
 * Concrete class representing a Pawn chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class King extends ChessPiece {
	private boolean firstMove;

	public King(boolean isWhite) {
		super(isWhite);
		
		if (isWhite) {
			setImage(Constants.KING_WHITE);
		} else {
			setImage(Constants.KING_BLACK);
		}
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
	
	public void completeFirstMove() {
		firstMove = true;
	}

	@Override
	public boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite) {
			return Constants.W_KING;
		}
		
		return Constants.B_KING;
	}
}
