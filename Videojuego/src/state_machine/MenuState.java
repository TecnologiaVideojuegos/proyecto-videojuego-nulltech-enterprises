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
import org.newdawn.slick.state.*;
public class MenuState extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	private final ResourceLoader resLoader;
	private KeyboardController keyboard;
	private MainManager Juego;
	private int MenuState;
	
	private Image backgroundImage;
	private Image playButton;
	private Image playButtonAM;
	private Image exitButton;
	private Image exitButtonAM;
	private float scale;
	
	/*
	 * Constructos
	 */
	public MenuState(final int stateId, final ResourceLoader resLoader) {
		this.stateId = stateId;
		this.resLoader = resLoader;
		keyboard = new KeyboardController(640);
		this.scale = Constants.GRAPHICS_MENU_SCALE;
		this.MenuState=0;
	}

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = resLoader.loadImageFromUrl(Constants.PATH_MENU_BACKGROUND);
		playButton = resLoader.loadImageFromUrl(Constants.PATH_MENU_PLAYBUTTON);
		playButtonAM = resLoader.loadImageFromUrl(Constants.PATH_MENU_PLAYBUTTON_AM);
		exitButton = resLoader.loadImageFromUrl(Constants.PATH_MENU_EXITBUTTON);
		exitButtonAM = resLoader.loadImageFromUrl(Constants.PATH_MENU_EXITBUTTON_AM);
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		if(MenuState==0)
		{
		playButtonAM.draw(250,100, scale);
		exitButton.draw(250, 300, scale);
		}
		if(MenuState==1)
		{
		playButton.draw(250,100, scale);
		exitButtonAM.draw(250, 300, scale);
		}
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		keyboard = new KeyboardController(640);
		if(keyboard.getPressedpl1(gc)!= ""){
		if(keyboard.lastpressedp1=="Down") 
		{
			MenuState++;
		}
		if(keyboard.lastpressedp1=="Up") 
		{
			MenuState--;
		}
		if(MenuState==2)
		{
			Juego.enterState(3); 
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
