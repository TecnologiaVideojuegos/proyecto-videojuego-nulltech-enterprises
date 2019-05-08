package state_machine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import controllers.KeyboardController;
import entities.Player;
import main.MainManager;
import resources.ResourceLoader;

public class MenuState extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	private final MainManager mainManager;
	private KeyboardController keyboard;
	private int MenuState;
	
	private Image backgroundImage;
	private Image playButton;
	private Image options;
	private Image exitButton;
	private Image credits;
	private float scale;
	
	
	/*
	 * Constructos
	 */
	public MenuState(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		this.mainManager = mainManager;
		
		this.scale = Constants.GRAPHICS_MENU_SCALE;
		keyboard = new KeyboardController(640);
		this.MenuState=0;
	}

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_BACKGROUND);
		playButton = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_PLAYBUTTON);
		options = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_OPTIONS);
		exitButton = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_EXITBUTTON);
		credits = ResourceLoader.loadImageFromUrl(Constants.PATH_MENU_CREDITS);
		
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawImage(backgroundImage, 0, 0);
		if(MenuState==0)
		{
		playButton.draw(1,2);
		}
		if(MenuState==1)
		{
		options.draw(4,137);
		}
		if(MenuState==2)
		{
		credits.draw(20,311);
		}
		if(MenuState==3)
		{
		exitButton.draw(37,473);
		}
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		keyboard = new KeyboardController(640);
		if(keyboard.getPressedpl1(gc)!= "")
		{
			if((keyboard.lastpressedp1=="Down") && (MenuState < 3))
			{
				MenuState++;
			}
			if(keyboard.lastpressedp1=="Up" && (MenuState > 0)) 
			{
				MenuState--;
			}
			if(keyboard.lastpressedp1=="Enter")
			{
				switch(MenuState)
				{
				case 0:sbg.enterState(4);break;
				case 1:break; //opciones
				case 2:break;//creditos
				case 3:sbg.enterState(-1);
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
	
	private void initComponents(final int pointsToWin, final Player[] players) {
		mainManager.initGameMode(pointsToWin);
		mainManager.initPlayers(players);
		mainManager.initGameState(mainManager.getGameMode(), mainManager.getPlayers());
	}
	
}