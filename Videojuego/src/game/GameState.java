package game;

import entities.Player;

public class GameState {

	private final GameMode gameMode;
	private final Player[] players;
	private int playerTurn;
	private boolean finishingTurn;
	
	
	/*
	 * Constructors
	 */
	public GameState(final GameMode gameMode, final Player[] players) {
		this.gameMode = gameMode;
		this.players = players;
		
		setReferenceInPlayers();
	}
	
	private void setReferenceInPlayers() {
		for(Player player : players) {
			player.setGameState(this);
		}
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
	public Player getPlayerByIdx(final int idx) { return players[idx]; }
	public int nextTurn() { return playerTurn = ++playerTurn < players.length ? playerTurn : 0; }
	public boolean getFinishingTurn() { return finishingTurn; }
	public void setFinishingTurn(final boolean finishingTurn) { this.finishingTurn = finishingTurn; }
}