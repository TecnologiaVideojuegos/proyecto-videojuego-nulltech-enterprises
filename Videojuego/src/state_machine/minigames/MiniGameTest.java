package state_machine.minigames;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import constants.Constants;
import controllers.KeyboardController;
import minigames.GameObject;
import resources.ResourceLoader;

public class MiniGameTest extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	
	private final KeyboardController keyboard;
	private final ResourceLoader resLoader;
	
	private Image backgroundImage;
	private Image bananaImage;
	private Image monkeyImage;

	private ArrayList<GameObject> arrayBananas;
	private GameObject player;
	
	private int spawnSpeed;
	private int speedDificulty;
	private int x;
	private int elapsedTime;
	
	
	/*
	 * Constructors
	 */
	public MiniGameTest(final int stateId, final ResourceLoader resLoader) {
		this.stateId = stateId;
		this.resLoader = resLoader;
		
		keyboard = new KeyboardController(640); 
		arrayBananas = new ArrayList<GameObject>();
		
		x = 500;
		elapsedTime = 0;
		spawnSpeed = 50;
		speedDificulty = 1;
	}
	

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image(Constants.PATH_MINIGAME_TEST_BACKGROUND);
		bananaImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME_TEST_BANANA);
		monkeyImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME_TEST_MONKEY);
		//player = new GameObject(monkeyImage, x, 520, 0.25f); // Set values as constants
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		backgroundImage.draw();
		
		for (GameObject go : arrayBananas) { go.render(g); }
		
		player.render(g);
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		player.updateX(x += keyboard.getXMovement() * delta / 200f); // Set values as constants
		
		if (elapsedTime++ > spawnSpeed) {
			arrayBananas.add(createBanana());
			elapsedTime = 0;
		}
		
		for (GameObject go : arrayBananas) {
			go.updateYByIncrease(speedDificulty);
			/*
			if (player.getCollisionBox().intersects(go.getCollisionBox())) {
				go.setDeleted(true);
			}
			*/
		}
		
	}
	
	/*
	 * Create Bananas
	 */
	private GameObject createBanana() {
		/*
		return new GameObject(bananaImage, ThreadLocalRandom.current().nextInt(0, 1024), 0, 0.1f); // Set values as constants
		*/
		return null;
	}

	/*
	 * Getters
	 */
	@Override
	public int getID() {
		return stateId;
	}

}
