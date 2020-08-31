import javax.swing.JFrame;
import commons.Constants;
import game.ChessBoard;

/**
 * This class is the main executable for the Unfair Chess game
 * 
 * @author Stephen Montes De Oca
 */
public class UnfairChess {
	// Class Constants
	private static final String FRAME_TITLE = "Unfair Chess";
	
	
	/**
	 * This function sets up the stage for the game window & initializes the game
	 * 
	 * @param args - String of command line arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame(FRAME_TITLE);
	    frame.getContentPane().add(new ChessBoard());
	    frame.pack();
	    frame.setVisible(true);
	    frame.setResizable(false);
	}
}
