package clueGame;

import java.awt.*;
import javax.swing.*;

public class ClueGame extends JFrame {
	private ClueGame gui;
	GameControlPanel controlPanel;
	GameCardPanel cardsPanel;
	Board board;

	public ClueGame() {
		gui = this;
		board = Board.getInstance();
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use my config files
		board.setConfigFiles("ClueLayout.csv", "ClueSetup.txt");		
		// Initialize will load config files 
		board.initialize();

		controlPanel = new GameControlPanel();
		cardsPanel = new GameCardPanel();
		setTitle("Clue Game - CSCI306");
		setSize(800, 720);  // size the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		createLayout();
	}

	private void createLayout() {
		add(board, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(controlPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		ClueGame gui = new ClueGame();  // create the panel
		gui.setVisible(true); // make it visible;
	}
}
