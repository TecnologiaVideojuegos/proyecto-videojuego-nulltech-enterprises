package hud;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IBasicHudComponent {
	
	public void init(GameContainer gc);
	public void render(GameContainer gc, Graphics g);
	public void update();
}
