package entities;

import commons.Constants;
import commons.Context;
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
		// Checking if the final position is within board's boundaries
		if (xEnd < 0 || xEnd >= Constants.SIZE || yEnd < 0 || yStart >= Constants.SIZE) {
			return false;
		}
		
		int dX = xEnd - xStart;
		int dY = yEnd - yStart;
		
		// Checking context
		Context rule = board.getContext();
		if (rule == Context.FORWARDS_ONLY && !isForwardsMove(yStart, yEnd)) {
			return false;
		} else if (rule == Context.BACKWARDS_ONLY && !isBackwardsMove(yStart, yEnd)) {
			return false;
		}
		
		// Regular king move
		if (Math.abs(dX) <= 1 && Math.abs(dY) <= 1) {
			// Pieces cannot move in place
			if (dX == 0 && dY == 0) {
				return false;
			}
			
			ChessPiece piece = board.getPiece(xEnd, yEnd);
			
			// if final position is empty or a piece is claimed
			if (piece == null || piece.isWhite != isWhite) {
				return true;
			}
		}
		// Castling move
		else if (firstMove == false && dY == 0 && (dX == Constants.KING_SIDE || dX == Constants.QUEEN_SIZE)) {
			// checking if path is clear for a castling move
			while (xStart != xEnd) {
				if (dX < 0) {
					xStart -= 1;
				} else {
					xStart += 1;
				}
				
				ChessPiece piece = board.getPiece(xStart, yStart);
				
				if (xStart == xEnd && piece == null) {
					ChessPiece corner = board.getPiece(dX < 0 ? 0 : Constants.SIZE-1, yStart);
					
					if (corner instanceof Rook) {
						Rook r = (Rook)corner;
						
						if (!r.getFirstMove()) {
							return true;
						}
					}
				} else if (piece == null) {
					continue;
				}
				
				break;
			}
		}
		
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
