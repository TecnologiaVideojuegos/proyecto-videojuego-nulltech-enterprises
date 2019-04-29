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
import resources.Coordinates;

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
	private ArrayList<GameObject> zonethunder;

	
	private final int numberBalls;

	private int speedBall;
	private int stopTime;
	private int speedTimeDelay;
	private int stopTimeDelay;
	private int puntuacion;
	private int espera;

	
	private ArrayList<Coordinates> coordinates = new ArrayList<>();
	
	private ArrayList<ArrayList<Coordinates>> rectas_adecuadas;
	public enum estados
	{
	    Movimiento,Espera, Calculo
	}
	private estados estado_juego=estados.Movimiento;
	private int x;
	private int y;

	
	/*
	 * Constructors
	 */
	public Minigame2(final int stateId, final ResourceLoader resLoader) {
		this.stateId = stateId;
		this.resLoader = resLoader;
		
		keyboard = new KeyboardController(640);
		numberBalls = 4;
		ballarray = new GameObject[numberBalls];
		zonethunder=new ArrayList<GameObject>();
		speedTimeDelay = 100;
		stopTimeDelay = 350;
		x=400;
		y=100;
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_BACKGROUND);
		ballImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_BALL);
		thunderZoneImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_THUNDER_ZONE);
		shockImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_SHOCK);
		playerImage = resLoader.loadImageFromUrl(Constants.PATH_MINIGAME2_PLAYER);

		
		player = new GameObject(playerImage, 400, 100, 2.0f); 			// Export values to constants

		
		for(int i = 0 ; i < numberBalls; i++) {
			ballarray[i] = new GameObject(ballImage, 0, 0, 2.0f); 		// Export values to constants
		}


	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		backgroundImage.draw();
		player.render(g);
	
		shockImage.draw();

		for(GameObject go : ballarray) {
			go.render(g);
		}
		for(GameObject go: zonethunder)
		{
			go.render(g);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		player.updateX(x += keyboard.getXMovement() * delta / 200f);
		player.updateY(y += keyboard.getYMovement() * delta / 200f);
		if(speedBall++ < speedTimeDelay){
			updateMove();

		}

		
		else {
			if (estado_juego ==estados.Movimiento)
			{
				estado_juego=estados.Calculo;
			}
			if (estado_juego==estados.Calculo){
				for(int i=1;i<numberBalls;i++)
				{
					//Tomo la posicion de una bola arbitraria
					int posx0=ballarray[i].getX();
					int posy0=ballarray[i].getY();
					for(int j=0;j<numberBalls-1;j++)
					{
						//Se calculan los puntos entre bolas
						if(posx0 != ballarray[j].getX() && posy0 != ballarray[j].getY()) {
							coordenadas_recta_disparo(posx0,posy0,ballarray[j].getX(),ballarray[j].getY());
							//Se crean objetos en los puntos calculados
							for(int k=0;k<coordinates.size();k++)
							{
								zonethunder.add(new GameObject(thunderZoneImage, coordinates.get(k).getX(), coordinates.get(k).getY(), 1.0f));
							}
							coordinates.clear();
						}
						
					}
				
				}
				estado_juego=estados.Espera;
			}
			if(stopTime++ > stopTimeDelay){
				speedBall = 0;
				stopTime = 0;
				estado_juego=estados.Movimiento;
				zonethunder.clear();
			}
			
		}
	}
	private void updateMove() {
		double posX, posY;
		double theta=0;
		int r = 256;

		for(int i=0;i<numberBalls;i++) {

			//Evito colisiones entre bolas
			theta=ThreadLocalRandom.current().nextDouble(i*2*Math.PI/numberBalls,(i+1)*2*Math.PI/numberBalls);

			posX = r * Math.cos(theta) + 460;
			posY = r * Math.sin(theta) + 220;
			
			
			ballarray[i].setX((int)posX);
			ballarray[i].setY((int)posY);			
		}

	}
	private void coordenadas_recta_disparo(int x0, int y0 , int x1 , int y1)
	{
		int pxmedio=(x1+x0)/2;
		int pymedio=(y1+y0)/2;
		int n=0;
		
		//Va calculando el punto medio entre los puntos medios hasta que la distancia es menor que el tamaño del sprite
		//para que no se solapen los de la misma recta.
		while( (Math.sqrt( Math.pow(pxmedio-x0 ,2) + Math.pow(pymedio-y0 ,2) ))>64 )
		{
			n++; //numero particiones
			x1=pxmedio;
			y1=pymedio;
			pxmedio=Math.abs(x1+x0)/2;
			pymedio=Math.abs(y1+y0)/2;
		}
		n=n*2;
		while(n>0)
		{
			//Una vez calculado la cantidad de divisiones posibles debido a la distancia entre bolas
			//se añaden n puntos de la distancia minima posible.
			coordinates.add(new Coordinates((int)pxmedio*(n), (int)pymedio*(n)));
			n--;
		}
	}

	
	@Override
	public int getID() {
		return stateId;
	}
}



