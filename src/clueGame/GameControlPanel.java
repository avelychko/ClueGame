package clueGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GameControlPanel extends JPanel {
	JPanel guessResultPanel, guessPanel, turnPanel, accusationPanel;
	/**
	 * Constructor for the panel, it does 90% of the work
	 */
	public GameControlPanel()  {
		setLayout(new GridLayout(2,2));
		turnPanel = new JPanel();
		add(turnPanel, BorderLayout.WEST);
		accusationPanel = new JPanel();
		add(accusationPanel, BorderLayout.EAST);
		guessResultPanel = new JPanel();
		add(guessResultPanel, BorderLayout.WEST);
		guessPanel = new JPanel();
		add(guessPanel, BorderLayout.EAST);
	}
	
	private void setGuessResult(String string) {
		JTextField guessResultText = new JTextField(32);
		guessResultText.setText(string);
		//guessResultText.setSize(32, 15);
		//guessResultText.setBorder(new LineBorder(Color.RED, 10));
		guessResultText.setBackground(new Color(238, 238, 238));
		guessResultPanel.add(guessResultText);
		guessResultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
	}

	private void setGuess(String string) {
		JTextField guessText = new JTextField(32);
		guessText.setText(string);
		guessText.setBackground(new Color(238, 238, 238));
		
		guessPanel.add(guessText);
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
	}

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		JPanel playerPanel = new JPanel();
		JPanel rollPanel = new JPanel();
		
		JLabel turnLabel = new JLabel("Whose turn?");
		JTextField playerText = new JTextField(15);
		playerText.setText(computerPlayer.getName());
		playerText.setBackground(new Color(248, 228, 115));
		playerPanel.add(turnLabel, BorderLayout.NORTH);
		playerPanel.add(playerText, BorderLayout.SOUTH);
		
		JLabel rollLabel = new JLabel("Roll:");
		JTextField rollText = new JTextField(5);
		rollText.setText(String.valueOf(i));
		rollText.setBackground(new Color(238, 238, 238));
		rollPanel.add(rollLabel);
		rollPanel.add(rollText);
		
		turnPanel.add(playerPanel, BorderLayout.WEST);
		turnPanel.add(rollPanel, BorderLayout.EAST);
	}
	
	private void setAccusationButton() {
		JPanel accusationButtonPanel = new JPanel();
		JPanel nextButtonPanel = new JPanel();
		
		JButton accusationButton = new JButton("Make Accusation");
		JButton nextButton = new JButton("NEXT!");
		//accusationButton.setSize(50, 50);
		//nextButton.setSize(50, 50);

		accusationButtonPanel.add(accusationButton);
		nextButtonPanel.add(nextButton);
		
		accusationPanel.add(accusationButtonPanel, BorderLayout.WEST);
		accusationPanel.add(nextButtonPanel, BorderLayout.WEST);
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
