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
import minigames.GameObject;
import resources.ResourceLoader;

public class Minigame5 extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	
	private final KeyboardController keyboard;
	
	private Image backgroundImage;
	private Animation bola1Image;
	private Animation monkeyImage;
	private Animation llamaImage;
	
	private ball bola1;
	private GameObject player;
	private GameObject disparo;
	
	private int spawnSpeed;
	private int speedDificulty;
	private int x,y;
	private int xball;
	private int elapsedTime;
	private double sy,sx;
	private boolean inicio,vueltax,vueltay;
	
	/*
	 * Constructors
	 */
	public Minigame5(final int stateId) {
		this.stateId = stateId;
		
		keyboard = new KeyboardController(640); 
		
		
		x = 10;
		y=480;
		elapsedTime = 0;
		spawnSpeed = 50;
		speedDificulty = 1;
		sy=0;
		sx=0;
		vueltax=false;
		vueltay=false;
		inicio=true;

	}
	

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image("res/images/nieve3.png");
		bola1Image = ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/bola-naranja.png", 64, 64, 100);
		monkeyImage = ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/protaderecha.png", 64, 64, 5);
		llamaImage = ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/llama.png", 64, 64, 5);
		
		player = new GameObject(monkeyImage, null, x, 480, 2f); // Set values as constants
		bola1 = new ball(bola1Image, null, 150, 0, 2f,0.3,0);

	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		backgroundImage.draw();
		bola1.render(g);
		
		player.render(g);
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if(vueltay == false && inicio == true) {
			bola1.updateYByIncrease((int)(bola1.getVy()*(delta/200.0f)));
			bola1.updateVy(bola1.getA()*delta/200.0f);
			if(bola1.getY() > 500)
			{
				vueltay=true;
			}
		}
		
		if(vueltay == true) {
			bola1.updateYByIncrease((int)(-bola1.getVy()*(delta/200.0f)));
			bola1.updateVy(-bola1.getA()*delta/200.0f);
			if(bola1.getY() < 0)
			{
				vueltay=false;
				bola1.setVy(0);
			}	
		}
		
		if(vueltax== false && inicio ==true)
		{
			bola1.updateXByIncrease((int)(bola1.getVx()*(2*delta)));
			if(bola1.getX() >960)
			{
				bola1.updateXByIncrease((int)(-2*sx));
				vueltax=true;
			}
		}
		if(vueltax==true)
		{
			bola1.updateXByIncrease((int)(-bola1.getVx()*(2*delta)));
			if(bola1.getX()< 0)
			{
				vueltax=false;
				bola1.updateXByIncrease((int)(-2*sx));
			}
		}
		
		
		
		player.updateX(x += keyboard.getXMovementPl1() * delta / 200f); // Set values as constants
		
		if (elapsedTime++ > spawnSpeed) {

			elapsedTime = 0;
		}

		
			
			if (player.getCollisionBox().intersects(bola1.getCollisionBox()) || gc.getHeight() < bola1.getY()) {
				
			}
	}
	
	
	/*
	 * Create Bananas
	 */
	private GameObject createball() {
		return new ball(bola1Image, null, bola1.getX(), bola1.getY(), 2f,0.2,0); // Set values as constants
		
	}
	private GameObject createshot() {
		return new GameObject(llamaImage, null, player.getX(), player.getY(), 2f);
	}

	/*
	 * Getters
	 */
	@Override
	public int getID() {
		return stateId;
	}

}


class ball extends GameObject{

	private double vx,vy;
	private double a;
	
	public ball(Animation anim, int[] startAnimIdxPtr, int x, int y, float scale,double vx,double vy) {
		super(anim, startAnimIdxPtr, x, y, scale);
		a=10;
		this.vx=vx;
		this.vy=vy;
		// TODO Auto-generated constructor stub
		
		
		
	}
	public double getVx() {return vx;}
	public void setVx(double vx) {this.vx=vx;}
	public double updateVx(double vx) {return this.vx+=vx;}
	
	public double getVy() {return vy;}
	public void setVy(double vy) {this.vy=vy;}
	public double updateVy(double vy) {return this.vy+=vy;}
	
	public void setA(double a) {this.a=a;}
	public double getA() {return a;}

}