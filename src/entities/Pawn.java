package entities;

import commons.Constants;
import commons.Context;
import game.ChessBoard;

/**
 * Concrete class representing a Pawn chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Pawn extends ChessPiece {
	private boolean firstMove;

	public Pawn(boolean isWhite) {
		super(isWhite);
		
		firstMove = false;
		if (isWhite) {
			setImage(Constants.PAWN_WHITE);
		} else {
			setImage(Constants.PAWN_BLACK);
		}
	}

	@Override
	public boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd) {
		// checking if move is within board's boundaries
		if (xEnd < 0 || xEnd >= Constants.SIZE || yEnd < 0 || yEnd >= Constants.SIZE) {
			return false;
		}
		
		// pawns can only move forward regardless of context
		if (!isForwardsMove(yStart,yEnd)) {
			return false;
		}
		
		int dY = yEnd - yStart;
		int dX = xEnd - xStart;
		
		// Checking context
		Context rule = board.getContext();
		
		if (Math.abs(dY) == Constants.FIRST_MOVE) {
			if (dX == 0 && firstMove == false) {
				// checking if path is clear
				while (yStart != yEnd) {
					if (dY < 0) {
						yStart -= 1;
					} else {
						yStart += 1;
					}
					
					ChessPiece piece = board.getPiece(xStart, yStart);
					
					if (yStart == yEnd) {
						// final position must be open or a special context is met
						if (piece == null) {
							return true;
						} else if (piece.isWhite != this.isWhite
								&& rule == Context.PAWN_FWD_ATK) {
							return true;
						}
					} else if (piece == null) {
						continue;
					}
					
					break;
				}
			} else if (Math.abs(dX) == Constants.EXTENSION &&
					rule == Context.PAWN_ATK_EXTEND) {
				// checking if path is clear
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
						if (piece != null && piece.isWhite != this.isWhite) {
							return true;
						}
						
						break;
					} else if (piece == null) {
						continue;
					}
					
					break;
				}
			}
		} else if (Math.abs(dY) == 1 && Math.abs(dX) <= 1) {
			ChessPiece piece = board.getPiece(xEnd, yEnd);
			
			if (Math.abs(dX) == 1 && piece != null &&
					piece.isWhite != this.isWhite) {
				return true;
			} else if (dX == 0 && piece == null) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (isWhite) {
			return Constants.W_PAWN;
		} else {
			return Constants.B_PAWN;
		}
	}
	
	public void completeFirstMove() {
		firstMove = true;
	}
	
	public boolean getFirstMove() {
		return firstMove;
	}
}
