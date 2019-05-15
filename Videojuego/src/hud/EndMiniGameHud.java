package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import constants.Constants;
import controllers.KeyboardController;
import main.MainManager;
import resources.ResourceLoader;

public class EndMiniGameHud implements IBasicHudComponent {

	private final MainManager mainManager;
	private final KeyboardController keyboard;
	private Image windowImage;
	private int playerWinner;

	public EndMiniGameHud(final MainManager mainManager, final KeyboardController keyboard) {
		this.mainManager = mainManager;
		this.keyboard = keyboard;
	}

	@Override
	public void init(GameContainer gc) {
		windowImage = ResourceLoader.loadImageFromUrl(Constants.PATH_INTERFACE_MOVEMENTS);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawImage(windowImage, 0, 0);
		/*
		g.drawString(mainManager.getGameState().getPlayerByIdx(0).getName(), 10, 10);
		g.drawString(mainManager.getGameState().getPlayerByIdx(1).getName(), 10, 30);
		*/
	}
	
	public int getPlayerWinner() { return playerWinner; }
	public void setPlayerWinner(final int playerWinner) { this.playerWinner = playerWinner; }

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
					mainManager.getGameState().getPlayerByIdx(0).setAvailableMovements(mainManager.getGameMode().getMovementsWhenWin());
					mainManager.getGameState().getPlayerByIdx(1).setAvailableMovements(mainManager.getGameMode().getMovementsWhenWin());
			}
			
			// ENTER MAP STATE
			mainManager.enterState(1);
		}
	}

}
