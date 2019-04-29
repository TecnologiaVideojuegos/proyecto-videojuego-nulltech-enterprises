package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import util.Coordinates;

public class Player {
	
	/*
	 * Attributes
	 */
	private final String name;
	private int puntuation;
	private Coordinates coordinates;
	
	private final Animation character;
	private final int scale;
	

	/*
	 * Constructors
	 */
	public Player(final String name, final Animation character, final int scale) {
		this.name = name;
		this.character = character;
		this.scale = scale;
	}
	
	
	/*
	 * Render
	 */
	public void render(Graphics g) {
		character.getCurrentFrame().draw(coordinates.getX(), coordinates.getY(), scale);
	}
	
	public void update() {
		
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

}
