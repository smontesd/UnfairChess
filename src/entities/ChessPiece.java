package entities;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import game.ChessBoard;

/**
 * This is an abstract class which all the chess board pieces will inherit from
 * 
 * @author Stephen Montes De Oca
 */
public abstract class ChessPiece {	
	// instance variables
	protected boolean isWhite;
	protected ImageIcon icon;
	
	/**
	 * Class Constructor: Initializes each instance member
	 * 
	 * @param isWhite - boolean which determines the color of the chess piece
	 */
	public ChessPiece(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	// abstract methods
	public abstract boolean canMove(ChessBoard board, int xStart, int yStart, int xEnd, int yEnd);
	
	// getters and setters
	public Image getImage() {
		return icon.getImage();
	}
	
	public void setImage(String filename) {
		this.icon = new ImageIcon(filename);
	}
	
	public boolean isWhite() {
		return this.isWhite();
	}
	
	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	// concrete methods
	private boolean isForwardsMove(int yStart, int yEnd) {
		if (isWhite) {
			if (yEnd < yStart) {
				return true; 
			} else if (yEnd > yStart) {
				return false;
			}
		} else {
			if (yEnd < yStart) {
				return false;
			} else if (yEnd > yStart) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isBackwardsMove(int yStart, int yEnd) {
		if (!isWhite) {
			if (yEnd < yStart) {
				return true; 
			} else if (yEnd > yStart) {
				return false;
			}
		} else {
			if (yEnd < yStart) {
				return false;
			} else if (yEnd > yStart) {
				return true;
			}
		}
		
		// yStart == yEnd in this case
		return false;
	}
	
	public void draw(Graphics2D g2d, int x, int y) {
		g2d.drawImage(getImage(), x, y, null);
	}
}
