package clueGame;

import java.awt.*;
import javax.swing.*;

public class ClueGame extends JFrame {
	private ClueGame gui;
	GameControlPanel controlPanel;
	GameCardPanel cardsPanel;
	Board boardPanel;
	
	public ClueGame() {
		gui = this;
		controlPanel = new GameControlPanel();
		cardsPanel = new GameCardPanel();
		boardPanel = new Board();
		setTitle("Clue Game - CSCI306");
		setSize(800, 720);  // size the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		createLayout();
	}
	
	private void createLayout() {
		add(boardPanel, BorderLayout.CENTER);
		add(cardsPanel, BorderLayout.EAST);
		add(controlPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		ClueGame gui = new ClueGame();  // create the panel
		gui.setVisible(true); // make it visible;
	}
}
