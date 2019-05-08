package controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class KeyboardController extends Input {

	/*
	 * Constructors
	 */
	public String lastpressedpl1="";
	public String lastpressedpl2="";
	public String previouspressedpl1="";
	public String previouspressedpl2="";
	
	public KeyboardController(int height) {
		super(height);
	}
	
	/*
	 * GETTERS
	 */
	public int getXMovementPl1() {
		if (isKeyDown(Input.KEY_A)) return -100;
		if (isKeyDown(Input.KEY_D)) return 100;
		return 0;
	}
	
	public int getYMovementPl1() {
		if (isKeyDown(Input.KEY_W)) return -100;
		if (isKeyDown(Input.KEY_S)) return 100;
		return 0;
	}
	
	public int getXMovementPl2() {
		if (isKeyDown(Input.KEY_LEFT)) return -100;
		if (isKeyDown(Input.KEY_RIGHT)) return 100;
		return 0;
	}
	
	public int getYMovementPl2() {
		if (isKeyDown(Input.KEY_UP)) return -100;
		if (isKeyDown(Input.KEY_DOWN)) return 100;
		return 0;
	}
	
	
	
	
	public String getPressedpl1(GameContainer gc)
	{

		if(gc.getInput().isKeyPressed(Input.KEY_W)){
			previouspressedpl1=lastpressedpl1;
			return lastpressedpl1="Up";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_S)){
			previouspressedpl1=lastpressedpl1;
			return lastpressedpl1="Down";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_A)){
			previouspressedpl1=lastpressedpl1;
			return lastpressedpl1="Left";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_D)){
			previouspressedpl1=lastpressedpl1;
			return lastpressedpl1="Right";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_1)) {
			previouspressedpl1=lastpressedpl1;
			return lastpressedpl1="One";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
			previouspressedpl1=lastpressedpl1;
			return lastpressedpl1="Space";
		}
		return "";
		
	}
	
	public String getPressedpl2(GameContainer gc)
	{

		if(gc.getInput().isKeyPressed(Input.KEY_UP)){
			previouspressedpl2=lastpressedpl2;
			return lastpressedpl2="Up";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_DOWN)){
			previouspressedpl2=lastpressedpl2;
			return lastpressedpl2="Down";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_LEFT)){
			previouspressedpl2=lastpressedpl2;
			return lastpressedpl2="Left";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_RIGHT)){
			previouspressedpl2=lastpressedpl2;
			return lastpressedpl2="Right";
			}
		if(gc.getInput().isKeyPressed(Input.KEY_NUMPAD1)) {
			previouspressedpl2=lastpressedpl2;
			return lastpressedpl2="One";
		}
		if(gc.getInput().isKeyPressed(Input.KEY_NUMPAD0)) {
			previouspressedpl2=lastpressedpl2;
			return lastpressedpl2="Zero";
		}

		return "";
		
	}



}
