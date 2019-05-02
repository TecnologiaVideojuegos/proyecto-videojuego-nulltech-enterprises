package entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;

import util.Coordinates;
import util.MapLocation;

public class Player {
	
	/*
	 * Attributes
	 */
	private final String name;
	private int puntuation;
	private Coordinates coordinates;
	private MapLocation mapLocation;
	
	private Animation character;
	private int scale;
	

	/*
	 * Constructors
	 */
	public Player(final String name) {
		this.name = name;
		mapLocation = new MapLocation();
	}
	
	public Player(final String name, final Animation character) {
		this.name = name;
		this.character = character;
		mapLocation = new MapLocation();
	}
	
	
	/*
	 * Render
	 */
	public void render(Graphics g, final int x, final int y) {
		if(character != null) {
			character.getCurrentFrame().draw(x, y, 0.25f);
		}
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
	
	/*
	 * Location
	 */
	public MapLocation getMapLocation() { return mapLocation; }
	public void setMapLocation(final MapLocation mapLocation) { this.mapLocation = mapLocation; } 

}


