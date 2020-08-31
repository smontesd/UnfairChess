package commons;

/**
 * This enum is used for keeping track of each
 * newly implemented rule
 * 
 * @author Stephen Montes De Oca
 */
public enum Context {
	FORWARDS_ONLY (Constants.FORWARDS_RULE),
	BACKWARDS_ONLY (Constants.BACKWARDS_RULE),
	QUEEN_KNIGHT (Constants.QKNIGHT_RULE),
	PAWN_FWD_ATK (Constants.PAWN_FWD_RULE),
	PAWN_ATK_EXTEND (Constants.PAWN_EXT_RULE),
	BISHOP_ROOK_SWAP (Constants.BISHOP_ROOK_SWAP),
	BISHOP_GHOST (Constants.BISHOP_GHOST_RULE),
	ROOK_GHOST (Constants.ROOK_GHOST_RULE),
	BOARD_FLIP (Constants.BOARD_FLIP),
	RANDOM_SWAP (Constants.RANDOM_SWAP);
	
	private final String description;
	
	Context(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}