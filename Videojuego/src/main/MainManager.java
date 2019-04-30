package main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import resources.ResourceLoader;
import state_machine.MenuState;
import state_machine.minigames.MiniGameTest;


public class MainManager extends StateBasedGame {
	
	/*
	 * Attributes
	 */

	private final int menuStateId = 0;
	private final int boardStateId = 1; // TODO
	private final int minigameTestStateId = 2;

	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);

		
		this.addState(new MenuState(menuStateId));
		this.addState(new MiniGameTest(minigameTestStateId));

	}

	/*
	 * Init
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuStateId).init(gc, this);
		this.getState(minigameTestStateId).init(gc, this);
		this.enterState(minigameTestStateId); // DEBUG
	}

	/*
	 * Main
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
			AppGameContainer app = new AppGameContainer(new MainManager("Constants.WINDOW_TITLE"));
			app.setDisplayMode(1024, 512, false); // Set window size as constants
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}


	
}
