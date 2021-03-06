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
	private float scale;
	private String direction;
	private Animation movimiento;
	private int bframe;
	private int eframe;
	public static int paso;
	private boolean visible;
	private int yMovement;
	private int xMovement;
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
		this.visible=true;
	}
	
	
	/*
	 * Render
	 */
	public void render(Graphics g) {
		if(visible) {
			movimiento.draw(x,y);
		}

		//g.draw(collisionBox); // DEBUG
//		anim.getImage(currentAnimIdx).draw(x, y, scale);
//		g.draw(collisionBox); // DEBUG
	}
	
	
	/*
	 * Animation
	 */
	public Animation getAnimation() { return movimiento; }
	public void changeAnimation(Animation anim,float scale) {
		this.anim=anim;
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
	
	
	public void updateXByXMovement() {
		x += xMovement;
		collisionBox.setX(x);
	}
	
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
	

	public void updateYByYMovement() {
		y += yMovement;
		collisionBox.setY(y);
	}
	
	public int getYMovement() { return yMovement; }
	public void setYMovement(final int yMovement) { this.yMovement = yMovement;}
	
	
	
	
	/*
	 * CollisionBox
	 */
	public Shape getCollisionBox() { return collisionBox; }
	public void setCollisionBox(Shape collisionBox) { this.collisionBox = collisionBox; }

	
	/*
	 * Scale
	 */
	
	
	
	public void setVisible(boolean visible) {this.visible=visible;}
	public boolean getVisible() {return this.visible;}
	public void setScale(float scale) {
		this.scale=scale;		
		movimiento=new Animation();
		movimiento.addFrame(anim.getImage(0).getScaledCopy(scale), 100);
	}
	public float getScale() { return scale; }
}