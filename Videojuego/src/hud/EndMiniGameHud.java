package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.Constants;
import controllers.KeyboardController;
import main.MainManager;
import resources.ResourceLoader;

public class EndMiniGameHud implements IBasicHudComponent {

	/*
	 * Attributes
	 */
	private final MainManager mainManager;
	private final KeyboardController keyboard;
	private Image playerOneWinsImage;
	private Image playerTwoWinsImage;
	private Image drawImage;
	private int playerWinner;
	private int posX, posY;

	
	/*
	 * Constructors
	 */
	public EndMiniGameHud(final MainManager mainManager, final KeyboardController keyboard) {
		this.mainManager = mainManager;
		this.keyboard = keyboard;
	}

	
	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc) {
		playerOneWinsImage = ResourceLoader.loadImageFromUrl(Constants.PATH_HUD_WINNER_PLAYER_ONE);
		playerTwoWinsImage = ResourceLoader.loadImageFromUrl(Constants.PATH_HUD_WINNER_PLAYER_TWO);
		drawImage = ResourceLoader.loadImageFromUrl(Constants.PATH_HUD_DRAW);
		
		posX = gc.getWidth() / 2 - playerOneWinsImage.getWidth() / 2;
		posY = gc.getHeight() / 2 - playerOneWinsImage.getHeight() / 2;
	}

	
	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(playerWinner == 1 ? playerOneWinsImage : playerWinner == 2 ? playerTwoWinsImage : drawImage, posX, posY);
	}
	
	
	/*
	 * Update
	 */
	@Override
	public void update() {
		if (keyboard.getActionButtonPl1() == 1) {
			//GIVE POINTS TO PLAYERS
			switch(playerWinner) {
				case 1:
					mainManager.getGameState().getPlayerByIdx(0).setAvailableMovements(mainManager.getGameMode().getMovementsWhenWin());
					mainManager.getGameState().getPlayerByIdx(1).setAvailableMovements(mainManager.getGameMode().getMovementsWhenLose());
					break;
				case 2:
					mainManager.getGameState().getPlayerByIdx(0).setAvailableMovements(mainManager.getGameMode().getMovementsWhenLose());
					mainManager.getGameState().getPlayerByIdx(1).setAvailableMovements(mainManager.getGameMode().getMovementsWhenWin());
					break;
				case 3:
					System.out.println("empate");
					mainManager.getGameState().getPlayerByIdx(0).setAvailableMovements(mainManager.getGameMode().getMovementsWhenWin());
					mainManager.getGameState().getPlayerByIdx(1).setAvailableMovements(mainManager.getGameMode().getMovementsWhenWin());
			}
			
			// ENTER MAP STATE
			mainManager.enterState(1);
		}
	}
	
	
	/*
	 * Player winner
	 */
	public int getPlayerWinner() { return playerWinner; }
	public void setPlayerWinner(final int playerWinner) { this.playerWinner = playerWinner; }


}
