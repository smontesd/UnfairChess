package entities;

import game.ChessBoard;

/**
 * Concrete class representing a Rook chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Rook extends ChessPiece {
	
	public Rook(boolean isWhite) {
		super(isWhite);
	}

	public boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd) {
		if (xEnd < 0 || xEnd >= ChessBoard.SIZE || yEnd < 0 || yEnd >= ChessBoard.SIZE) {
			return false;
		}
		
		int dX = xEnd - xStart;
		int dY = yEnd - yStart;
		
		if (dX == 0 && dY != 0) {
			
		} else if (dX != 0 && dY == 0) {
			
		}
		
		return false;
	}
}
