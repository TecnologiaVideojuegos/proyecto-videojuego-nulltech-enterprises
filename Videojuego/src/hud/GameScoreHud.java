package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.Constants;
import game.GameState;
import resources.ResourceLoader;

public class GameScoreHud implements IBasicHudComponent {

	private final GameState gameState;
	private Image gameScoreImage;
	
	public GameScoreHud(final GameState gameState) {
		this.gameState = gameState; 
	}
	
	@Override
	public void init(GameContainer gc) {
		gameScoreImage = ResourceLoader.loadImageFromUrl(Constants.PATH_INTERFACE_POINTS);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(gameScoreImage, gc.getWidth() - gameScoreImage.getWidth() - 10, 10);
	}
	
	@Override
	public void update() {
		
	}
	
}
