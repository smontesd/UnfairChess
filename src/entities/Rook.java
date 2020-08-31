package entities;

import commons.Constants;
import game.ChessBoard;

/**
 * Concrete class representing a Rook chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Rook extends ChessPiece {
	
	public Rook(boolean isWhite) {
		super(isWhite);
		
		if (isWhite) {
			setImage(Constants.ROOK_WHITE);
		} else {
			setImage(Constants.ROOK_BLACK);
		}
	}
	
	public boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd) {
		if (xEnd < 0 || xEnd >= Constants.SIZE || yEnd < 0 || yEnd >= Constants.SIZE) {
			return false;
		}
		
		int dX = xEnd - xStart;
		int dY = yEnd - yStart;
		
		if (dX == 0 && dY != 0) {
			
			while (yStart != yEnd) {
				if (dY < 0) {
					yStart -= 1;
				} else {
					yStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (yStart == yEnd) {
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
				} else {
					if (piece == null) {
						continue;
					}
					
					// TODO: Add Contextual Teammate Move
					return false;
				}
			}
		} else if (dX != 0 && dY == 0) {
			
			while (xStart != xEnd) {
				if (dX < 0) {
					xStart -= 1;
				} else {
					xStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (xStart == xEnd) {
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
				} else {
					if (piece == null) {
						continue;
					}
					
					// TODO: Add contextual Teammate move
					return false;
				}
			}
		}
		
		return false;
	}
}
