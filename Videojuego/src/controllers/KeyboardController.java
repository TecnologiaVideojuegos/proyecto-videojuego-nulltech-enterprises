package controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardController extends Input {

	/*
	 * Constructors
	 */
	static String lastpressedp1="";
	
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
	public String getPressedpl1(GameContainer gc)
	{
		if(gc.getInput().isKeyPressed(Input.KEY_W)){return lastpressedp1="Up";}
		if(gc.getInput().isKeyPressed(Input.KEY_S)){return lastpressedp1="Down";}
		if(gc.getInput().isKeyPressed(Input.KEY_A)){return lastpressedp1="Left";}
		if(gc.getInput().isKeyPressed(Input.KEY_D)){return lastpressedp1="Right";}
		return "";
		
	}



}
