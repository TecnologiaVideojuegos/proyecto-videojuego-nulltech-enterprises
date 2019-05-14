package state_machine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import main.MainManager;

public class EndGameState extends BasicGameState {
	
	/*
	 * Attributes
	 */
	private final int stateId;
	private final MainManager mainManager;
	
	
	/*
	 * Constructors
	 */
	public EndGameState(final int stateId, final MainManager mainManager) {
		this.stateId = stateId;
		this.mainManager = mainManager;
	}

	
	/*
	 * Init
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	
	/*
	 * Render
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if(mainManager != null && mainManager.getPlayers() != null && mainManager.getPlayers()[0] != null && mainManager.getPlayers()[1] != null) {
			// Player 1
			g.drawString(mainManager.getPlayers()[0].getName(), 100, 100);
			g.drawString(String.valueOf(mainManager.getPlayers()[0].getPuntuation()), 100, 200);
			
			// Player 2
			g.drawString(mainManager.getPlayers()[1].getName(), 300, 100);
			g.drawString(String.valueOf(mainManager.getPlayers()[1].getPuntuation()), 300, 200);
		}
	}

	
	/*
	 * Update
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
	}

	@Override
	public int getID() {
		return stateId;
	}
	
}
