package state_machine;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import constants.Constants;
import entities.Player;
import game.GameState;
import hud.IBasicHudComponent;
import hud.MiniMapHud;
import hud.MovementHud;
import main.MainManager;
import maps.Map;
import resources.ResourceLoader;

public class MapState extends BasicGameState {
	
	private Map[] maps;
	private final int stateId;
	private final MainManager mainManager;
	private final MapGameState state;
	
	private Image playerOneTurn;
	private Image playerTwoTurn;
	
	private IBasicHudComponent[] hudComponents;
	
	private int x, y;
	
	public MapState(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		this.mainManager = mainManager;
		
		state = new MapGameState();
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TEST
		
		mainManager.initGameMode(1);
		Player[] players = {new Player("Jugador 1", ResourceLoader.loadAnimatioFromSpriteSheet(ResourceLoader.loadSpriteSheetfromurl(Constants.PATH_MINIGAME_TEST_MONKEY, 220, 280), 10)), new Player("Jugador 2", ResourceLoader.loadAnimatioFromSpriteSheet(ResourceLoader.loadSpriteSheetfromurl(Constants.PATH_MINIGAME_TEST_MONKEY, 220, 280), 10))};
		mainManager.initPlayers(players);
		mainManager.initGameState(mainManager.getGameMode(), mainManager.getPlayers());
		
		// TEST
		
		maps = new Map[1];
		maps[0] = new Map(new TiledMap("res/maps/test.tmx"));
		
		playerOneTurn = ResourceLoader.loadImageFromUrl(Constants.PATH_MAPSTATE_PLAYER_ONE);
		playerTwoTurn = ResourceLoader.loadImageFromUrl(Constants.PATH_MAPSTATE_PLAYER_TWO);
		
		hudComponents = loadHudComponets(mainManager.getGameState());
		for(IBasicHudComponent c : hudComponents) { c.init(gc); }
		
		x = ((gc.getWidth() - playerOneTurn.getWidth())  / 2 - 10);
		y = (gc.getHeight() / 2 - playerOneTurn.getHeight() - 10);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Map
		Map map = maps[mainManager.getGameState().getPlayerByTurn().getMapLocation().getMapId()];
		map.render(0, 0);
		
		// Player
		int squareId = mainManager.getGameState().getPlayerByTurn().getMapLocation().getSquareId();
		mainManager.getGameState().getPlayerByTurn().render(g, map.getTiledMap().getObjectX(0, squareId), map.getTiledMap().getObjectY(0, squareId));
		
		// Animations
		if (state.switchingTurnAnimation) {
			g.drawImage(mainManager.getGameState().getPlayerTurn() == 0 ? playerOneTurn : playerTwoTurn, x, y);
		} else if (state.loadingMapAnimation) {
			
		} else if (state.loadingMinigameAnimation) {
			
		}
		
		//Interface
		for(IBasicHudComponent c : hudComponents) { c.render(gc, g); }
		
		//Flechas
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (mainManager.getGameState().getFinishingTurn()) {
			
			if (mainManager.getGameState().nextTurn() == 0) {
				
				// LAUNCH MINIGAME
				System.out.println("MINIGAME");
				state.loadingMinigameAnimation = true;
				state.loadingMapAnimation = true;
				state.switchingTurnAnimation = true;
				enterMiniGame(gc, sbg);
				
			} else {
				
				// SWITCH PLAYER
				System.out.println("\n\tPlayer switch\n");
				state.switchingTurnAnimation = true;
				
			}
			
			mainManager.getGameState().setFinishingTurn(false);
			
		} else {
			
			if (state.switchingTurnAnimation && state.timeElapsed < 3000) {
				
				state.timeElapsed += delta;
				
			} else if (state.switchingTurnAnimation) {
				
				state.timeElapsed = 0;
				state.switchingTurnAnimation = false;
				
			} else {
				
				mainManager.getGameState().getPlayerByTurn().update(maps.length, delta);
				
			}
			
		}
		
	}
	
	private IBasicHudComponent[] loadHudComponets(final GameState gameState) {
		IBasicHudComponent[] components = new IBasicHudComponent[2];
		
		components[0] = new MiniMapHud(gameState);
		components[1] = new MovementHud(gameState);
		
		return components;
	}
	
	private void enterMiniGame(GameContainer gc, StateBasedGame sbg) {
		try {
			ArrayList<Integer> notPlayed = mainManager.getGameState().getMiniGameStateIdsNotPlayed();
			ArrayList<Integer> played = mainManager.getGameState().getMiniGameStateIdsPlayed();

			if(notPlayed.isEmpty()) {
				mainManager.getGameState().setMiniGameStateIdsNotPlayed(played);
			} else if (notPlayed.isEmpty() && !played.isEmpty()) {
				for(Integer i : played) {
					notPlayed.add(i);
				}
				played.clear();
			}
			
			if (notPlayed.size() > 1) {
				int idx = ThreadLocalRandom.current().nextInt(0, notPlayed.size());
				played.add(notPlayed.get(idx));
				notPlayed.remove(idx);
				
				mainManager.getState(played.get(played.size() - 1)).init(gc, sbg);
				mainManager.enterState(played.get(played.size() - 1));
			} else {
				mainManager.enterState(notPlayed.get(0));
			}
			
			
			
		} catch (Exception e) {
			System.out.println("RIP");
		}
	}

	
	@Override
	public int getID() {
		return stateId;
	}
	
}

class MapGameState {
	boolean switchingTurnAnimation = true;
	boolean loadingMinigameAnimation;
	boolean loadingMapAnimation;
	int timeElapsed;
}