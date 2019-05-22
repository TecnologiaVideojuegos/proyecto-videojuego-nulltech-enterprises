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
import state_machine.minigames.Minigame1;
import state_machine.minigames.Minigame2;
import state_machine.minigames.Minigame3;
import state_machine.minigames.Minigame4;

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
	private final int minigameJumpRollersStateId = 4;
	private final int minigame1Id= 5;
	private final int minigame2Id= 6;
	private final int minigame3Id= 7;
	private final int minigame4Id= 8;

	
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
		this.addState(new Minigame1(minigame1Id));
		this.addState(new Minigame2(minigame2Id, this));
		this.addState(new Minigame3(minigame3Id, this));
		this.addState(new Minigame4(minigame4Id, this));
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
		this.getState(minigame1Id).init(gc, this);
		this.getState(minigame2Id).init(gc, this);
		this.getState(minigame3Id).init(gc, this);
		this.getState(minigame4Id).init(gc, this);
		this.enterState(menuStateId); // DEBUG
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
//		minigames.add(minigame1Id);
		minigames.add(minigame2Id);
		minigames.add(minigame3Id);
		minigames.add(minigame4Id);

		gameState = new GameState(gameMode, players, minigames);
	}
	
	public void initPlayers(final Player[] players) {
		this.players = players;
	}
	
	public GameMode getGameMode() { return gameMode; }
	public GameState getGameState() { return gameState; }
	public Player[] getPlayers() { return players; }

	/*
	 * Get State Ids
	 */
	public int getMenuStateId() { return menuStateId; }
	public int getMapStateId() { return mapStateId; }
	public int getEndGameStateId() { return endGameStateId; }
	public int getMinigameTestStateId() { return minigameTestStateId; }
	
	
}