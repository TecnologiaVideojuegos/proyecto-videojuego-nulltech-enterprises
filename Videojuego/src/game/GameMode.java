package game;

public class GameMode {

	/*
	 * Attributes
	 */
	private final int pointsToWin;
	private final int movementsWhenWin = 2;
	private final int movementsWhenLose = 1;
	
	
	/*
	 * Constructors
	 */
	public GameMode(final int pointsToWin) {
		this.pointsToWin = pointsToWin;
	}
	
	
	/*
	 * Points to win
	 */
	public int getPointsToWin() { return pointsToWin; }
	
	public int getMovementsWhenWin() { return movementsWhenWin; }
	public int getMovementsWhenLose() { return movementsWhenLose; }
}
