package state_machine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import controllers.KeyboardController;
import main.MainManager;
import resources.ResourceLoader;
public class MenuState extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	private KeyboardController keyboard;
	private MainManager mainManager;
	private int menuState;
	
	private Image backgroundImage;
	private Image playButton;
	private Image playButtonAM;
	private Image exitButton;
	private Image exitButtonAM;
	private float scale;
	
	/*
	 * Constructos
	 */
	public MenuState(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		this.mainManager = mainManager;
		
		keyboard = new KeyboardController(640);
		this.scale = Constants.GRAPHICS_MENU_SCALE;
	}

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_BACKGROUND);
		playButton = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_PLAYBUTTON);
		playButtonAM = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_PLAYBUTTON_AM);
		exitButton = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_EXITBUTTON);
		exitButtonAM = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_EXITBUTTON_AM);
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
//		g.drawImage(backgroundImage, 0, 0);
		
		switch (menuState) {
			case 0:
				playButtonAM.draw(250,100, scale);
				exitButton.draw(250, 300, scale);
				break;
			case 1:
				playButton.draw(250, 100, scale);
				exitButtonAM.draw(250, 300, scale);
				break;
		}
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if(keyboard.getPressedpl1(gc) != ""){
			if(keyboard.lastpressedp1 == "Down") {
				menuState = ++menuState > 1 ? 1 : menuState;
			} else if(keyboard.lastpressedp1 == "Up") {
				menuState = --menuState < 0 ? 0 : menuState;
			} else if (keyboard.lastpressedp1 == "Enter") {
				switch (menuState) {
					case 0:
						mainManager.enterState(mainManager.getMapStateId());
						break;
					case 1:
						System.exit(0);
						break;
				}
			}
		}
	}
	
	
	/*
	 * Getters
	 */
	@Override
	public int getID() {
		return stateId;
	}
	
}