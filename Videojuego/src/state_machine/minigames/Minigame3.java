package state_machine.minigames;


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

public class Minigame3 extends BasicGameState{

	private final int stateId;
	
	private final KeyboardController keyboard;
	
	private Image backgroundImage;
	private Animation block_anim;
	private Animation playerImage;
	private Animation yetiImage;
	private Animation pezImage;
	private Animation penguinImage;
	private Animation tecla1Image;
	
	
	private GameObject player;
	private GameObject iceblock;	
	private GameObject penguin;
	private GameObject yeti;
	private GameObject pez;
	private GameObject tecla1;
	

	private int x;
	private int y;
	private boolean vuelta=false;
	private boolean inicio=true;
	private boolean pulsado=false;
	
	public Minigame3(final int stateId) {
		this.stateId = stateId;
		keyboard = new KeyboardController(640);
		x=160;
		y=100;

	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		backgroundImage=new Image("res/images/minijuegonieve.png");
		playerImage=ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/protaderecha.png",64,64,100);
		penguinImage=ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/penguin.png",64,64,100);
		yetiImage=ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/yeti_nieve1.png",64,64,100);
		tecla1Image=ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/teclaa1.png",64,64,100);
		
		block_anim=ResourceLoader.loadAnimationFromSpriteSheetUrl("res/images/bloqueselecc1-sheet.png",64,64,100);
		player=new GameObject(playerImage,null,70,90,2.0f);
		iceblock=new GameObject(block_anim,null,400,100,2.0f);
		penguin = new GameObject(penguinImage,null,50,100,0.7f);
		tecla1 = new GameObject(tecla1Image,null,50,0,0.7f);
		//yeti= new GameObject(yetiImage,null,400,100,0.5f);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		backgroundImage.draw();
		player.render(g);
		iceblock.render(g);
		penguin.render(g);
		if(tecla1.getX()< 400)
		{
			tecla1.render(g);
		}
		//yeti.render(g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		if((keyboard.getPressedpl1(gc)=="One" || keyboard.getPressedpl1(gc)=="Two" || keyboard.getPressedpl1(gc)=="Three")&& pulsado==false){
			
			switch(keyboard.lastpressedp1)
			{
			case("One"):
				
				
			case("Two"):
				
				
			case("Three"):
				
				
			}
			pulsado=true;
			inicio=true;
		}

		if(vuelta == false && inicio == true) {
			iceblock.updateX(x += 100 * delta / 200f);
			if(x > 700)
			{
				vuelta=true;
				switch(keyboard.lastpressedp1)
				{
				case("One"):
					penguin.setX(800);
					penguin.setY(50);
					
				case("Two"):
					
					
				case("Three"):
						
				}
			}
		}
		if(vuelta == true) {
			iceblock.updateX(x -= 100 * delta / 200f);
			if(x < 160)
			{
				vuelta=false;
				inicio=false;
				pulsado=false;
			}
		}
		
		
		
		
		tecla1.setX(penguin.getX());
		tecla1.setY(penguin.getY()-64);
	}
	

	@Override
	public int getID() {
		return stateId;
	}

	
		
}
