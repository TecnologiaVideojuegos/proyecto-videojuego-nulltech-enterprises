package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import controllers.KeyboardController;
import game.GameState;
import util.Coordinates;
import util.MapLocation;

public class Player {
	
	/*
	 * Attributes
	 */
	private final String name;
	private int puntuation;
	private int availableMovements = 1;
	
	private Coordinates coordinates;
	private MapLocation mapLocation;
	
	private GameState gameState;
	
	private final KeyboardController keyboard;
	
	private Animation character;
	private int scale;
	
	private final PlayerGameState state;
	

	/*
	 * Constructors
	 */
	public Player(final String name) {
		this.name = name;
		mapLocation = new MapLocation();
		keyboard = new KeyboardController(600);
		state = new PlayerGameState();
	}
	
	public Player(final String name, final Animation character) {
		this.name = name;
		this.character = character;
		mapLocation = new MapLocation();
		keyboard = new KeyboardController(600);
		state = new PlayerGameState();
	}
	
	
	/*
	 * Render
	 */
	public void render(Graphics g, final int x, final int y) {
		if(character != null) {
			character.getCurrentFrame().draw(x, y, 0.25f);
		}
	}
	
	public void update(final int mapCount, final int delta) {
		if (!state.inputDisabled) {
			/** REMAKE THIS PART **/
			if (availableMovements > 0 && keyboard.getXMovementPl1() < 0) {
				mapLocation.updateLocation(-1, mapCount);
				availableMovements--;
				state.inputDisabled = true;
			} else if (availableMovements > 0 && keyboard.getXMovementPl1() > 0) {
				mapLocation.updateLocation(1, mapCount);
				availableMovements--;
				state.inputDisabled = true;
			} else if (keyboard.getActionButtonPl1() > 0) {
				gameState.setFinishingTurn(true);
			}
		} else if (state.inputDisabled && state.elapsedTime < 2000) {
			state.elapsedTime += delta;
		} else if (state.inputDisabled) {
			state.elapsedTime = 0;
			state.inputDisabled = false;
		}
	}
	
	
	/*
	 * Puntuation
	 */
	public int getPuntuation() { return puntuation; }
	
	public int updatePuntuation(final int points) {
		return puntuation += points;
	}
	
	
	/*
	 * Name
	 */
	public String getName() { return name; }
	
	/*
	 * Location
	 */
	public MapLocation getMapLocation() { return mapLocation; }
	public void setMapLocation(final MapLocation mapLocation) { this.mapLocation = mapLocation; } 
	
	public void setGameState (final GameState gameState) { this.gameState = gameState; }
	
	public int getAvailableMovements() { return availableMovements; }
	public void setAvailableMovements(final int availableMovements) { this.availableMovements = availableMovements; }
}

class PlayerGameState {
	boolean inputDisabled;
	int elapsedTime;
}
