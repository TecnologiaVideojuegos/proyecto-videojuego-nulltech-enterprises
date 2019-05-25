package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import controllers.KeyboardController;
import game.GameState;
import main.MainManager;
import maps.Map;
import state_machine.MapState;
import util.Coordinates;
import util.MapLocation;

public class Player {
	
	/*
	 * Attributes
	 */
	private MainManager mainManager;
	
	private final String name;
	private int puntuation;
	private int availableMovements = 1;
	
	private Coordinates coordinates;
	private MapLocation mapLocation;
	
	private GameState gameState;
	
	private final KeyboardController keyboard;
	
	private Animation character;
	private Animation movimiento;
	private int bframe;
	private int eframe;
	private float scale;
	
	private final PlayerGameState state;
	private int inicio_animacion=0;
	private int ianimacion=0;
	
	private int squareId,nextsquareId,id=0;
	private int x_act,y_act,x_nxt,y_nxt,posx,posy;
	private Map map,mapnxt;
	private boolean moving;
	
	/*
	 * Constructors
	 */
	public Player(final String name) {
		this.name = name;
		mapLocation = new MapLocation();
		keyboard = new KeyboardController(600);
		state = new PlayerGameState();
	}
	
	public Player(final String name, final Animation character,float scale) {
		this.name = name;
		this.character = character;
		this.scale=scale;
		mapLocation = new MapLocation();
		keyboard = new KeyboardController(600);
		state = new PlayerGameState();
		movimiento=new Animation();
		movimiento.addFrame(character.getImage(21).getScaledCopy(scale), 100);
	}
	
	public Animation getAnimation() { return movimiento; }
	
	public void changeAnimation(Animation anim,float scale) {
		this.character=anim;
		movimiento=new Animation();
		movimiento.addFrame(anim.getImage(0).getScaledCopy(scale), 100);
	}
	
	
	public void updateCurrentAnimation(final int bframe,final int eframe,final float scale) {
		if (this.bframe!=bframe || this.eframe !=eframe)
		{
			this.bframe=bframe;
			this.eframe=eframe;
			movimiento=new Animation();
			for(int i=bframe;i<=eframe;i++)
			{
				movimiento.addFrame(this.character.getImage(i).getScaledCopy(scale),100);
			}
		}
	}
	
	/*
	 * Render
	 */
	public void render(Graphics g, final int x, final int y) {
		if(movimiento != null) {
			if(moving == true)
			{
				movimiento.draw(posx, y);
			}
			else
			{
				movimiento.draw(x, y);
				posx=x;
				posy=y;
			}
		}
	}
	
	public void update(final int mapCount, final int delta) {
		if (!state.inputDisabled) {
			/** REMAKE THIS PART **/
			if (availableMovements > 0 && keyboard.getXMovementPl1() < 0 && inicio_animacion==0) {
				inicio_animacion=1;
				if((nextsquareId=mapLocation.getSquareId()-1)<0) {
					if((id=mapLocation.getMapId()-1)<0) {id=8;}
					nextsquareId=2;
				}
				else{
					id=mapLocation.getMapId();
				}

				availableMovements--;
				state.inputDisabled = true;
				updateCurrentAnimation(25, 28, 2f);
				
			} else if (availableMovements > 0 && keyboard.getXMovementPl1() > 0 && inicio_animacion==0) {
				inicio_animacion=2;
				if((nextsquareId=mapLocation.getSquareId()+1)>2) {
					if((id=mapLocation.getMapId()+1)>8) {id=0;}
					nextsquareId=0;	
				}
				else{
					id=mapLocation.getMapId();
				}
				
				
				availableMovements--;
				state.inputDisabled = true;
				updateCurrentAnimation(15, 18, 2f);
				
			} else if (keyboard.getActionButtonPl1() > 0 && inicio_animacion==0) {
				gameState.setFinishingTurn(true);
			}
		} else if (state.inputDisabled && state.elapsedTime < 2000) {
			state.elapsedTime += delta;
		} else if (state.inputDisabled ) {
			state.elapsedTime = 0;
			state.inputDisabled = false;
		}
		
		mapnxt=MapState.maps[id];
		map=MapState.maps[mapLocation.getSquareId()];
		x_act=map.getTiledMap().getObjectX(0, mapLocation.getSquareId());
		y_act=map.getTiledMap().getObjectY(0, mapLocation.getSquareId());
		x_nxt=mapnxt.getTiledMap().getObjectX(0, nextsquareId);
		y_nxt=mapnxt.getTiledMap().getObjectY(0, nextsquareId);
		
		
		if(inicio_animacion == 1)
		{
			if (nextsquareId == 2){
				updateXByIncrease(-5);
			}
			else {
				updateXByIncrease((x_nxt-x_act)/100);
			}
			moving=true;
			if (ianimacion++ > 150) {
				mapLocation.updateLocation(-1, mapCount);
				moving=false;
				movimiento.addFrame(character.getImage(21).getScaledCopy(scale),100);
				getAnimation().stopAt(4);
				eframe=6;
				ianimacion = 0;
				inicio_animacion=0;
				
			}
		}
		if(inicio_animacion == 2)
		{
			if(nextsquareId == 0){
				updateXByIncrease(5);
			}
			else {
				updateXByIncrease((x_nxt-x_act)/100);
			}
			moving=true;
			if (ianimacion++ > 150) {
				mapLocation.updateLocation(1, mapCount);
				moving=false;
				movimiento.addFrame(character.getImage(21).getScaledCopy(scale),100);
				getAnimation().stopAt(4);
				eframe=6;
				ianimacion = 0;
				inicio_animacion=0;
			}
		}

	}
	
	
	/*
	 * Puntuation
	 */
	public int getPuntuation() { return puntuation; }
	
	public int updatePuntuation(final int points) {
		return puntuation += points;
	}
	
	
	/*
	 * Name
	 */
	public String getName() { return name; }
	
	/*
	 * Location
	 */
	public MapLocation getMapLocation() { return mapLocation; }
	public void setMapLocation(final MapLocation mapLocation) { this.mapLocation = mapLocation; } 
	
	public void setGameState (final GameState gameState) { this.gameState = gameState; }
	
	public int getAvailableMovements() { return availableMovements; }
	public void setAvailableMovements(final int availableMovements) { this.availableMovements = availableMovements; }
	public void setScale(float scale) {
		this.scale=scale;		
		movimiento=new Animation();
		movimiento.addFrame(character.getImage(0).getScaledCopy(scale), 100);
	}
	public float getScale() { return scale; }
	public void updateXByIncrease(final int increase) {
		posx += increase;
	}
	public void updateY(final int increase) {
		posy += increase;
	}
	
	
	
	
}

class PlayerGameState {
	boolean inputDisabled;
	int elapsedTime;
}
