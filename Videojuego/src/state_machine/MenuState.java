package state_machine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import resources.ResourceLoader;

public class MenuState extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	
	private Image backgroundImage;
	private Image playButton;
	private float scale;
	
	/*
	 * Constructos
	 */
	public MenuState(final int stateId) {
		this.stateId = stateId;
		
		this.scale = Constants.GRAPHICS_MENU_SCALE;
	}

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_BACKGROUND);
		playButton = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_PLAYBUTTON);
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		playButton.draw(512 - playButton.getWidth() / 2, 320 - playButton.getHeight() / 2 , scale);
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
	}

	/*
	 * Getters
	 */
	@Override
	public int getID() {
		return stateId;
	}
	
}
