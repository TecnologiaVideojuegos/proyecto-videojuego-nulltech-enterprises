package controllers;

import org.newdawn.slick.Input;

public class KeyboardController extends Input {

	/*
	 * Constructors
	 */
	public KeyboardController(int height) {
		super(height);
	}
	
	/*
	 * GETTERS
	 */
	public int getXMovement() {
		if (isKeyDown(Input.KEY_A)) return -100;
		if (isKeyDown(Input.KEY_D)) return 100;
		return 0;
	}
	
	public int getYMovement() {
		if (isKeyDown(Input.KEY_W)) return -100;
		if (isKeyDown(Input.KEY_S)) return 100;
		return 0;
	}

}
