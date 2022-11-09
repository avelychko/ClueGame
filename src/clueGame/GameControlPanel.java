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
		turnPanel = new JPanel();
		add(turnPanel, BorderLayout.WEST);
		accusationPanel = new JPanel();
		add(accusationPanel, BorderLayout.EAST);
		setLayout(new GridLayout(2,2));
		guessResultPanel = new JPanel();
		add(guessResultPanel, BorderLayout.WEST);
		guessPanel = new JPanel();
		add(guessPanel, BorderLayout.EAST);
	}
	
	private void setGuessResult(String string) {
		JTextField guessResultText = new JTextField(32);
		guessResultText.setText(string);
		guessResultPanel.add(guessResultText);
		guessResultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result"));
	}

	private void setGuess(String string) {
		JTextField guessText = new JTextField(32);
		guessText.setText(string);
		guessPanel.add(guessText);
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess"));
	}

	private void setTurn(ComputerPlayer computerPlayer, int i) {
		JPanel playerPanel = new JPanel();
		JPanel rollPanel = new JPanel();
		JLabel turnLabel = new JLabel("Whose turn?");
		playerPanel.add(turnLabel);
		JLabel rollLabel = new JLabel("Roll:");
		rollPanel.add(rollLabel);
		turnPanel.add(playerPanel, BorderLayout.WEST);
		turnPanel.add(rollPanel, BorderLayout.EAST);
	}
	
	private void setAccusationButton() {
//		GameControlPanel panel = new GameControlPanel();
//		JButton accusationButton = new JButton("Make Accusation");
//		JButton nextButton = new JButton("NEXT!");
//		panel.add(accusationButton);
//		panel.add(nextButton);
	}
	

//	private class ButtonListener implements ActionListener
//	{
//		public void actionPerformed(ActionEvent e)
//		{
//			String message = "Hello " + myName.getText(); //input text
//			JOptionPane.showMessageDialog(null, message); //opens window with text
//			String numStr = JOptionPane.showInputDialog("Enter your age"); //window where input text
//			int num = Integer.parseInt(numStr);
//			int yearsLeft = 100 - num;
//			JOptionPane.showMessageDialog(null, 
//					"You have " + yearsLeft + " years left");
//			int ready = JOptionPane.showConfirmDialog(null, 
//					"Are you ready to continue?"); //window with choice buttons
//			if (ready == JOptionPane.YES_OPTION) //if you choose yes
//				JOptionPane.showMessageDialog(gui, "Here we go!");
//			else //if you choose no
//				JOptionPane.showMessageDialog(gui, "OK, we'll wait");
//		}
//	}


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
