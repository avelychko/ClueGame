package clueGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameControlPanel extends JPanel {
	JPanel topPanel, bottomPanel;
	/**
	 * Constructor for the panel, it does 90% of the work
	 */
	public GameControlPanel()  {

		setLayout(new GridLayout(2, 0)); 
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		add(topPanel);
		add(bottomPanel);
		bottomPanel.setLayout(new GridLayout(0, 2)); 

	}
	
	private void setGuessResult(String string) {
		JPanel guessResultPanel = new JPanel();
		guessResultPanel.setLayout(new GridLayout(1, 0)); 
		
		JTextField guessResultText = new JTextField(15);
		guessResultText.setText(string);
		guessResultText.setBackground(new Color(238, 238, 238));
		
		guessResultPanel.add(guessResultText);
		guessResultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
		bottomPanel.add(guessResultPanel);
	}

	private void setGuess(String string) {
		JPanel guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(1, 0)); 
		
		JTextField guessText = new JTextField(15);
		guessText.setText(string);
		guessText.setBackground(new Color(238, 238, 238));
		
		guessPanel.add(guessText);
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
		bottomPanel.add(guessPanel);
	}

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		topPanel.setLayout(new GridLayout(1, 4)); 
		JPanel playerPanel = new JPanel();
		JPanel rollPanel = new JPanel();
		JButton accusationButton = new JButton("Make Accusation");
		JButton nextButton = new JButton("NEXT!");
		
		JLabel turnLabel = new JLabel("Whose turn?");
		JTextField playerText = new JTextField(15);
		playerText.setText(computerPlayer.getName());
		playerText.setBackground(new Color(250, 218, 94));
		playerPanel.add(turnLabel);
		playerPanel.add(playerText);
		
		JLabel rollLabel = new JLabel("Roll:");
		JTextField rollText = new JTextField(5);
		rollText.setText(String.valueOf(i));
		rollText.setBackground(new Color(238, 238, 238));
		rollPanel.add(rollLabel);
		rollPanel.add(rollText);
		
		topPanel.add(playerPanel);
		topPanel.add(rollPanel);
		topPanel.add(accusationButton);
		topPanel.add(nextButton);
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
	}
}
