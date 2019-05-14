package minigames;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	/*
	 * Attributes
	 */
	private final Animation anim;
	private Shape collisionBox;
	
	private int currentAnimIdx;
	private int[] startAnimIdxPtr;
	
	private int x;
	private int y;
	private final float scale;
	
	
	/*
	 * Constructors
	 */
	public GameObject(final Animation anim, final int[] startAnimIdxPtr, final int x, final int y, final float scale) {
		this.anim = anim;
		this.startAnimIdxPtr = startAnimIdxPtr;
		this.currentAnimIdx = 0;
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.collisionBox = new Rectangle(x, y, anim.getWidth() * scale, anim.getHeight() * scale);
	}
	
	
	/*
	 * Render
	 */
	public void render(Graphics g) {
		anim.getImage(currentAnimIdx).draw(x, y, scale);
		g.draw(collisionBox); // DEBUG
	}
	
	
	/*
	 * Animation
	 */
	public Animation getAnimation() { return anim; }
	
	public void updateCurrentAnimation() {
		if (++currentAnimIdx == anim.getFrameCount()) {
			currentAnimIdx = 0;
		}
	}
	
	public void updateCurrentAnimation(final int pos) {
		if (pos < startAnimIdxPtr.length) {
			if (currentAnimIdx <= startAnimIdxPtr[pos] || currentAnimIdx <= startAnimIdxPtr[pos - 1]) {
				currentAnimIdx = startAnimIdxPtr[pos];
			} else {
				currentAnimIdx++;
			}
		}
	}

	
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
	 * Update X and Y
	 */
	public void updateX(final int newX) {
		x = newX;
		collisionBox.setX(x);
	}
	
	public void updateXByIncrease(final int oscilation) {
		x += oscilation;
		collisionBox.setX(x);
	}
	
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
