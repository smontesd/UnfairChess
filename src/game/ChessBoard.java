package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import entities.ChessPiece;

public class ChessBoard extends JPanel implements ActionListener {
	// class variables
	public static final int SIZE = 8;
	// instance variables
	ChessPiece[][] board;
	
	public ChessBoard() {
		this.board = new ChessPiece[SIZE][SIZE];
		setFocusable(true);
		setBackground(Color.BLACK);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
