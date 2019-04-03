package main;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import constants.Constants;
import maps.Map;
import maps.MapManager;

public class MainManager extends BasicGame {
	
	/*
	 * Attributes
	 */
	private MapManager mapManager;
	private int x = 0;
	private int y = 0;
	
	/*
	 * Constructors
	 */
	public MainManager(String title) {
		super(title);
	}

	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		renderGraphics(g);
		
		mapManager.getSelectedMap().renderLayersBeforePlayer(x, y);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		mapManager = new MapManager();
		mapManager.setAddMapToArray(new Map(new TiledMap(Constants.PATH_MAP_TEST)));
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub

	}
	
	private void renderGraphics(Graphics g) {
		g.scale(Constants.GRAPHICS_DEFAULT_SCALE, Constants.GRAPHICS_DEFAULT_SCALE);
	}

	/*
	 * Main
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
			AppGameContainer app = new AppGameContainer(new MainManager("test"));
			app.setDisplayMode(600, 400, false);
			app.setTargetFrameRate(60);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
