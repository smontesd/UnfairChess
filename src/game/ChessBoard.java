package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import commons.Constants;
import commons.Context;
import entities.*;

public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {
	// instance variables
	private final Image background;
	private ChessPiece[][] board;
	private ChessPiece selected;
	private Context rule;
	private boolean currentTurnWhite;
	private boolean isGameOver;
	
	/**
	 * Default class constructor: initializes board
	 * 
	 */
	public ChessBoard() {
		this.background = (new ImageIcon(Constants.BACKGROUND)).getImage();
		this.board = new ChessPiece[Constants.SIZE][Constants.SIZE];
		this.currentTurnWhite = true;
		
		initializeBoard();
		
		setFocusable(true);
		
		Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
		setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	}
	
	public void initializeBoard() {
		board[0][0] = new Rook(false);
		board[0][7] = new Rook(false);
		board[7][0] = new Rook(true);
		board[7][7] = new Rook(true);
		
		// TODO: Add the remaining chess pieces
	}
	
	public void paintComponent(Graphics g) {
		  g.drawImage(background, 0, 0, null);
		  
		  Graphics2D g2d = (Graphics2D)g;
		  
		  for (int i = 0; i < Constants.SIZE; i++) {
			  for (int j = 0; j < Constants.SIZE; j++) {
				  if (board[i][j] == null) {
					  continue;
				  }
				  
				  board[i][j].draw(g2d, i * 100, j * 100);
			  }
		  }
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// setters and getters
	public ChessPiece getPiece(int x, int y) {
		if (x < 0 || x >= Constants.SIZE || y < 0 || y >= Constants.SIZE) {
			throw new IndexOutOfBoundsException();
		}
		return board[x][y];
	}
	
	public void setPiece(int x, int y, ChessPiece piece) {
		if (x < 0 || x >= Constants.SIZE || y < 0 || y >= Constants.SIZE) {
			throw new IndexOutOfBoundsException();
		}
		
		board[x][y] = piece;
	}
}
