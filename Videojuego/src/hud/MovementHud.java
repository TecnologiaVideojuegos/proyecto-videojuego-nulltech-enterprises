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
	private Image movementImage;
	private Coordinates textCoordinates;
	
	public MovementHud(final GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void init(GameContainer gc) {
		movementImage = ResourceLoader.loadImageFromUrl(Constants.PATH_INTERFACE_MOVEMENTS);
		textCoordinates = new Coordinates((int)(gc.getWidth() - (movementImage.getWidth() * 0.5) - 10), (int)((movementImage.getWidth() * 1.5) + 20));
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(movementImage, gc.getWidth() - movementImage.getWidth() - 10, movementImage.getWidth() + 20);
		g.drawString(String.valueOf(gameState.getPlayerByTurn().getAvailableMovements()), textCoordinates.getX(), textCoordinates.getY());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
