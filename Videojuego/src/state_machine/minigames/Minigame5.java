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
	
	private ArrayList<ball> bolalist;
	private GameObject player;
	private GameObject disparo;
	private ball bola_choque;
	
	
	private int spawnSpeed;
	private int speedDificulty;
	private int x,y;
	private int xball;
	private int elapsedTime;
	private double sy,sx;
	private boolean inicio;
	private boolean vueltax;
	private boolean vueltay;
	private boolean nueva_bola;
	
	
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
		inicio=true;
		vueltax=false;
		vueltay=false;
		nueva_bola=false;
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
		
		player = new GameObject(monkeyImage, x, 480, 2f); // Set values as constants
		disparo = new GameObject(llamaImage, -10, -10, 1.2f);;
		
		bolalist = new ArrayList<ball>();
		bolalist.add(createball(100,50,1.8f,0.25,false,false,500));
	}

	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		backgroundImage.draw();
		for (ball go : bolalist){
			go.render(g);
		}
		
		player.render(g);
		if (disparo != null) {
			disparo.render(g);
		}
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		if(keyboard.getPressedpl1(gc) == "Space")
		{
			disparo=createshot();
			
				
			}
			
		
		
		
		for (ball go : bolalist){
			movimiento_ball(go,delta);
			if (disparo.getCollisionBox().intersects(go.getCollisionBox())) {
				go.setScale(go.getScale()/2);
				nueva_bola=true;
				bola_choque=go;
				disparo.setY(-10);
				disparo.setX(-10);


			}
		}
			if(nueva_bola == true){
				bolalist.add(createball(bola_choque.getX(),bola_choque.getY(),bola_choque.getScale(),bola_choque.getVx(),bola_choque.getvueltax(),!bola_choque.getvueltay(),bola_choque.getymax() ));
				nueva_bola=false;
			}

		
		
		
		player.updateX(x += keyboard.getXMovementPl1() * delta / 200f); // Set values as constants
		
		if (elapsedTime++ > spawnSpeed) {

			elapsedTime = 0;
		}
		if (disparo != null) {
			disparo.updateYByIncrease(-25);
		}
		

	}
	
	private void movimiento_ball(ball bola1 ,int delta)
	{


		if(bola1.getvueltay() == false && inicio == true) {
			bola1.updateYByIncrease((int)(bola1.getVy()*(delta/200.0f)));
			bola1.updateVy(bola1.getA()*delta/200.0f);
			if(bola1.getY() > bola1.getymax()) //ymax=500
			{
				bola1.setvueltay(true);
			}
		}
		
		if(bola1.getvueltay() == true) {
			bola1.updateYByIncrease((int)(-bola1.getVy()*(delta/200.0f)));
			bola1.updateVy(-bola1.getA()*delta/200.0f);
			if(bola1.getY() < 50) //ymin=50
			{
				bola1.setvueltay(false);
				bola1.setVy(0);
			}	
		}
		
		if(bola1.getvueltax()== false && inicio ==true)
		{
			bola1.updateXByIncrease((int)(bola1.getVx()*(2*delta)));
			if(bola1.getX() >960)
			{

				bola1.setvueltaX(true);
			}
		}
		if(bola1.getvueltax()==true)
		{
			bola1.updateXByIncrease((int)(-bola1.getVx()*(2*delta)));
			if(bola1.getX()< 0)
			{
				bola1.setvueltaX(false);

			}
		}
	
		
	}
	
	/*
	 * Create Bananas
	 */
	private ball createball(int x0,int y0,float scale,double vx,boolean vueltax,boolean vueltay,int ymax) {
		return new ball(bola1Image, null, x0, y0, scale,vx,0,vueltax,vueltay,ymax); // Set values as constants
		
	}
	private GameObject createshot() {
		return new GameObject(llamaImage, player.getX()+32, player.getY()-32, 1.2f);
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
	private boolean vueltay,vueltax;
	private int ymax,xmax;
	
	public ball(Animation anim, int[] startAnimIdxPtr, int x, int y, float scale,double vx,double vy,boolean vueltax,boolean vueltay,int ymax) {
		super(anim, x, y, scale);
		a=10;
		this.vx=vx;
		this.vy=vy;
		this.vueltax=vueltax;
		this.vueltay=vueltay;
		this.ymax=ymax;
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
	
	public void setvueltay(boolean vueltay) {this.vueltay=vueltay;}
	public boolean getvueltay() {return vueltay;}
	
	public void setvueltaX(boolean vueltax) {this.vueltax=vueltax;}
	public boolean getvueltax() {return vueltax;}
	
	public int getymax() {return ymax;}
	public void setymax(int ymax) {this.ymax=ymax;}
	
	public int getxmax() {return xmax;}
	public void setyxmax(int xmax) {this.xmax=xmax;}
}