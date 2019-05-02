package main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import entities.Player;
import game.GameMode;
import game.GameState;
import state_machine.EndGameState;
import state_machine.MenuState;
import state_machine.minigames.MiniGameTest;

public class MainManager extends StateBasedGame {
	
	/*
	 * Attributes
	 */
	private GameMode gameMode;
	private GameState gameState;
	private Player[] players;
	
	private final int menuStateId = 0;
	private final int mapStateId = 1; // TODO
	private final int endGameStateId = 2;
	private final int minigameTestStateId = 3;
	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);
		
		this.addState(new MenuState(menuStateId, this));
		this.addState(new EndGameState(endGameStateId, this));
		this.addState(new MiniGameTest(minigameTestStateId));
	}

	/*
	 * Init
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuStateId).init(gc, this);
		this.getState(endGameStateId).init(gc, this);
		this.getState(minigameTestStateId).init(gc, this);
		
		this.enterState(menuStateId); // DEBUG
	}

	/*
	 * Main
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
			AppGameContainer app = new AppGameContainer(new MainManager("Constants.WINDOW_TITLE"));
			app.setDisplayMode(1024, 640, false); // Set window size as constants
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void initGameMode(final int pointToWins) {
		gameMode = new GameMode(pointToWins);
	}
	
	public void initGameState(final GameMode gameMode, final Player[] players) {
		gameState = new GameState(gameMode, players);
	}
	
	public void initPlayers(final Player[] players) {
		this.players = players;
	}
	
	public GameMode getGameMode() { return gameMode; }
	public GameState getGameState() { return gameState; }
	public Player[] getPlayers() { return players; }


	
}
