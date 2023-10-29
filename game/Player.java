package game;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Player {

	ArrayList<Card> cards = new ArrayList<Card>();
	ArrayList<JLabel> cardLabels = new ArrayList<JLabel>();
	int sum;
	int aceNum;

	public Player() {

	}

	public void getStartingDeck() {

		this.cards.add(new Card());
		this.cardLabels.add(new JLabel());
		this.cardLabels.get(0).setIcon(this.cards.get(0).img);
		this.cardLabels.get(0).setLocation(522, 310);
		this.cardLabels.get(0).setSize(168, 244);

		this.cards.add(new Card());
		this.cardLabels.add(new JLabel());
		this.cardLabels.get(1).setIcon(this.cards.get(1).img);
		this.cardLabels.get(1).setLocation(462, 310);
		this.cardLabels.get(1).setSize(168, 244);
		
		this.aceNum = 0;
		for(int i=0; i < this.cards.size(); i++) {
			if(this.cards.get(i).isAce) {
				this.aceNum++;
			}
		}
	}

	public JLabel getNewCard() {

		this.cards.add(new Card());
		this.cardLabels.add(new JLabel());
		this.cardLabels.get(this.cardLabels.size() - 1).setIcon(this.cards.get(this.cards.size() - 1).img);
		this.cardLabels.get(this.cardLabels.size() - 1).setLocation(522 - (60 * (this.cards.size() - 1)), 310);
		this.cardLabels.get(this.cardLabels.size() - 1).setSize(168, 244);
		
		this.aceNum = 0;
		for(int i=0; i < this.cards.size(); i++) {
			if(this.cards.get(i).isAce) {
				this.aceNum++;
			}
		}
		
		return this.cardLabels.get(this.cardLabels.size() - 1);
	}

	public int getSum() {
		
		for(int i =0; i < this.cards.size(); i++) {
			this.sum += this.cards.get(i).value;
		}
		
		if(this.aceNum != 0) {
			if((this.sum + 10*aceNum)>21) {
				return this.sum;
			}
			else {
				int temp = this.sum;
				this.sum = (temp - aceNum) + 11*aceNum;
				return this.sum;
			}
		}
		else {
			return this.sum;
		}
	}

	public void setSum() {
		this.sum = 0;
	}
	
	public void clear() {
		this.cards.clear();
		this.cardLabels.clear();
		this.aceNum = 0;
		this.sum = 0;
	}

}
