package minigames;

import org.newdawn.slick.Image;

public class Dice {
	
	private final Image[] diceImages;
	
	public Dice(final Image[] diceImages) {
		this.diceImages = diceImages;
	}
	
	public void rollDice() {
		
	}

	public Image getImageByIndex(final int index) {
		return diceImages[index];
	}
}
