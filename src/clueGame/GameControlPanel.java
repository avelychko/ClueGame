package clueGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.Random;
import java.util.Set;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControlPanel extends JPanel{
	JPanel topPanel, bottomPanel, guessResultPanel, guessPanel, playerPanel, rollPanel;
	JLabel turnLabel, rollLabel;
	JTextField guessResultText, guessText, playerText, rollText;
	JButton accusationButton, nextButton;
	Board board;
	int currentPlayer = 0;
	int dieRoll;


	public GameControlPanel()  {
		board = Board.getInstance();
		//create main grid
		setLayout(new GridLayout(2, 0)); 
		//create top and bottom panels
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		//add top and bottom panels to main panel
		add(topPanel);
		add(bottomPanel);
		//set grid of top and bottom panels
		topPanel.setLayout(new GridLayout(1, 4)); 
		bottomPanel.setLayout(new GridLayout(0, 2)); 
		
		//create guess result panel
		guessResultPanel = new JPanel(); 
		guessResultPanel.setLayout(new GridLayout(1, 0)); //set grid for guess result panel
		
		guessResultText = new JTextField(15); //create text box
		guessResultText.setBackground(new Color(238, 238, 238)); //change color for text box
		
		guessResultPanel.add(guessResultText); //add text box to guess result panel
		guessResultPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess Result")); //add title to panel with border
		bottomPanel.add(guessResultPanel); //add guess result panel to bottom panel
		
		//create guess panel
		guessPanel = new JPanel(); 
		guessPanel.setLayout(new GridLayout(1, 0)); //set guess panel grid
		
		guessText = new JTextField(15); //create text box
		guessText.setBackground(new Color(238, 238, 238)); //change color of text box
		
		guessPanel.add(guessText); //add text box to guess panel
		guessPanel.setBorder(new TitledBorder (new EtchedBorder(), "Guess")); //add title with border to guess panel
		bottomPanel.add(guessPanel); //add guess panel to bottom panel
		
		//create player and roll panels
		playerPanel = new JPanel();
		rollPanel = new JPanel();
		//create accusation and next buttons
		accusationButton = new JButton("Make Accusation");
		nextButton = new JButton("NEXT!");
		
		//create label and text box for player panel
		turnLabel = new JLabel("Whose turn?"); //label
		playerText = new JTextField(15); //text box
		playerText.setBackground(new Color(238, 238, 238)); //text background change
		playerPanel.add(turnLabel); //add label to player panel
		playerPanel.add(playerText); //add text box to player panel
		
		//create label and text box for roll panel
		rollLabel = new JLabel("Roll:"); //label
		rollText = new JTextField(5); //text box
		rollText.setBackground(new Color(238, 238, 238)); //text background change
		rollPanel.add(rollLabel); //add label to roll panel
		rollPanel.add(rollText); //add text box to roll panel
		
		//add player, roll panel, and accusation, next buttons to top panel
		topPanel.add(playerPanel);
		topPanel.add(rollPanel);
		topPanel.add(accusationButton);
		topPanel.add(nextButton);
		
		
		nextButton.addMouseListener(new mouseClicker());
		
		
	}
	

	
	private void setGuessResult(String string) {
		guessResultText.setText(string); //text for guess result panel
	}

	public void setGuess(String string) {
		guessText.setText(string); //text for guess panel
	}

	private void setTurn(Player player, int i) {
		playerText.setText(player.getName()); //text for player panel
		playerText.setBackground(board.getPlayer().get(currentPlayer).getColor());
		rollText.setText(String.valueOf(i)); //text for roll panel
	}
	
	private class mouseClicker implements MouseListener{
		
		public void mouseClicked(MouseEvent e) {
			
			Player player = board.getPlayer().get(currentPlayer);
			board.setPlayerTurn(player);
			
			Random ranNum = new Random();
			dieRoll = ranNum.nextInt(6) + 1;
			
			setTurn(player, dieRoll);
			
			board.calcTargets(board.getCell(player.getRow(), player.getCol()), dieRoll);
			
			if (currentPlayer == 0) {
				repaint();
			}
			
			currentPlayer++;
			if (currentPlayer == board.getPlayer().size()) {
				currentPlayer = 0;
			}
			repaint();
				
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}

	//add a mouse listner for accusation
	
}
