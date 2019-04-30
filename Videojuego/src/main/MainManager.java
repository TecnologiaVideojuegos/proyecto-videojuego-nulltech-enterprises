package main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import resources.ResourceLoader;
import state_machine.MenuState;
import state_machine.minigames.MiniGameTest;
import state_machine.minigames.Minigame1;
import state_machine.minigames.Minigame3;


public class MainManager extends StateBasedGame {
	
	/*
	 * Attributes
	 */

	private final int menuStateId = 0;
	private final int boardStateId = 1; // TODO
	private final int minigameTestStateId = 2;
	private final int minigame1Id = 3;
	private final int minigame3Id = 5;
	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);

		this.addState(new MenuState(menuStateId));
		this.addState(new MiniGameTest(minigameTestStateId));
		this.addState(new Minigame1(minigame1Id));
		this.addState(new Minigame3(minigame3Id));
	}

	/*
	 * Init
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuStateId).init(gc, this);
		this.getState(minigameTestStateId).init(gc, this);
		this.getState(minigame1Id);
		this.getState(minigame3Id);
		this.enterState(minigame3Id); // DEBUG
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
