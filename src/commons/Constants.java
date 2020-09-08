package commons;

public class Constants {
	// Unfair Chess constants
	public static final int BOARD_WIDTH = 800;
	public static final int BOARD_HEIGHT = 800;
	
	// Chess Board constants
	public static final String BACKGROUND = "src/images/chessboard.png";
	public static final String SELECTED_PROMPT = "Selected %s: %d, %d";
	public static final String PLACED_PROMPT = "Placed %s: %d, %d";
	public static final int SIZE = 8;
	
	// Chess Piece constants
	public static final String ROOK_BLACK = "src/images/rook_black.png";
	public static final String BISHOP_BLACK = "src/images/bishop_black.png";
	public static final String KNIGHT_BLACK = "src/images/knight_black.png";
	public static final String KING_BLACK = "src/images/king_black.png";
	public static final String QUEEN_BLACK = "src/images/queen_black.png";
	public static final String PAWN_BLACK = "src/images/pawn_black.png";
	
	public static final String ROOK_WHITE = "src/images/rook_white.png";
	public static final String BISHOP_WHITE = "src/images/bishop_white.png";
	public static final String KNIGHT_WHITE = "src/images/knight_white.png";
	public static final String KING_WHITE = "src/images/king_white.png";
	public static final String QUEEN_WHITE = "src/images/queen_white.png";
	public static final String PAWN_WHITE = "src/images/pawn_white.png";
	
	public static final String W_ROOK = "rook (white)";
	public static final String W_BISHOP = "bishop (white)";
	public static final String W_KNIGHT = "knight (white)";
	public static final String W_KING = "king (white)";
	public static final String W_QUEEN = "queen (white)";
	public static final String W_PAWN = "pawn (white)";
	
	public static final String B_ROOK = "rook (black)";
	public static final String B_BISHOP = "bishop (black)";
	public static final String B_KNIGHT = "knight (black)";
	public static final String B_KING = "king (black)";
	public static final String B_QUEEN = "queen (black)";
	public static final String B_PAWN = "pawn (black)";
	
	public static final int LONG_L = 2;
	public static final int SHORT_L = 1;
	public static final int FIRST_MOVE = 2;
	public static final int EXTENSION = 2;
	public static final int KING_SIDE = 2;
	public static final int QUEEN_SIZE = -3;
	
	// Context constants
	public static final String FORWARDS_RULE = "On this turn, chess pieces can only move forwards";
	public static final String BACKWARDS_RULE = "On this turn, chess pieces can only move backwards (does not apply to pawns)";
	public static final String QKNIGHT_RULE = "On this turn, the queen gains the ability to perform a knight move";
	public static final String PAWN_FWD_RULE = "On this turn, pawns gain the ability to attack forwards";
	public static final String PAWN_EXT_RULE = "On this turn, a pawn attacks have a 2 tile diagonal scope";
	public static final String BISHOP_ROOK_SWAP = "Your bishops and rooks get swapped";
	public static final String BOARD_FLIP = "The board is flipped horizontally for the current player";
	public static final String BISHOP_GHOST_RULE = "On this turn, bishops can pass through ally chess pieces";
	public static final String ROOK_GHOST_RULE = "On this turn, rooks can pass through ally chess pieces";
	public static final String NEUTRAL_RULE = "On this turn, the rules of chess remain the same";
}
