package game;

public class GameMode {

	/*
	 * Attributes
	 */
	private final int pointsToWin;
	
	
	/*
	 * Constructors
	 */
	public GameMode(final int pointsToWin) {
		this.pointsToWin = pointsToWin;
	}
	
	
	/*
	 * Points to win
	 */
	public int getPointsToWin() {
		return pointsToWin;
	}
}
