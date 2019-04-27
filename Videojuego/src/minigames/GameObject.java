package minigames;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	/*
	 * Attributes
	 */
	private final Image image;
	private final Animation anim;
	private int animation_index;
	private int[] posarray;
	private int x;
	private int y;
	private final float scale;
	private Shape collisionBox;
	private boolean deleted;
	private String direction;
	
	
	/*
	 * Constructors
	 */
	public GameObject(final Image image, final int x, final int y, final float scale) {
		this.image = image;
		this.anim=null;
		this.animation_index=0;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.collisionBox = new Rectangle(x, y, image.getWidth() * scale, image.getHeight() * scale);
		
	}
	public GameObject(final Animation anim,final int x,final int y,final float scale)
	{
		this.image=null;
		this.anim = anim;
		this.animation_index=0;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.collisionBox = new Rectangle(x, y, image.getWidth() * scale, image.getHeight() * scale);
		
	}
	public GameObject(final Animation anim,final int[] posarray,final int x,final int y,final float scale)
	{
		this.image=null;
		this.anim = anim;
		this.posarray= posarray;
		this.animation_index=0;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.collisionBox = new Rectangle(x, y, image.getWidth() * scale, image.getHeight() * scale);
		
	}
	
	/*
	 * Rener
	 */
	public void render(Graphics g) {
		if (!deleted) {
			image.draw(x, y, scale);
			//
			//anim.getImage(animation_index);
			// g.draw(collisionBox); // DEBUG
		}
	}

	public String getdirection() {return direction;}
	public void setdirection(String direction) {this.direction=direction;}
	/*
	 * X
	 */
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }

	/*
	 * Y
	 */
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }

	/*
	 * Image
	 */
	public Image getImage() { return image; }
	
	public void update_current_animation()
	{
		if(animation_index++==anim.getFrameCount()) {
			animation_index=0;
		}
	}
	public void update_current_animation(final int pos)
	{
		int inicio=posarray[pos];
		int fin= posarray[pos + 1];
		if(animation_index <= inicio || animation_index>= fin ) {animation_index=inicio;}
		else {animation_index++;}
	}
	
	/*
	 * Update X and Y
	 */
	public void updateXByIncrease(final int oscilation) {
		System.out.println(oscilation + " " + x);
		x += oscilation;
		collisionBox.setX(x);
	}
	
	public void updateYByIncrease(final int oscilation) {
		y += oscilation;
		collisionBox.setY(y);
	}
	
	public void updateX(final int newX) {
		x = newX;
		collisionBox.setX(x);
	}
	
	public void updateY(final int newY) {
		y = newY;
		collisionBox.setY(y);
	}

	/*
	 * CollisionBox
	 */
	public Shape getCollisionBox() { return collisionBox; }
	public void setCollisionBox(Shape collisionBox) { this.collisionBox = collisionBox; }

	/*
	 * Scale
	 */
	public float getScale() { return scale; }

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
