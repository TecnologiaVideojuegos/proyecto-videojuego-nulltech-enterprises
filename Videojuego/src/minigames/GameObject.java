package minigames;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	/*
	 * Attributes
	 */
	private Animation anim;
	private Shape collisionBox;
	
	
	private int x;
	private int y;
	private final float scale;
	private String direction;
	private Animation movimiento;
	private int bframe;
	private int eframe;
	/*
	 * Constructors
	 */
	public GameObject(final Animation anim, final int x, final int y, final float scale) {
		this.anim = anim;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.collisionBox = new Rectangle(x, y, anim.getWidth() * scale, anim.getHeight() * scale);
		movimiento=new Animation();
		movimiento.addFrame(anim.getImage(0).getScaledCopy(scale), 100);
	}
	
	
	/*
	 * Render
	 */
	public void render(Graphics g) {

			movimiento.draw(x,y);


		g.draw(collisionBox); // DEBUG
	}
	
	
	/*
	 * Animation
	 */
	public Animation getAnimation() { return anim; }
	public void changeAnimation(Animation anim) {this.anim=anim;}
	
	public void updateCurrentAnimation(final int bframe,final int eframe,final float scale) {
		if (this.bframe!=bframe || this.eframe !=eframe)
		{
			this.bframe=bframe;
			this.eframe=eframe;
			movimiento=new Animation();
			for(int i=bframe;i<=eframe;i++)
			{
				movimiento.addFrame(anim.getImage(i).getScaledCopy(scale),100);
			}
		}
	}

	public String getdirection() {return direction;}
	public void setdirection(String direction) {this.direction=direction;}
	
	/*
	 * X
	 */
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	
	public void updateX(final int newX) {
		x = newX;
		collisionBox.setX(x);
	}
	
	public void updateXByIncrease(final int increase) {
		x += increase;
		collisionBox.setX(x);
	}

	/*
	 * Y
	 */
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }

	public void updateY(final int newY) {
		y = newY;
		collisionBox.setY(y);
	}
	
	public void updateYByIncrease(final int increase) {
		y += increase;
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
	
}