package entities;

import commons.Constants;
import commons.Context;
import game.ChessBoard;

/**
 * Concrete class representing a Knight chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Knight extends ChessPiece{

	public Knight(boolean isWhite) {
		super(isWhite);
		
		if (isWhite) {
			setImage(Constants.KNIGHT_WHITE);
		} else {
			setImage(Constants.KNIGHT_BLACK);
		}
	}

	@Override
	public boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd) {
		// Checking move is within valid boundaries
		if (xEnd < 0 || xEnd >= Constants.SIZE || yEnd < 0 || yEnd >= Constants.SIZE) {
			return false;
		}
		
		// Checking context
		Context rule = board.getContext();
		if (rule == Context.FORWARDS_ONLY && !isForwardsMove(yStart, yEnd)) {
			return false;
		} else if (rule == Context.BACKWARDS_ONLY && !isBackwardsMove(yStart, yEnd)) {
			return false;
		}
		
		// Checking in move is L shaped
		int dX = Math.abs(xEnd - xStart);
		int dY = Math.abs(yEnd - yStart);
		
		if (!(dX == Constants.LONG_L && dY == Constants.SHORT_L ||
				dX == Constants.SHORT_L && dY == Constants.LONG_L)) {
			return false;
		}
		
		// Checking if final position is open or occupied by opposing team
		ChessPiece finalPos = board.getPiece(xEnd, yEnd);
		
		if (finalPos == null || finalPos.isWhite != this.isWhite) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite) {
			return Constants.W_KNIGHT;
		} else {
			return Constants.B_KNIGHT;
		}
	}
}
