package minigames;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class GameObject {

	/*
	 * Attributes
	 */
	private final Image image;
	private int x;
	private int y;
	private final float scale;
	private Shape collisionBox;
	private boolean deleted;
	
	/*
	 * Constructors
	 */
	public GameObject(final Image image, final int x, final int y, final float scale) {
		this.image = image;
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
			// g.draw(collisionBox); // DEBUG
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
	 * Image
	 */
	public Image getImage() { return image; }
	
	/*
	 * Update X and Y
	 */
	public void updateXByIncrease(final int oscilation) {
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
