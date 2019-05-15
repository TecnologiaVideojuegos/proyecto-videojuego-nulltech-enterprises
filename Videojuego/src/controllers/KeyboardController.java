package controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardController extends Input {

	/*
	 * Constructors
	 */
	public String lastpressedp1="";
	
	public KeyboardController(int height) {
		super(height);
	}
	
	/*
	 * GETTERS
	 */
	public int getXMovementPl1() {
		if (isKeyDown(Input.KEY_A)) return -1;
		if (isKeyDown(Input.KEY_D)) return 1;
		return 0;
	}
	
	public int getYMovementPl1() {
		if (isKeyDown(Input.KEY_W)) return -1;
		if (isKeyDown(Input.KEY_S)) return 1;
		return 0;
	}
	
	public int getActionButtonPl1() {
		if (isKeyDown(Input.KEY_ENTER)) return 1;
		return 0;
	}
	
	public int getXMovementPl2() {
		if (isKeyDown(Input.KEY_LEFT)) return -1;
		if (isKeyDown(Input.KEY_RIGHT)) return 1;
		return 0;
	}
	
	public int getYMovementPl2() {
		if (isKeyDown(Input.KEY_UP)) return -1;
		if (isKeyDown(Input.KEY_DOWN)) return 1;
		return 0;
	}
	
	
	
	
	public String getPressedpl1(GameContainer gc)
	{

		if(gc.getInput().isKeyPressed(Input.KEY_W)){
			return lastpressedp1="Up";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_S)){
			return lastpressedp1="Down";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_A)){
			return lastpressedp1="Left";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_D)){
			return lastpressedp1="Right";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_NUMPAD1)) {
			return lastpressedp1="One";
		}
		return "";
		
	}
	
	public String getPressedpl2(GameContainer gc)
	{

		if(gc.getInput().isKeyPressed(Input.KEY_UP)){
			return lastpressedp1="Up";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_DOWN)){
			return lastpressedp1="Down";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_LEFT)){
			return lastpressedp1="Left";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_RIGHT)){
			return lastpressedp1="Right";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_NUMPAD1)) {
			return lastpressedp1="One";
		}
		return "";
		
	}



}
