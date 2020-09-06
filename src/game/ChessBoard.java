package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import commons.Constants;
import commons.Context;
import entities.*;

/**
 * This class represents a Chess Board for the Unfair Chess java program.
 * It is used as a display for the board and its pieces, allowing for playing
 * using mouse events.
 * 
 * @author Stephen Montes De Oca
 */
public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener {
	// For debugging purposes
	private static final boolean PRINT_ENABLED = true;
	private static final boolean CONTEXT_ENABLED = true;
	// Instance variables
	private final Image background;
	private ChessPiece[][] board;
	private int selectedX;
	private int selectedY;
	private Context rule;
	private boolean currentTurnWhite;
	private boolean isGameOver;
	// GUI Components
	private JButton passButton;
	private JButton resignButton;
	private JLabel turnContext;
	
	/**
	 * Default class constructor: sets up a new ChessBoard
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
	    createPassButton();
	    createResignButton();
	    createTurnLabel();
	    
	    if (PRINT_ENABLED) {
	    	System.out.println("White's turn");
	    }
	}
	
	public void initializeBoard() {
		// adding rooks
		board[0][0] = new Rook(false);
		board[0][7] = new Rook(false);
		board[7][0] = new Rook(true);
		board[7][7] = new Rook(true);
		
		// adding knights
		board[0][1] = new Knight(false);
		board[0][6] = new Knight(false);
		board[7][1] = new Knight(true);
		board[7][6] = new Knight(true);
		
		// adding bishops
		board[0][2] = new Bishop(false);
		board[0][5] = new Bishop(false);
		board[7][2] = new Bishop(true);
		board[7][5] = new Bishop(true);
		
		// adding queens
		board[0][3] = new Queen(false);
		board[7][3] = new Queen(true);
		
		// adding kings
		board[0][4] = new King(false);
		board[7][4] = new King(true);
		
		// adding pawns
		for (int x = 0; x < Constants.SIZE; x++) {
			board[1][x] = new Pawn(false);
			board[6][x] = new Pawn(true);
		}
	}
	
	public void paintComponent(Graphics g) {
		// Drawing chess board diagram
		g.drawImage(background, 0, 0, null);
		
		Graphics2D g2d = (Graphics2D)g;
		  
		// Drawing each individual chess piece
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
		// Checking if move is within boundaries
		if (xStart < 0 || xStart >= Constants.SIZE || yStart < 0 ||
				yStart >= Constants.SIZE ) {
			return false;
		} else if (xEnd < 0 || xEnd >= Constants.SIZE || yEnd < 0 ||
				yEnd >= Constants.SIZE) {
			return false;
		}
		
		ChessPiece selected = board[yStart][xStart];
		
		if (selected == null || selected.isWhite() != currentTurnWhite) {
			return false;
		}
		
		if (!selected.canMove(this, xStart, yStart, xEnd, yEnd)) {
			return false;
		}
		
		// Moving chess piece onto final location
		ChessPiece claimed = board[yEnd][xEnd];
		board[yEnd][xEnd] = board[yStart][xStart];
		board[yStart][xStart] = null;
		
		if (PRINT_ENABLED) {
			System.out.println(String.format(Constants.PLACED_PROMPT,
				selected.toString(), xEnd, yEnd));
		}
		
		if (selected instanceof Pawn) {
			Pawn p = (Pawn)selected;
			
			if (!p.getFirstMove()) {
				p.completeFirstMove();
			}
			
			// TODO: Add code when pawn lands on other side of board
		} else if (selected instanceof Rook) {
			Rook r = (Rook)selected;
			
			if (!r.getFirstMove()) {
				r.completeFirstMove();
			}
		} else if (selected instanceof King) {
			King k = (King)selected;
			
			int dX = xEnd - xStart;
			
			if (!k.getFirstMove()) {
				k.completeFirstMove();
				
				// Checking for castling move
				if (dX == Constants.KING_SIDE) {
					ChessPiece corner = board[yStart][xEnd+1];
					
					if (corner instanceof Rook) {
						Rook r = (Rook) corner;
						
						r.completeFirstMove();
						board[yStart][xEnd-1] = r;
						board[yStart][xEnd+1] = null;
					}
				} else if (dX == Constants.QUEEN_SIZE) {
					ChessPiece corner = board[yStart][xEnd-1];
					
					if (corner instanceof Rook) {
						Rook r = (Rook) corner;
						
						r.completeFirstMove();
						board[yStart][xEnd+1] = r;
						board[yStart][xEnd-1] = null;
					}
				}
			}
		}
		
		if (claimed instanceof King) {
			// game is over since king was taken
			isGameOver = true;
			
			if (turnContext != null) {
				turnContext.setText(currentTurnWhite ? "White wins!" : "Black wins!");
			}
			
			if (PRINT_ENABLED) {
				System.out.println(currentTurnWhite ? "White wins!" : "Black wins!");
			}
		}
		
		return true;
	}
	
	public void nextTurn() {
		if (isGameOver) {
			return;
		}
		
		currentTurnWhite = !currentTurnWhite;
		
		if (PRINT_ENABLED) {
			System.out.println(currentTurnWhite ?
					"White's turn" : "Black's turn");
		}
		
		if (CONTEXT_ENABLED) {
			nextContext();
		}
		
		if (turnContext != null) {
			String turnText = currentTurnWhite ? "White" : "Black";
			turnContext.setText(String.format(
					"%s's turn: %s",turnText,getContext()));
		}
	}
	
	public void nextContext() {
		// Each rule has an equal chance of occurring
		Context[] rules = Context.values();
		int randIndex = new Random().nextInt(rules.length);
		
		rule = rules[randIndex];
		
		if (PRINT_ENABLED) {
			System.out.println(rule.toString());
		}
		
		// Updating board on a particular context
		if (rule == Context.BOARD_FLIP) {
			flipBoard();
		} else if (rule == Context.BISHOP_ROOK_SWAP) {
			swapRooksAndBishops();
		}
	}
	
	public void flipBoard() {
		// iterating over each row
		for (int y = 0; y < Constants.SIZE; y++) {
			// iterating over the first half of columns
			for (int x = 0; x < Constants.SIZE/2; x++) {
				ChessPiece piece = board[y][x];
				ChessPiece toSwap = board[y][Constants.SIZE-1-x];
				
				// Checking if a swap is needed
				if (piece == null) {
					if (toSwap == null || toSwap.isWhite() != currentTurnWhite) {
						continue;
					}
				} else if (piece.isWhite() != currentTurnWhite &&
						(toSwap == null || toSwap.isWhite() != currentTurnWhite)) {
							continue;
				}
				
				// Swapping
				board[y][x] = toSwap;
				board[y][Constants.SIZE-1-x] = piece;
			}
		}
	}
	
	public void swapRooksAndBishops() {
		// iterating over each row
		for (int y = 0; y < Constants.SIZE; y++) {
			// iterating over each column
			for (int x = 0; x < Constants.SIZE; x++) {
				ChessPiece piece = board[y][x];
				
				if (piece == null || piece.isWhite() != currentTurnWhite) {
					continue;
				}
				
				if (piece instanceof Rook) {
					// turning rook into a bishop
					board[y][x] = new Bishop(currentTurnWhite);
				} else if (piece instanceof Bishop) {
					// turning bishop into a rook
					board[y][x] = new Rook(currentTurnWhite);
				}
			}
		}
	}
	
	public void resign() {
		if (isGameOver) {
			return;
		}
		
		isGameOver = true;
		
		if (PRINT_ENABLED) {
			System.out.print(String.format("%s resigns", currentTurnWhite ? "White" : "Black"));
		}
		
		if (turnContext != null) {
			String winner = currentTurnWhite ? "White" : "Black";
			String loser = currentTurnWhite ? "Black" : "White";
			turnContext.setText(String.format("%s resigns, %s wins!",winner,loser));
		}
		
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
	
	// GUI Component: Setters and Getters
	public JButton getPassButton() {
		return this.passButton;
	}
	
	public void createPassButton() {
		passButton = new JButton("Pass Turn");
		passButton.addActionListener(new PassAction(this));
	}
	
	public JButton getResignButton() {
		return this.resignButton;
	}
	
	public void createResignButton() {
		resignButton = new JButton("Resign");
		resignButton.addActionListener(new ResignAction(this));
	}
	
	public JLabel getJLabel() {
		return turnContext;
	}
	
	public void createTurnLabel() {
		turnContext = new JLabel("White Turn: First move start");	
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
		
		if (x < 0 || x >= Constants.SIZE || y < 0 || y >= Constants.SIZE) {
			return;
		}
		
		ChessPiece selected = board[y][x];
		
		if (selected == null || selected.isWhite() != currentTurnWhite) {
			return;
		}
		
		if (selectedX != x || selectedY != y) {
			selectedX = x;
			selectedY = y;
			
			if (PRINT_ENABLED) {
				System.out.println(String.format(Constants.SELECTED_PROMPT,
					selected.toString(), selectedX, selectedY));
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Mouse actions do nothing once game ends
		if (isGameOver) {
			return;
		}
		
		int placedX = Math.floorDiv(e.getX(), 100);
		int placedY = Math.floorDiv(e.getY(), 100);
		
		if (move(selectedX, selectedY, placedX, placedY)) {
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
