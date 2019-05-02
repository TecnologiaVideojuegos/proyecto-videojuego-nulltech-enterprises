package game;

import entities.Player;

public class GameState {

	private final GameMode gameMode;
	private final Player[] players;
	private int playerTurn;
	
	
	/*
	 * Constructors
	 */
	public GameState(final GameMode gameMode, final Player[] players) {
		this.gameMode = gameMode;
		this.players = players;
	}
	
	
	/*
	 * Points
	 */
	public void updatePuntuationToPlayer(final int playerIdx, final int points) {
		if(players[playerIdx].updatePuntuation(points) >= gameMode.getPointsToWin()) {
			playerWins(playerIdx);
		}
	}
	
	private void playerWins(final int playerIdx) {
		System.out.println("Player " + playerIdx + " wins!");
	}
	
	public int getPlayerTurn() { return playerTurn; }
	public Player getPlayerByTurn() { return players[playerTurn]; }
}
