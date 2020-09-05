package entities;

import commons.Constants;
import commons.Context;
import game.ChessBoard;

/**
 * Concrete class representing a Rook chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Rook extends ChessPiece {
	private boolean firstMove;
	
	public Rook(boolean isWhite) {
		super(isWhite);
		
		if (isWhite) {
			setImage(Constants.ROOK_WHITE);
		} else {
			setImage(Constants.ROOK_BLACK);
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
		// checking if move is within board boundaries
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
		
		// Calculating change
		int dX = xEnd - xStart;
		int dY = yEnd - yStart;
		
		// Y direction rook move
		if (dX == 0 && dY != 0) {
			// Checking the board for pieces that block this rook
			while (yStart != yEnd) {
				if (dY < 0) {
					yStart -= 1;
				} else {
					yStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (yStart == yEnd) {
					// Final position either is open or claims piece
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
					
					return false;
				} else {
					if (piece == null) {
						continue;
					}
					
					if (piece.isWhite == this.isWhite &&
							rule == Context.ROOK_GHOST) {
						continue;
					}
					
					return false;
				}
			}
		}
		// X direction rook move
		else if (dX != 0 && dY == 0) {
			
			while (xStart != xEnd) {
				if (dX < 0) {
					xStart -= 1;
				} else {
					xStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (xStart == xEnd) {
					// Final position must be open or occupied by opposite team
					if (piece == null || piece.isWhite != this.isWhite) {
						return true;
					}
					
					return false;
				} else {
					if (piece == null) {
						continue;
					}
					
					if (piece.isWhite == this.isWhite && 
							rule == Context.ROOK_GHOST) {
						continue;
					}
					return false;
				}
			}
		}
		
		// Invalid direction given or no change occurred
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite) {
			return Constants.W_ROOK;
		} else {
			return Constants.B_ROOK;
		}
	}
}
