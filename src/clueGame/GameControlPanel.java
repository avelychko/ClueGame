package clueGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameControlPanel extends JPanel {

	/**
	 * Constructor for the panel, it does 90% of the work
	 */
	public GameControlPanel()  {
		
	}
	
	private void setGuessResult(String string) {
		JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
	 	JLabel nameLabel = new JLabel("Guess");
		panel.add(nameLabel);
		JTextField textField = new JTextField();
		textField.setText(string);
		panel.add(textField);
	}

	private void setGuess(String string) {
		JPanel panel = new JPanel();
	 	// Use a grid layout, 1 row, 2 elements (label, text)
		panel.setLayout(new GridLayout(1,2));
	 	JLabel nameLabel = new JLabel("Guess Result");
		panel.add(nameLabel);
		JTextField textField = new JTextField();
		textField.setText(string);
		panel.add(textField);
	}

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		
		
	}
	
	private void setAccusationButton() {
		GameControlPanel panel = new GameControlPanel();
		JButton accusationButton = new JButton("Make Accusation");
		JButton nextButton = new JButton("NEXT!");
		panel.add(accusationButton);
		panel.add(nextButton);
	}
	
	/**
	 * Main to test the panel
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameControlPanel panel = new GameControlPanel();  // create the panel
		JFrame frame = new JFrame();  // create the frame 
		frame.setContentPane(panel); // put the panel in the frame
		frame.setSize(750, 180);  // size the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow it to close
		frame.setVisible(true); // make it visible
		
		// test filling in the data
		panel.setTurn(new ComputerPlayer( "Col. Mustard", "orange", 0, 0), 5);
		panel.setGuess( "I have no guess!");
		panel.setGuessResult( "So you have nothing?");
		panel.setAccusationButton();
	}
}
