# UnfairChess
Chess, but each turn a new rule is implemented which makes the game unfair

# How to Run
1. Clone this repository
2. Enter the UnfairChess directory
3. In the command line type `java -jar UnfairChess.jar`

# How to Play
This game is like chess, but a new rule is implemented each turn.
Once the board is initialized white starts first.
Pieces are moved by clicking on a piece then dragging them to the desired placement.
First player to claim their opponent's king wins.

# What Kind of Rules Are Added?
- Bishops can pass through teammates for a turn
- Rooks can pass through teammates for a turn
- Queens can attack like Knights for a turn
- Chess pieces are only allowed to move forward for a turn
- Chess pieces (except pawns) are only allowed to move backwards for a turn
- Pawns can attack forwards for a turn
- Pawns can attack with an extended reach (2 tiles)
- Bishops and Rooks are swapped for each other
- The board is flipped horizontally for the current player
