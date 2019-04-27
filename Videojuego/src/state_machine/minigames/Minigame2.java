package state_machine.minigames;

import java.lang.Math;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


import controllers.KeyboardController;
import minigames.GameObject;
import resources.ResourceLoader;

public class Minigame2 extends BasicGameState{

	private int number_ball=4;
	
	private final int stateId;
	
	private final KeyboardController keyboard;
	private final ResourceLoader resLoader;
	
	private Image backgroundImage;
	private Image ballImage;
	private Image zonethunderImage;
	private Image shockImage;
	private Image playerImage;

	private GameObject[] ballarray=new GameObject[number_ball];
	private GameObject	player;
	private GameObject zonethunder;
	
	private int speed_ball;
	private int stop_time;
	private int speed_time_delay;
	private int stop_time_delay;
	private int puntuacion=0;
	private int espera=0;
	ArrayList<int[]> lista = new ArrayList<>();
	private int[] coord= new int[2];
	/*
	 * Constructors
	 */
	public Minigame2(final int stateId, final ResourceLoader resLoader) {
		this.stateId = stateId;
		this.resLoader = resLoader;
		
		keyboard = new KeyboardController(640); 
		speed_ball=0;
		stop_time=0;
		speed_time_delay=100;
		stop_time_delay=350;
		Bresenham(200,120,500,10);
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = new Image("res/images/Escenariolava.png");
		ballImage= new Image("res/images/bolacanon.png");
		zonethunderImage= new Image("res/images/zonaelectrica.png");
		shockImage= new Image("res/images/chispa.png");
		playerImage=new Image("res/images/protaderecha.png");

		player=new GameObject(playerImage,400,100,2.0f);
		zonethunder=new GameObject(zonethunderImage,64,64,2.0f);
		
		for(int i=0;i<number_ball;i++)
		{
			ballarray[i]=new GameObject(ballImage,0,0,2.0f);
		}
		
}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		backgroundImage.draw();
		player.render(g);
		zonethunder.render(g);
		shockImage.draw();
		for(int i=0;i<number_ball;i++)
		{
			ballarray[i].render(g);
			
		}
		for(int i=0;i<lista.size();i++)
		{
			g.drawString(String.valueOf(lista.get(i)[0]),200+10*i,200+10*i);
		}
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if(speed_ball++ < speed_time_delay){
			update_move();
			
		}
		if(stop_time++>stop_time_delay)
		{
			speed_ball=0;
			stop_time=0;
		}
		

	}

	@Override
	public int getID() {
		return stateId;
	}
	
	private void update_move()
	{
		double posx=0;
		double posy;
		int theta;
		int r=256;
		for(int i=0;i<number_ball;i++)
		{
			theta=ThreadLocalRandom.current().nextInt(0,360);
			posx=r*Math.cos(theta)+460;
			posy=r*Math.sin(theta)+220;
			
			ballarray[i].setX((int)posx);
			ballarray[i].setY((int)posy);
		}
	}
	
	private void Bresenham(int x0, int y0, int x1, int y1) { 
		  int x, y, dx, dy, p, incE, incNE, stepx, stepy;
		  dx = (x1 - x0);
		  dy = (y1 - y0);

		 /* determinar que punto usar para empezar, cual para terminar */
		  if (dy < 0) { 
		    dy = -dy; 
		    stepy = -1; 
		  } 
		  else {
		    stepy = 1;
		  }

		  if (dx < 0) {  
		    dx = -dx;  
		    stepx = -1; 
		  } 
		  else {
		    stepx = 1;
		  }

		  x = x0;
		  y = y0;

		 /* se cicla hasta llegar al extremo de la línea */
		  if(dx>dy){
		    p = 2*dy - dx;
		    incE = 2*dy;
		    incNE = 2*(dy-dx);
		    while (x != x1){
		      x = x + stepx;
		      
		      if (p < 0){
		        p = p + incE;
		      }
		      else {
		        y = y + stepy;
		        p = p + incNE;
		      }
		      coord[0]=x;
		      coord[1]=y;
		      lista.add(coord);
		    }
		  }
		  else{
		    p = 2*dx - dy;
		    incE = 2*dx;
		    incNE = 2*(dx-dy);
		    while (y != y1){
		      y = y + stepy;
		      
		      if (p < 0){
		        p = p + incE;
		      }
		      else {
		        x = x + stepx;
		        
		        p = p + incNE;
		      }
		      coord[0]=x;
		      coord[1]=y;
		      lista.add(coord);
		    }
		  }
		 }


}
