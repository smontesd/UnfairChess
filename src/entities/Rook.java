package entities;

/**
 * Concrete class representing a Rook chess piece
 * 
 * @author Stephen Montes De Oca
 */
public class Rook extends ChessPiece {
	
	public Rook(boolean isWhite) {
		super(isWhite);
	}

	public boolean canMove(int x, int y) {
		return true;
	}
}
