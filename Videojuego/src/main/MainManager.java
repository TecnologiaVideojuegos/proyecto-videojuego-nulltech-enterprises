package main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import resources.ResourceLoader;
import state_machine.MenuState;
import state_machine.minigames.MiniGameJumpRollers;
import state_machine.minigames.MiniGameTest;

public class MainManager extends StateBasedGame {
	
	/*
	 * Attributes
	 */
	private final ResourceLoader resLoader;
	
	private final int menuStateId = 0;
	private final int boardStateId = 1; // TODO
	private final int minigameTestStateId = 2;
	private final int minigameJumpRollersStateId = 3;
	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);
		resLoader = new ResourceLoader();
		
		this.addState(new MenuState(menuStateId, resLoader));
		this.addState(new MiniGameTest(minigameTestStateId, resLoader));
		this.addState(new MiniGameJumpRollers(minigameJumpRollersStateId, resLoader));
	}

	/*
	 * Init
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuStateId).init(gc, this);
		this.getState(minigameTestStateId).init(gc, this);
		this.getState(minigameJumpRollersStateId).init(gc, this);
		
		this.enterState(minigameJumpRollersStateId); // DEBUG
	}

	/*
	 * Main
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
			AppGameContainer app = new AppGameContainer(new MainManager("Constants.WINDOW_TITLE"));
			app.setDisplayMode(Constants.WINDOW_DEFAULT_WIDTH, Constants.WINDOW_DEFAULT_HEIGHT, false); // Set window size as constants
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}


	
}
