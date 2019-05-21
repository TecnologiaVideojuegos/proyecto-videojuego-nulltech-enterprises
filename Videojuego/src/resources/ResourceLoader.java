package resources;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class ResourceLoader {


	/*
	 * Dice
	 */
	public Image[] loadDiceImages() {
		try {
			Image[] images = new Image[6];
			
			images[0] = new Image("res/images/dice/Dado1.png");
			images[1] = new Image("res/images/dice/Dado2.png");
			images[2] = new Image("res/images/dice/Dado3.png");
			images[3] = new Image("res/images/dice/Dado4.png");
			images[4] = new Image("res/images/dice/Dado5.png");
			images[5] = new Image("res/images/dice/Dado6.png");
			
			return images;
		} catch (SlickException e) {
			System.out.println("Error loading Dice images. " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Loaders
	 */
	public static Image loadImageFromUrl(final String path) {
		try {
			return new Image(path);
		} catch (SlickException e) {
			System.out.println("Error loading Image: " + path);
			e.printStackTrace();
		}
		return null;
	}
	
	public static Animation animationfromimage(final String path,final int sizex,final int sizey,final int ms)
	{
		return loadAnimatioFromSpriteSheet(loadSpriteSheetfromurl(path,sizex,sizey),ms);
	}	
	
	public static Animation animationfromimage(final String path,final int sizex,final int sizey)
	{
		return loadAnimatioFromSpriteSheet(loadSpriteSheetfromurl(path,sizex,sizey),100);
	}
	
	public static Animation loadAnimatioFromSpriteSheet(final SpriteSheet spriteSheet,int duration)
	{
		return new Animation(spriteSheet,duration);
	}
	
	public static SpriteSheet loadSpriteSheetfromurl(final String path,final int sizex,final int sizey)
	{
		try {
			return new SpriteSheet(path,sizex,sizey);
		} catch (SlickException e) {
			System.out.println("Error loading image. " + e);
			e.printStackTrace();
		}
		return null;
		
	}
}