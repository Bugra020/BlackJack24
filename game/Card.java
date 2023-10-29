package game;

import javax.swing.ImageIcon;

public class Card {
	int value;
	ImageIcon img;
	boolean isAce;
	
	public Card() { //the constractor gives a random card
		int r = (int) (1 + (Math.random() * 52));
		
		//getting value
		int j = r % 13;
		if(j == 1) {
			this.value = 1;
			this.isAce = true;
			this.img = new ImageIcon("/BlackJack24/Images/kartlar_" + r + ".png");
		}
		else if(j > 1 && j < 11) {
			this.value = j;
			this.isAce = false;
			this.img = new ImageIcon("/BlackJack24/Images/kartlar_" + r + ".png");
		}
		else if(j > 10 ^ j == 0) {
			this.value = 10;
			this.isAce = false;
			this.img = new ImageIcon("/BlackJack24/Images/kartlar_" + r + ".png");
		}
	}
	
}
