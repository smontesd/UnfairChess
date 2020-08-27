package entities;

import javax.swing.ImageIcon;

/**
 * This is an abstract class which all the chess board pieces will inherit from
 * 
 * @author Stephen Montes De Oca
 */
public abstract class ChessPiece {	
	// instance variables
	protected boolean isWhite;
	private ImageIcon icon;
	
	/**
	 * Class Constructor: Initializes each instance member
	 * 
	 * @param isWhite - boolean which determines the color of the chess piece
	 */
	public ChessPiece(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	// abstract methods
	public abstract boolean canMove(int xStart, int yStart);
	
	// getters and setters
	public ImageIcon getImage() {
		return this.icon;
	}
	
	public void setImage(String filename) {
		this.icon = new ImageIcon(filename);
	}
}
