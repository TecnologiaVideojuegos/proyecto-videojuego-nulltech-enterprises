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

import controllers.KeyboardController;
import minigames.GameObject;
import resources.ResourceLoader;

public class Minigame1 extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	
	private final KeyboardController keyboard;
	
	private Image backgroundImage;
	private Animation keyimage;
	private Animation arrowimageright;
	private Animation arrowimageleft;
	private Animation arrowimageup;
	private Animation arrowimagedown;


	private ArrayList<GameObject> arrayArrow;
	private GameObject keys;
	
	private int elapsedTime;
	private int spawnSpeed;
	private int speedDificulty;
	private int puntuacion=0;
	
	
	/*
	 * Constructors
	 */
	public Minigame1(final int stateId) {
		this.stateId = stateId;

		
		keyboard = new KeyboardController(640); 
		arrayArrow = new ArrayList<GameObject>();
		elapsedTime=0;
		spawnSpeed=20;
		speedDificulty=3;


	}
	

	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage = ResourceLoader.loadImageFromUrl("res/images/maxresdefault.png");
		keyimage = ResourceLoader.animationfromimage("res/images/teclas.png",64,64);
		arrowimagedown = ResourceLoader.animationfromimage("res/images/flechaabajo.png",64,64);
		arrowimageup= ResourceLoader.animationfromimage("res/images/flechaarriba.png",64,64);
		arrowimageright= ResourceLoader.animationfromimage("res/images/flechaderecha.png",64,64);
		arrowimageleft= ResourceLoader.animationfromimage("res/images/flechaizquierda.png",64,64);
		keys=new GameObject(keyimage,null,64,0,1.0f);
		
}
	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		backgroundImage.draw();
		keys.render(g);
		for (GameObject go : arrayArrow) {go.render(g);}
		g.drawString(String.valueOf(puntuacion),200,200);
		
	}

	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		
		
		if (elapsedTime++ > spawnSpeed) {
			arrayArrow.add(createArrow());
			elapsedTime = 0;
		}
		
		for (int i=0;i<arrayArrow.size();i++) {
			arrayArrow.get(i).updateYByIncrease(-speedDificulty);
		}
		
		
		if(arrayArrow.size()>1)
		{
			scoreColission(arrayArrow,gc);
		
			if(arrayArrow.get(0).getY() <= 0)
			{
				arrayArrow.remove(0);
			}
		}
}
		
	
	
	private void scoreColission(ArrayList<GameObject> arrayarrow,GameContainer gc){
						
		GameObject firstelement=arrayarrow.get(0);
		if(keyboard.getPressedpl1(gc)!= ""){
			if (keyboard.lastpressedp1==firstelement.getdirection()){
				if(arrayarrow.get(0).getCollisionBox().intersects(keys.getCollisionBox())){
					puntuacion+=20;
				 	}
				else{
					puntuacion-=20;
				}
				arrayarrow.remove(0);
			 }
			else
			{
				puntuacion-=20;
				arrayarrow.remove(0);
			}

	}
}
	

	
	/*
	 * Create Bananas
	 */
	private GameObject createArrow() {
		GameObject arr1; 
		
		int arrowcolor=ThreadLocalRandom.current().nextInt(0, 4);
		spawnSpeed=ThreadLocalRandom.current().nextInt(20, 60);
		int posx=0;
		String direction="";
		
		Animation arrowaux = null;
		switch(arrowcolor) {
		
		case 0:
			arrowaux=arrowimagedown;
			direction="Down";
			posx=64;
			break;
		case 1:
			arrowaux=arrowimageup;
			direction="Up";
			posx=128;
			break;
		case 2:
			arrowaux=arrowimageright;
			direction="Right";
			posx=192;
			break;
		case 3:
			arrowaux=arrowimageleft;
			direction="Left";
			posx=256;
			break;
		}
		
		arr1 = new GameObject(arrowaux,null, posx, 512, 1.0f); // Set values as constants
		arr1.setdirection(direction);
		return arr1;
		
	}
	
	

	/*
	 * Getters
	 */
	@Override
	public int getID() {
		return stateId;
	}

}
