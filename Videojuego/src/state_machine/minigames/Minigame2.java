package state_machine.minigames;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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

public class Minigame2 extends BasicGameState{

	private final int stateId;
	
	private final KeyboardController keyboard;
	private final ResourceLoader resLoader;
	
	private Image backgroundImage;
	private Image ballImage;
	private Image thunderZoneImage;
	private Image shockImage;
	private Image playerImage;

	private GameObject[] ballarray;
	private GameObject player;
	private GameObject zonethunder;
	
	private final int numberBalls;
	private int speedBall;
	private int stopTime;
	private int speedTimeDelay;
	private int stopTimeDelay;
	private int puntuacion;
	private int espera;
	private ArrayList<Coordinates> coordinates = new ArrayList<>();
	
	
	/*
	 * Constructors
	 */
	public Minigame2(final int stateId, final ResourceLoader resLoader) {
		this.stateId = stateId;
		this.resLoader = resLoader;
		
		keyboard = new KeyboardController(640);
		
		numberBalls = 4;
		ballarray = new GameObject[numberBalls];
		speedTimeDelay = 100;
		stopTimeDelay = 350;
		bresenham(200, 120, 500, 10);
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_BACKGROUND);
		ballImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_BALL);
		thunderZoneImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_THUNDER_ZONE);
		shockImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_SHOCK);
		playerImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_PLAYER);

		player = new GameObject(playerImage, 400, 100, 2.0f); 			// Export values to constants
		zonethunder = new GameObject(thunderZoneImage, 64, 64, 2.0f);   // Export values to constants
		
		for(int i = 0 ; i < numberBalls; i++) {
			ballarray[i] = new GameObject(ballImage, 0, 0, 2.0f); 		// Export values to constants
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		backgroundImage.draw();
		player.render(g);
		zonethunder.render(g);
		shockImage.draw();
		
		for(GameObject go : ballarray) {
			go.render(g);
		}
		
		for(int i = 0; i < coordinates.size(); i++) {
			g.drawString(String.valueOf(coordinates.get(i).getX()), 200 + 10 * i, 200 + 10 * i);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {		
		if(speedBall++ < speedTimeDelay){
			updateMove();
		}
		if(stopTime++ > stopTimeDelay){
			speedBall = 0;
			stopTime = 0;
		}
	}

	private void updateMove() {
		double posX, posY;
		int theta;
		int r = 256;
		
		for(GameObject go : ballarray) {
			theta=ThreadLocalRandom.current().nextInt(0, 360);
			posX = r * Math.cos(theta) + 460;
			posY = r * Math.sin(theta) + 220;
			
			go.setX((int)posX);
			go.setY((int)posY);
		}
	}
	
	private void bresenham(int x, int y, final int x1, final int y1) {
		int stepX, stepY, p;
		int dx = x1 - x;
		int dy = y1 - y;

		if (dx < 0) {
			dx *= -1;
			stepX = -1;
		} else {
			stepX = 1;
			dx = x1 - x;
		}
		
		if (dy < 0) {
			dy *= -1;
			stepY = -1;
		} else {
			stepY = 1;
		}
		
		if (dx > dy) {
			p = 2 * dy - dx;
			
			while (x != x1) {
				x += stepX;
				
				if (p < 0) {
					p += 2 * dy;
				} else {
					p +=  2 * (dy - dx);
					y += stepY;
				}
				
				coordinates.add(new Coordinates(x, y));
			}
		} else {
			p = 2 * dx - dy;
			
			while (y != y1) {
				y += stepY;
				
				if (p < 0) {
					p += 2 * dx;
				} else {
					p +=  2 * (dx - dy);
					x += stepX;
				}
				
				coordinates.add(new Coordinates(x, y));
			}
		}
	}

	
	@Override
	public int getID() {
		return stateId;
	}
}


/*
 * Coordinates Class: Can not be used outside this file.
 */
class Coordinates {
	private final int x;
	private final int y;
	
	public Coordinates(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x; }
	public int getY() { return y;}
}
