package game;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * This class is the main executable for the Unfair Chess game
 * 
 * @author Stephen Montes De Oca
 */
public class UnfairChess extends JFrame {
	// Class variables
	public static final String FRAME_TITLE = "Unfair Chess";
	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 800;
	// instance variables
	private ChessBoard board;
	
	public UnfairChess(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contents = getContentPane();
		contents.setLayout(new BorderLayout(0,0));
		
		// JPanel center
		ChessBoard board = new ChessBoard();
		contents.add(board, BorderLayout.CENTER);
		
		// JPanel top
		JPanel topPanel = new JPanel();
		JLabel jlabel = board.getJLabel();
		
		topPanel.setBorder(new LineBorder(Color.DARK_GRAY, 3));
		topPanel.add(jlabel);
		contents.add(topPanel, BorderLayout.NORTH);
		
		// JPanel bottom
		JPanel botPanel = new JPanel();
		JButton jbutton = board.getPassButton();
		JButton jbutton2 = board.getResignButton();
		
		botPanel.setBorder(new LineBorder(Color.DARK_GRAY, 3));
		botPanel.add(jbutton);
		botPanel.add(jbutton2);
		contents.add(botPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * This function creates an instance of the Unfair Chess program window
	 * 
	 * @param args - String of command line arguments
	 */
	public static void main(String[] args) {
		UnfairChess game = new UnfairChess(FRAME_TITLE);
	}
}
