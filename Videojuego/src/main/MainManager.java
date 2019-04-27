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
import state_machine.minigames.Minigame2;

public class MainManager extends StateBasedGame {
	
	/*
	 * Attributes
	 */
	private final ResourceLoader resLoader;
	
	private final int menuStateId = 0;
	private final int boardStateId = 1; // TODO
	private final int minigameTestStateId = 2;
	private final int minigame1Id= 3;
	private final int minigame2Id = 4;
	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);
		resLoader = new ResourceLoader();
		
		this.addState(new MenuState(menuStateId, resLoader));
		this.addState(new MiniGameTest(minigameTestStateId, resLoader));
		this.addState(new Minigame1(minigame1Id, resLoader));
		this.addState(new Minigame2(minigame2Id,resLoader));
	}

	/*
	 * Init
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuStateId).init(gc, this);
		this.getState(minigameTestStateId).init(gc, this);
		this.getState(minigame1Id).init(gc, this);
		this.getState(minigame2Id).init(gc, this);
		this.enterState(minigame2Id); // DEBUG
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
