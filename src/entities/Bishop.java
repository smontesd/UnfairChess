package entities;

import commons.Constants;
import commons.Context;
import game.ChessBoard;

/**
 * Concrete class representing a Bishop chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Bishop extends ChessPiece {

	public Bishop(boolean isWhite) {
		super(isWhite);
		
		if (isWhite) {
			setImage(Constants.BISHOP_WHITE);
		}
		else {
			setImage(Constants.BISHOP_BLACK);
		}
	}

	@Override
	public boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd) {
		// checking if move is within valid boundaries
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
		
		int dX = xEnd - xStart;
		int dY = yEnd - yStart;
		
		// |dY/dx| = 1 means valid move
		if (dX != 0 && Math.abs(dX) == Math.abs(dY)) {
			while (yStart != yEnd && xStart != xEnd) {
				if (dY < 0) {
					yStart -= 1;
				} else {
					yStart += 1;
				}
				
				if (dX < 0) {
					xStart -= 1;
				} else {
					xStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (yStart == yEnd && xStart == xEnd) {
					// Final position must be open or opposite team piece
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
					
					return false;
				} else {
					if (piece == null) {
						continue;
					}
					
					if (piece.isWhite == this.isWhite &&
							rule == Context.BISHOP_GHOST){
						continue;
					}
					
					return false;
				}
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite) {
			return Constants.W_BISHOP;
		} else {
			return Constants.B_BISHOP;
		}
	}
}
