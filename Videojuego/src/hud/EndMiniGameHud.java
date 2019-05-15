package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.Constants;
import game.GameState;
import resources.ResourceLoader;

public class EndMiniGameHud implements IBasicHudComponent {

	private final GameState gameState;
	private Image windowImage;
	private int playerWinner;

	public EndMiniGameHud(final GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void init(GameContainer gc) {
		windowImage = ResourceLoader.loadImageFromUrl(Constants.PATH_INTERFACE_MOVEMENTS);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(windowImage, 0, 0);
		
		g.drawString(gameState.getPlayerByIdx(0).getName(), 10, 10);
		g.drawString(gameState.getPlayerByIdx(1).getName(), 10, 30);

	}
	
	public void setPlayerWinner(final int playerWinner) { this.playerWinner = playerWinner; }

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
