package maps;

import org.newdawn.slick.tiled.TiledMap;

import constants.Constants;

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
	public TiledMap getMap() {
		return map;
	}

	/*
	 * Render
	 */
	public void renderLayersBeforePlayer(final int x, final int y) {
		map.render(x, y, map.getLayerIndex(Constants.MAP_LAYER_BELOW_PLAYER));
		map.render(x, y, map.getLayerIndex(Constants.MAP_LAYER_WORLD));
	}

	public void renderLayersAfterPlayer(final int x, final int y) {
		map.render(x, y, map.getLayerIndex(Constants.MAP_LAYER_ABOVE_PLAYER));
	}
}
