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
	private int selectedX;
	private int selectedY;
	private Context rule;
	private boolean currentTurnWhite;
	private boolean isGameOver;
	
	/**
	 * Default class constructor: initializes a new board
	 */
	public ChessBoard() {
		this.background = new ImageIcon(Constants.BACKGROUND).getImage();
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
	    
	    addMouseListener(this);
	}
	
	public void initializeBoard() {
		// adding rooks
		board[0][0] = new Rook(false);
		board[0][7] = new Rook(false);
		board[7][0] = new Rook(true);
		board[7][7] = new Rook(true);
		
		// adding bishops
		board[0][2] = new Bishop(false);
		board[0][5] = new Bishop(false);
		board[7][2] = new Bishop(true);
		board[7][5] = new Bishop(true);
		
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
				  
				  board[i][j].draw(g2d, j * 100, i * 100);
			  }
		  }
	}
	
	public boolean move(int xStart, int yStart, int xEnd, int yEnd) {
		ChessPiece selected = board[yStart][xStart];
		ChessPiece slot = board[yStart][xStart];
		
		if (selected == null || selected.isWhite() != currentTurnWhite) {
			return false;
		}
		
		if (selected.canMove(this, xStart, yStart, xEnd, yEnd)) {
			board[yEnd][xEnd] = board[yStart][xStart];
			board[yStart][xStart] = null;
			return true;
		}
		
		// TODO: Add castling move
		// Add first move to pawn
		return false;
	}
	
	public void nextTurn() {
		currentTurnWhite = !currentTurnWhite;
	}
	
	// setters and getters
	public ChessPiece getPiece(int x, int y) {
		if (x < 0 || x >= Constants.SIZE || y < 0 || y >= Constants.SIZE) {
			throw new IndexOutOfBoundsException();
		}
		
		return board[y][x];
	}
		
	public void setPiece(int x, int y, ChessPiece piece) {
		if (x < 0 || x >= Constants.SIZE || y < 0 || y >= Constants.SIZE) {
			throw new IndexOutOfBoundsException();
		}
		
		board[y][x] = piece;
	}
	
	public Context getContext() {
		return this.rule;
	}
	
	public void setContext(Context rule) {
		this.rule = rule;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Mouse events do nothing when the game ends
		if (isGameOver) {
			return;
		}
		
		// Selecting a board piece
		int x = Math.floorDiv(e.getX(), 100);
		int y = Math.floorDiv(e.getY(), 100);
		
		ChessPiece selected = board[y][x];
		
		if (selected == null || selected.isWhite() != currentTurnWhite) {
			return;
		}
		
		selectedX = x;
		selectedY = y;
		System.out.println(String.format(Constants.SELECTED_PROMPT, selectedX, selectedY));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int placedX = Math.floorDiv(e.getX(), 100);
		int placedY = Math.floorDiv(e.getY(), 100);
					
		if (move(selectedX, selectedY, placedX, placedY)) {
			System.out.println(String.format(Constants.PLACED_PROMPT, placedX, placedY));
			nextTurn();
		}
					
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
	@Override
	public void mouseClicked(MouseEvent e) { }
	
	@Override
	public void mouseDragged(MouseEvent e) { }

	@Override
	public void mouseMoved(MouseEvent e) { }
}
