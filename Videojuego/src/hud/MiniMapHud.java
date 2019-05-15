package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.Constants;
import game.GameState;
import resources.ResourceLoader;

public class MiniMapHud implements IBasicHudComponent {

	private final GameState gameState;
	
	private Image miniMapImage;
	private Image playerOneIcon;
	private Image playerTwoIcon;
	
	public MiniMapHud(final GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public void init(GameContainer gc) {
		miniMapImage = ResourceLoader.loadImageFromUrl(Constants.PATH_INTERFACE_MINIMAP);
//		playerOneIcon = ResourceLoader.loadImageFromUrl(path);
//		playerTwoIcon = ResourceLoader.loadImageFromUrl(path);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) {
		// Mini map
		g.drawImage(miniMapImage, 10, 10);
		
		// Players icons
//		g.drawImage(playerOneIcon, x, y);
//		g.drawImage(playerTwoIcon, x, y);
	}
	
	@Override
	public void update() {
		
	}
}

