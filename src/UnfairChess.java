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
		frame.setSize(Constants.BOARD_WIDTH, Constants.BOARD_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(new ChessBoard());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
}
