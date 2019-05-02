package util;

public class Coordinates {

	/*
	 * Attributes
	 */
	private int x, y;
	
	/*
	 * Constructors
	 */
	public Coordinates() {}
	
	public Coordinates(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	
	/*
	 * X
	 */
	public int getX() { return x; }
	public void setX(final int x) { this.x = x; }
	public void incrementX(final int increment) { x += increment; }
	
	/*
	 * Y
	 */
	public int getY() { return y; }
	public void setY(final int y) { this.y = y; }
	public void incrementY(final int increment) { y += increment; }
}

