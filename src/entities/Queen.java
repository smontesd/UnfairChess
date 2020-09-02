package entities;

import commons.Constants;
import commons.Context;
import game.ChessBoard;

public class Queen extends ChessPiece {

	public Queen(boolean isWhite) {
		super(isWhite);
		
		if (isWhite) {
			setImage(Constants.QUEEN_WHITE);
		} else {
			setImage(Constants.QUEEN_BLACK);
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
		
		int dX = xEnd - xStart;
		int dY = yEnd - yStart;
		
		// Checking if Queen does a knight move (context dependent)
		if (rule == Context.QUEEN_KNIGHT && 
				(Math.abs(dX) == Constants.LONG_L && Math.abs(dY) == Constants.SHORT_L ||
				Math.abs(dX) == Constants.SHORT_L && Math.abs(dY) == Constants.LONG_L)) {
			// final position must be open or occupied by opposing team piece
			ChessPiece piece = board.getPiece(xEnd, yEnd);
			
			if (piece == null || piece.isWhite != this.isWhite) {
				return true;
			}
		}
		// Checking if Queen does a diagonal move
		else if (dX != 0 && Math.abs(dY) == Math.abs(dX)) {
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
				
				// checking if final position is occupied
				if (yStart == yEnd && xStart == xEnd) {
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
					
					return false;
				}
				// path must be clear
				else {
					if (piece == null) {
						continue;
					}
					
					return false;
				}
			}
		}
		// Checking if Queen does a up/down move
		else if (dX == 0 && dY != 0) {
			while (yStart != yEnd) {
				if (dY < 0) {
					yStart -= 1;
				} else {
					yStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (yStart == yEnd) {
					// final position must be open or opposing team piece
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
				}
				// checking if path traveled is empty
				else if (piece == null) {
					continue;
				}
				
				// a piece is in the way
				return false;
			}
		}
		// Checking if Queen does left/right move
		else if (dY == 0 && dX != 0) {
			while (xStart != xEnd) {
				if (dX < 0) {
					xStart -= 1;
				} else {
					xStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (xStart == xEnd) {
					// final position must be open or opposing team piece
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
				}
				// checking if path traveled is empty
				else if (piece == null) {
					continue;
				}
				
				// a piece is in the way
				return false;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite) {
			return Constants.W_QUEEN;
		} else {
			return Constants.B_QUEEN;
		}
	}
}
