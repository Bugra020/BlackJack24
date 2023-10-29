package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Display {

	// boolean RUNNING = true;
	boolean START = true;
	boolean TURN_ENDED = false;
	boolean İS_NEW_GAME = false;
	int WİNNER = 3;
	boolean dealerT = false;

	ArrayList<JLabel> cardUIs = new ArrayList<JLabel>();

	Player player = new Player();
	Dealer dealer = new Dealer();

	JFrame frame = new JFrame();
	JButton hitButton = new JButton();
	JButton standButton = new JButton();
	JLabel pSum = new JLabel();
	JLabel dSum = new JLabel();
	JLabel endgameText = new JLabel();
	JButton newGame = new JButton();
	JButton exitButton = new JButton();

	public Display() {

		frame.setTitle("BLACKJACK24");
		frame.setSize(960, 720);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(22, 68, 52));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);

		hitButton.setText("HIT");
		hitButton.setBackground(new Color(255, 60, 40));
		hitButton.setSize(80, 50);
		hitButton.setLocation(380, 590);
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.add(player.getNewCard());
				player.getSum();
				pSum.setText("" + player.sum);
				checkP21(player.sum);
				player.setSum();

				frame.repaint();
			}
		});

		standButton.setText("STAND");
		standButton.setBackground(new Color(40, 60, 255));
		standButton.setSize(80, 50);
		standButton.setLocation(480, 590);
		standButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TURN_ENDED = true;
				/*
				 * player.getSum(); dealer.getSum(); check(player.sum, dealer.sum);
				 * dealer.setSum(); player.setSum();
				 */
				dealerTurn();

				frame.repaint();
			}
		});

		pSum.setSize(80, 50);
		pSum.setLocation(750, 340);
		pSum.setFont(new Font("serif", Font.BOLD, 32));
		pSum.setForeground(Color.BLACK);

		dSum.setSize(80, 50);
		dSum.setLocation(750, 40);
		dSum.setFont(new Font("serif", Font.BOLD, 32));
		dSum.setForeground(Color.BLACK);

		endgameText.setSize(300, 50);
		endgameText.setLocation(380, 260);
		endgameText.setFont(new Font("serif", Font.BOLD, 32));

		newGame.setSize(300, 50);
		newGame.setLocation(20, 10);
		newGame.setFont(new Font("serif", Font.BOLD, 32));
		newGame.setText("PLAY AGAIN");
		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				resetFrame();
				newGame();
			}
		});

		exitButton.setSize(300, 50);
		exitButton.setLocation(20, 70);
		exitButton.setFont(new Font("serif", Font.BOLD, 32));
		exitButton.setText("EXIT");
		exitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				exit();
			}
		});
	}

	public void GameStarted() {
		if (START) {
			frame.add(hitButton);
			frame.add(standButton);

			player.getStartingDeck();
			frame.add(player.cardLabels.get(0));
			frame.add(player.cardLabels.get(1));

			dealer.getStartingDeck();
			frame.add(dealer.cardLabels.get(0));
			frame.add(dealer.cardLabels.get(1));

			player.getSum();
			pSum.setText("" + player.sum);
			frame.add(pSum);
			player.setSum();

			dealer.getSum();
			dSum.setText("" + dealer.sum);
			frame.add(dSum);
			dealer.setSum();

			endgameText.setText("");
			frame.add(endgameText);

			frame.setVisible(true);
			this.START = false;
		}

	}

	public void checkP21(int p) {
		if (p > 21) {
			this.WİNNER = 2;
			gameEnded();
		}
	}

	public void checkD21(int d) {
		if (d > 21) {
			this.WİNNER = 1;
			gameEnded();
		}
	}

	public void check(int p, int d) {
		if (p > 21 && d > 21) {
			this.WİNNER = 0;
			gameEnded();
		}

		else if (p == 21 && d == 21) {
			this.WİNNER = 1;
			gameEnded();
		}
		else if (p == 21 && d < 21) {
			this.WİNNER = 1;
			gameEnded();
		} else if (d == 21 && p < 21) {
			this.WİNNER = 2;
			gameEnded();
		} else if (p < 21 && d < 21) {
			if (p > d) {
				this.WİNNER = 1;
				gameEnded();
			} else if (p < d) {
				this.WİNNER = 2;
				gameEnded();
			} else if (p == d) {
				this.WİNNER = 0;
				gameEnded();
			}

		}

	}

	public JLabel createLabel() {
		return new JLabel();
	}

	public void gameEnded() {

		if (this.WİNNER == 0) {
			endgameText.setForeground(Color.GRAY);
			endgameText.setText("TİE");
		} else if (this.WİNNER == 1) {
			endgameText.setForeground(Color.GREEN);
			endgameText.setText("YOU WON");
		} else if (this.WİNNER == 2) {
			endgameText.setForeground(Color.RED);
			endgameText.setText("YOU LOST");
		}

		askNewGame();
		frame.repaint();

	}

	public void dealerTurn() {
		frame.repaint();
		dealer.getSum();

		if (dealer.sum <= 17) {
			frame.add(dealer.getNewCard());
			dealer.getSum();
			dSum.setText("" + dealer.sum);
			checkD21(dealer.sum);
			dealer.setSum();
			frame.repaint();
			dealerTurn();
		} else {
			player.getSum();
			dSum.setText("" + dealer.sum);
			check(player.sum, dealer.sum);
			dealer.setSum();
			player.setSum();

			frame.repaint();
		}

	}

	public void newGame() {
		
		player.clear();
		dealer.clear();

		this.START = true;
		this.GameStarted();
	}

	public void askNewGame() {
		this.frame.add(newGame);
		this.frame.add(exitButton);
		this.frame.repaint();
	}

	public void exit() {
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
	}

	public void resetFrame() {
		for (int j = 0; j < this.player.cardLabels.size(); j++) {
			this.frame.remove(this.player.cardLabels.get(j));
		}
		for (int j = 0; j < this.dealer.cardLabels.size(); j++) {
			this.frame.remove(this.dealer.cardLabels.get(j));
		}
		this.frame.remove(endgameText);
		this.frame.remove(newGame);
		this.frame.remove(exitButton);
		this.frame.remove(pSum);
		this.frame.remove(dSum);
		this.frame.remove(hitButton);
		this.frame.remove(standButton);

		this.frame.repaint();
	}
}
