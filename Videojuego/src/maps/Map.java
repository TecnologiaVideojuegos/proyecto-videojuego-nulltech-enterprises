package maps;

import org.newdawn.slick.tiled.TiledMap;

public class Map {

	/*
	 * Attributes
	 */
	private final TiledMap map;

	/*
	 * Constructors
	 */
	public Map(final TiledMap map) {
		this.map = map;
	}

	/*
	 * Getters
	 */
	public TiledMap getTiledMap() {
		return map;
	}

	/*
	 * Render
	 */
	public void render(final int x, final int y) {
		map.render(x, y);
	}
}