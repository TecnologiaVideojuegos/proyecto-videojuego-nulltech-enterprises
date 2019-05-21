package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.Constants;
import game.GameState;
import resources.ResourceLoader;
import util.Coordinates;

public class MovementHud implements IBasicHudComponent{
	
	private final GameState gameState;
	private Image movementImage1;
	private Image movementImage2;
	private Coordinates textCoordinates;
	private float x;
			
	
	public MovementHud(final GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void init(GameContainer gc) {
		movementImage1 = ResourceLoader.loadImageFromUrl(Constants.PATH_HUD_MAP_PLAYER_ONE_INFO);
		movementImage2 = ResourceLoader.loadImageFromUrl(Constants.PATH_HUD_MAP_PLAYER_TWO_INFO);
		textCoordinates = new Coordinates((int) (gc.getWidth() - (movementImage1.getWidth() * 0.2) + 60), 35);
		
		x = (float) (gc.getWidth() - (movementImage1.getWidth() * 0.2) - 10);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (gameState.getPlayerTurn() == 0) {
			movementImage1.draw(x, 20,  0.2f);
		} else {
			movementImage2.draw(x, 20,  0.2f);
		}
		g.drawString(String.valueOf(gameState.getPlayerByTurn().getAvailableMovements()), textCoordinates.getX(), textCoordinates.getY());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
