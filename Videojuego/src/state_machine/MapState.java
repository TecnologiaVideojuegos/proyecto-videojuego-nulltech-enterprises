package state_machine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import constants.Constants;
import entities.Player;
import main.MainManager;
import maps.Map;
import resources.ResourceLoader;

public class MapState extends BasicGameState {
	
	private Map[] maps;
	private final int stateId;
	private final MainManager mainManager;
	
	public MapState(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		this.mainManager = mainManager;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TEST
		mainManager.initGameMode(1);
		Player[] players = {new Player("Jugador 1", ResourceLoader.loadAnimationFromSpriteSheetUrl(Constants.PATH_MINIGAME_TEST_MONKEY, 220, 280, 10)), new Player("Jugador 2")};
		mainManager.initPlayers(players);
		mainManager.initGameState(mainManager.getGameMode(), mainManager.getPlayers());
		// TEST
		
		maps = new Map[1];
		maps[0] = new Map(new TiledMap("res/maps/test.tmx"));
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Map map = maps[mainManager.getGameState().getPlayerByTurn().getMapLocation().getMapId()];
		// Map
		map.render(0, 0);
		
		// Player
		int squareId = mainManager.getGameState().getPlayerByTurn().getMapLocation().getSquareId();
		mainManager.getGameState().getPlayerByTurn().render(g, map.getTiledMap().getObjectX(0, squareId), map.getTiledMap().getObjectY(0, squareId));
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateId;
	}

}
