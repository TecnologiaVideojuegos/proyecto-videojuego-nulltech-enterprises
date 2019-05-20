package state_machine.minigames;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import controllers.KeyboardController;
import hud.EndMiniGameHud;
import main.MainManager;
import minigames.GameObject;
import minigames.MiniGameCurrentState;
import resources.ResourceLoader;

public class MiniGameJumpRollers extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	
	private final MainManager mainManager;
	private final KeyboardController keyboard;
	private MiniGameCurrentState state;
	private final EndMiniGameHud endMiniGame;
	
	// Images
	private Image backgroundImage;
	private Image overlayImage;
	private Animation rollerImage;

	// Rollers
	private ArrayList<GameObject> arrayRollers1;
	private ArrayList<GameObject> arrayRollers2;
	
	// Players
	private GameObject player1;
	private GameObject player2;
	
	private int speedDificulty;
	private int obstaclesJumped;
	
	private int playerOneMinY, playerOneMaxY;
	private int playerTwoMinY, playerTwoMaxY;
	
	
	/*
	 * Constructors
	 */
	public MiniGameJumpRollers(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		this.mainManager = mainManager;
		
		keyboard = new KeyboardController(640); 
		
		arrayRollers1 = new ArrayList<GameObject>();
		arrayRollers2 = new ArrayList<GameObject>();

		speedDificulty = 3;
		obstaclesJumped = 0;
		state = new MiniGameCurrentState();
		endMiniGame = new EndMiniGameHud(mainManager, keyboard);
	}
	

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image(Constants.PATH_MINIGAME_TEST_BACKGROUND);
		overlayImage = new Image(Constants.PATH_MINIGAME_JUMP_ROLLERS_OVERLAY);
		rollerImage = ResourceLoader.loadAnimationFromSpriteSheetUrl(Constants.PATH_MINIGAME_JUMP_ROLLERS_ROLLER, 32, 64, 1);
		Animation playerAnim = ResourceLoader.loadAnimationFromSpriteSheetUrl(Constants.PATH_MINIGAME_TEST_MONKEY, 220, 280, 5);
		
		player1 = new GameObject(playerAnim, null, (int) (Constants.WINDOW_DEFAULT_WIDTH * 0.1), (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.45 - playerAnim.getHeight() * 0.25), 0.25f); // Set values as constants
		player2 = new GameObject(playerAnim, null, (int) (Constants.WINDOW_DEFAULT_WIDTH * 0.1), (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.95 - playerAnim.getHeight() * 0.25), 0.25f); // Set values as constants
		
		playerOneMinY = (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.45 - playerAnim.getHeight() * 0.25);
		playerOneMaxY = (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.45 - playerAnim.getHeight() * 0.25) - 75;
		
		playerTwoMinY = (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.95 - playerAnim.getHeight() * 0.25);
		playerTwoMaxY = (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.95 - playerAnim.getHeight() * 0.25) - 75;
		
		endMiniGame.init(gc);
		
		addRollersToArrays(20);
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// Below player
		backgroundImage.draw();
		
		// Player level
		for (GameObject go : arrayRollers1) { go.render(g); }
		for (GameObject go : arrayRollers2) { go.render(g); }
		
		player1.render(g);
		player2.render(g);
		
		// Above player
		overlayImage.draw(0, 0, Constants.WINDOW_DEFAULT_WIDTH, Constants.WINDOW_DEFAULT_HEIGHT);
		
		// State
		if (state.endMiniGameScreen) {
			endMiniGame.render(gc, g);
		} else if (state.gamePaused) {
			// TODO: Pausa
		}
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		if (!state.gamePaused && !state.endMiniGameScreen) {
			// Player movement
			playerOneMovement();
			playerTwoMovement();
			
			if (keyboard.getActionButtonPl1() == 2) {
				state.gamePaused = true;
			}
			
			/** REFACTOR THIS PART **/
			for (GameObject go : (ArrayList<GameObject>) arrayRollers1.clone()) {
				go.updateXByIncrease(-speedDificulty);
				if (player1.getCollisionBox().intersects(go.getCollisionBox())) {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(2);
				} else if (go.getX() < -go.getAnimation().getCurrentFrame().getWidth() * go.getScale()) {
					arrayRollers1.remove(go);
					obstaclesJumped++;
					
					// Increment dificulty
					if (obstaclesJumped % 20 == 0) { speedDificulty++; }
					
					// Add obstacles
					if (arrayRollers1.size() < 10) { addRollersToArrays(20); }
				}
			}
			
			for (GameObject go : (ArrayList<GameObject>) arrayRollers2.clone()) {
				go.updateXByIncrease(-speedDificulty);
				if (player2.getCollisionBox().intersects(go.getCollisionBox())) {
					state.endMiniGameScreen = true;
					endMiniGame.setPlayerWinner(endMiniGame.getPlayerWinner() == 0 ? 2 : 3);
				} else if (go.getX() < -go.getAnimation().getCurrentFrame().getWidth() * go.getScale()) {
					arrayRollers2.remove(go);
				}
			}
			/** REFACTOR THIS PART **/
			
		} else if (state.endMiniGameScreen) {
			endMiniGame.update();
		} else {
			// TODO: PAUSE
		}
	}
	
	
	/*
	 * Create Rollers
	 */
	private void addRollersToArrays(final int numberOfRollers) {
		int posX;
		for (int i = 0; i < numberOfRollers; i++) {
			posX = (arrayRollers1.size() > 0 ? arrayRollers1.get(arrayRollers1.size() - 1).getX() : Constants.WINDOW_DEFAULT_WIDTH) + ThreadLocalRandom.current().nextInt(100, 500);
			arrayRollers1.add(new GameObject(rollerImage, null, posX, (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.45 - rollerImage.getHeight() * 0.25), 0.5f));
			arrayRollers2.add(new GameObject(rollerImage, null, posX, (int) (Constants.WINDOW_DEFAULT_HEIGHT * 0.95 - rollerImage.getHeight() * 0.25), 0.5f));			
		}
	}
	
	
	/*
	 * Player movement
	 */
	private void playerOneMovement() {
		if (player1.getYMovement() == 0 && keyboard.getYMovementPl1() < 0) {
			player1.setYMovement(-3);
		} else if (player1.getYMovement() < 0) {
			if (player1.getY() > playerOneMaxY) {
				player1.updateYByYMovement();
			} else {
				player1.setYMovement(3);
			}
		} else if (player1.getY() > 0) {
			if (player1.getY() < playerOneMinY) {
				player1.updateYByYMovement();
			} else {
				player1.setYMovement(0);
			}
		}
	}
	
	private void playerTwoMovement() {
		if (player2.getYMovement() == 0 && keyboard.getYMovementPl2() < 0) {
			player2.setYMovement(-3);
		} else if (player2.getYMovement() < 0) {
			if (player2.getY() > playerTwoMaxY) {
				player2.updateYByYMovement();
			} else {
				player2.setYMovement(3);
			}
		} else if (player2.getY() > 0) {
			if (player2.getY() < playerTwoMinY) {
				player2.updateYByYMovement();
			} else {
				player2.setYMovement(0);
			}
		}
	}
	

	/*
	 * State ID
	 */
	@Override
	public int getID() {
		return stateId;
	}

}
