package minigames;

import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.Image;

public class Dice {
	
	/*
	 * Attributes
	 */
	private final Image[] diceImages;
	private int currentDiceValue;
	
	
	/*
	 * Constructors
	 */
	public Dice(final Image[] diceImages) {
		this.diceImages = diceImages;
	}
	
	
	/*
	 * Dice functionality
	 */
	public void rollDice() {
		currentDiceValue = ThreadLocalRandom.current().nextInt(1, diceImages.length);
	}

	
	/*
	 * Getters
	 */
	public Image getImageByIndex(final int index) {
		return diceImages[index];
	}
	
	public Image getImageByDiceValue(int index) {
		return diceImages[--index];
	}
	
	public Image getImageByCurrentDieValue() {
		return diceImages[currentDiceValue];
	}
}
