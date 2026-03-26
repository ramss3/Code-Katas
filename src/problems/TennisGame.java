package problems;

import java.util.HashMap;

public class TennisGame {
	
	private final String player1;
	private final String player2;
	private int score1 = 0, score2 = 0;
	private final HashMap<Integer, String> scoreMap = new HashMap<>();
	
	public TennisGame(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
		scoreMap.put(0, "Love");
		scoreMap.put(1, "Fifteen");
		scoreMap.put(2, "Thirty");
		scoreMap.put(3, "Forty");
	}
	
	public void wonPoint(String playerName) {
		if(playerName.equals(player1)) {
			score1++;		
		} else if(playerName.equals(player2)) {
			score2++;
		} else {
			throw new IllegalArgumentException("Unknown player: " + playerName);
		}
	}
	
	public String getGameScore() {		
		if(isDeuce()) {
			return "Deuce";
		} else if(isFinished()) {
			return score1 > score2 ? "Game " + player1 : "Game " + player2;
		} else if(score1 == score2) {
			return scoreMap.get(score1) + " - All";
		} else if(isAdvantage()) {
			return score1 > score2 ? "Advantage " + player1 : "Advantage " + player2;
		} else {
			return scoreMap.get(score1) + " - " + scoreMap.get(score2);
		}		
	}
	
	private boolean isDeuce() {
		return score1 >= 3 && score1 == score2;
	}
	
	private boolean isFinished() {
		return (score1 >= 4 || score2 >= 4) && Math.abs(score1 - score2) >= 2;
	}
	
	private boolean isAdvantage() {
		return (score1 >= 4 || score2 >= 4) && Math.abs(score1 - score2) == 1;
	}	
	
	public static void main(String[] args) {
		TennisGame gameTennis = new TennisGame("Joe", "Michael");
		gameTennis.wonPoint("Joe");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Joe");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Joe");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Joe");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Joe");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		gameTennis.wonPoint("Michael");
		System.out.println(gameTennis.getGameScore());
		
	}
}
