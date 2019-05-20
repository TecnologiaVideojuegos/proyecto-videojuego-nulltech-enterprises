package main;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import entities.Player;
import game.GameMode;
import game.GameState;
import state_machine.EndGameState;
import state_machine.MapState;
import state_machine.MenuState;
import state_machine.minigames.MiniGameJumpRollers;
import state_machine.minigames.MiniGameTest;

public class MainManager extends StateBasedGame {
	
	/*
	 * Attributes
	 */
	private GameMode gameMode;
	private GameState gameState;
	private Player[] players;
	
	private final int menuStateId = 0;
	private final int boardStateId = 5; // TODO
	private final int mapStateId = 1; // TODO
	private final int endGameStateId = 2;
	private final int minigameTestStateId = 3;
	private final int minigameJumpRollersStateId = 4;

	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);
		
		this.addState(new MenuState(menuStateId, this));
		this.addState(new MapState(mapStateId, this));
		this.addState(new EndGameState(endGameStateId, this));
		this.addState(new MiniGameTest(minigameTestStateId, this));
		this.addState(new MiniGameJumpRollers(minigameJumpRollersStateId, this));
	}

	/*
	 * Init
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menuStateId).init(gc, this);
		this.getState(mapStateId).init(gc, this);
		this.getState(endGameStateId).init(gc, this);
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
	
	public void initGameMode(final int pointToWins) {
		gameMode = new GameMode(pointToWins);
	}
	
	public void initGameState(final GameMode gameMode, final Player[] players) {
		ArrayList<Integer> minigames = new ArrayList<Integer>();
		minigames.add(minigameJumpRollersStateId);
		
		gameState = new GameState(gameMode, players, minigames);
	}
	
	public void initPlayers(final Player[] players) {
		this.players = players;
	}
	
	public GameMode getGameMode() { return gameMode; }
	public GameState getGameState() { return gameState; }
	public Player[] getPlayers() { return players; }


	
}
