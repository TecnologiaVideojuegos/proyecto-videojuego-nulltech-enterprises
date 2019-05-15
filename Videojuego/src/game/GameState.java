package game;

import java.util.ArrayList;

import entities.Player;

public class GameState {

	private final GameMode gameMode;
	private final Player[] players;
	private int playerTurn;
	private boolean finishingTurn;
	private ArrayList<Integer> miniGameStateIdsNotPlayed;
	private ArrayList<Integer> miniGameStateIdsPlayed;
	
	
	/*
	 * Constructors
	 */
	public GameState(final GameMode gameMode, final Player[] players, final ArrayList<Integer>miniGameStateIdsNotPlayed) {
		this.gameMode = gameMode;
		this.players = players;
		this.miniGameStateIdsNotPlayed = miniGameStateIdsNotPlayed;
		
		miniGameStateIdsPlayed = new ArrayList<Integer>();
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
	
	public ArrayList<Integer> getMiniGameStateIdsNotPlayed() { return miniGameStateIdsNotPlayed; }
	public void setMiniGameStateIdsNotPlayed(final ArrayList<Integer> MiniGameStateIdsNotPlayed) { this.miniGameStateIdsNotPlayed = miniGameStateIdsNotPlayed; }
	
	public ArrayList<Integer> getMiniGameStateIdsPlayed() { return miniGameStateIdsPlayed; }
	public void setMiniGameStateIdsPlayed(final ArrayList<Integer> MiniGameStateIdsPlayed) { this.miniGameStateIdsPlayed = miniGameStateIdsPlayed; }
}
