package clueGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControlPanel extends JPanel {
	JPanel topPanel, bottomPanel, guessResultPanel, guessPanel, playerPanel, rollPanel;
	JLabel turnLabel, rollLabel;
	JTextField guessResultText, guessText, playerText, rollText;
	JButton accusationButton, nextButton;
	Board board;
	private Accusation accusation;
	int currentPlayer = 0; // to see what current player we are on
	int dieRoll;
	Player player;

	public GameControlPanel() {
		board = Board.getInstance();
		// create main grid
		setLayout(new GridLayout(2, 0));
		// create top and bottom panels
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		// add top and bottom panels to main panel
		add(topPanel);
		add(bottomPanel);
		// set grid of top and bottom panels
		topPanel.setLayout(new GridLayout(1, 4));
		bottomPanel.setLayout(new GridLayout(0, 2));

		// create guess result panel
		guessResultPanel = new JPanel();
		guessResultPanel.setLayout(new GridLayout(1, 0)); // set grid for guess result panel

		guessResultText = new JTextField(15); // create text box
		guessResultText.setEditable(false);

		guessResultPanel.add(guessResultText); // add text box to guess result panel
		guessResultPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result")); // add title to panel with
		// border
		bottomPanel.add(guessResultPanel); // add guess result panel to bottom panel

		// create guess panel
		guessPanel = new JPanel();
		guessPanel.setLayout(new GridLayout(1, 0)); // set guess panel grid

		guessText = new JTextField(15); // create text box
		guessText.setEditable(false);

		guessPanel.add(guessText); // add text box to guess panel
		guessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Guess")); // add title with border to guess panel
		bottomPanel.add(guessPanel); // add guess panel to bottom panel

		// create player and roll panels
		playerPanel = new JPanel();
		rollPanel = new JPanel();
		// create accusation and next buttons
		accusationButton = new JButton("Make Accusation");
		nextButton = new JButton("NEXT!");

		// create label and text box for player panel
		turnLabel = new JLabel("Whose turn?"); // label
		playerText = new JTextField(15); // text box
		playerText.setEditable(false);
		playerPanel.add(turnLabel); // add label to player panel
		playerPanel.add(playerText); // add text box to player panel

		// create label and text box for roll panel
		rollLabel = new JLabel("Roll:"); // label
		rollText = new JTextField(5); // text box
		rollText.setEditable(false);
		rollPanel.add(rollLabel); // add label to roll panel
		rollPanel.add(rollText); // add text box to roll panel

		// add player, roll panel, and accusation, next buttons to top panel
		topPanel.add(playerPanel);
		topPanel.add(rollPanel);
		topPanel.add(accusationButton);
		topPanel.add(nextButton);

		//start with the first player who is the human
		setPlayer();
		setRoll();
		setTurn(player, dieRoll);
		drawTargets(player, dieRoll);

		nextButton.addMouseListener(new NextButtonListener());
		accusationButton.addMouseListener(new AccusationButtonListener());
	}

	//only for the human player
	public void drawTargets(Player player, int dieRoll) {
		board.calcTargets(board.getCell(player.getRow(), player.getCol()), dieRoll);

		if (currentPlayer == 0) {
			repaint();
		}
	}
	//die roll for movement
	public void setRoll() {
		Random ranNum = new Random();
		dieRoll = ranNum.nextInt(6) + 1;
	}

	public void setPlayer() {
		player = board.getPlayer().get(currentPlayer);
		board.setPlayerTurn(player);
	}

	private void setGuessResult(String string) {
		guessResultText.setText(string); // text for guess result panel
	}

	public void setGuess(String string) {
		guessText.setText(string); // text for guess panel
		guessText.setBackground(board.getPlayer().get(currentPlayer).getColor()); //color of the guess panel
	}

	private void setTurn(Player player, int i) {
		playerText.setText(player.getName()); // text for player panel
		playerText.setBackground(board.getPlayer().get(currentPlayer).getColor()); //color of the player panel
		rollText.setText(String.valueOf(i)); // text for roll panel
	}

	private class NextButtonListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			//makes sure the human player finishes their turn
			if (!board.isTurnFinished() && currentPlayer == 0) {
				JOptionPane.showMessageDialog(null, "Finish your turn!", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				currentPlayer++;
				if (currentPlayer == board.getPlayer().size()) {
					currentPlayer = 0;
					board.setTurnFinished(false); // makes sure the human player plays their turn and doesn't skip them
				}
				repaint();
				setPlayer();
				setRoll();
				setTurn(player, dieRoll);
				drawTargets(player, dieRoll);
			}

			//Computers will randomly move
			if (player != board.getPlayer().get(0)) {
				BoardCell targetCell = null;
				boolean turnFinished = false;

				board.calcTargets(board.getCell(player.getRow(), player.getCol()), dieRoll);
				Set<BoardCell> targets = board.getTargets();

				for (BoardCell location : targets) {
					// if target is a room and not seen in players seen cards, then go to that location
					if (location.isRoomCenter() && !player.getSeenCards().contains(location)) {
						turnFinished = true;
						targetCell = location;
						break;
					}
				}

				if (!turnFinished) {
					// if no room can be found then a random target will be chosen
					BoardCell[] randomTarget = targets.toArray(new BoardCell[0]);
					Random randLocation = new Random();
					int randomIndexLocation = randLocation.nextInt(randomTarget.length);

					targetCell = randomTarget[randomIndexLocation];
				}

				// computer player will move to target 
				if (targetCell != null) {
					player.updateRow(targetCell.getRow());
					player.updateCol(targetCell.getCol());
					
					if (targetCell.isRoomCenter()) {
						
						Card roomCard;
						
						for (int i = 0; i < board.getRoomDeck().size(); i++) {
							if (board.getRoomDeck().get(i).getCardName() == board.getRoom(targetCell).getName()) {
								roomCard = board.getRoomDeck().get(i);
								setGuess(roomCard + ", " + personDeck.get(0) + ", " + weaponDeck.get(0));
								handleSuggestion(player.get(0), roomCard, personDeck.get(0), weaponDeck.get(0));
								break;
							}
						}
					}
					repaint();
					targets.clear();
				}
			}

		}
		//  Empty definitions for unused event methods.
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
	}

	// will be filled in later
	private class AccusationButtonListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
				accusation = new Accusation();
				accusation.setVisible(true);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
}
