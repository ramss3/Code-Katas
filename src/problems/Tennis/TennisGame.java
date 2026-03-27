package problems.Tennis;

import java.util.HashMap;

public class TennisGame {
	
	private final Player player1;
	private final Player player2;
	private int score1 = 0, score2 = 0;
	private final HashMap<Integer, String> scoreMap = new HashMap<>();
	
	public TennisGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		scoreMap.put(0, "Love");
		scoreMap.put(1, "Fifteen");
		scoreMap.put(2, "Thirty");
		scoreMap.put(3, "Forty");
	}
	
	public void wonPoint(Player playerName) {
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
		} else if(gameWon()) {
			return score1 > score2 ? "Game " + player1.getName() : "Game " + player2.getName();
		} else if(score1 == score2) {
			return scoreMap.get(score1) + " - All";
		} else if(isAdvantage()) {
			return score1 > score2 ? "Advantage " + player1.getName() : "Advantage " + player2.getName();
		} else {
			return scoreMap.get(score1) + " - " + scoreMap.get(score2);
		}		
	}
	
	private boolean isDeuce() {
		return score1 >= 3 && score1 == score2;
	}
	
	private boolean gameWon() {
		return (score1 >= 4 || score2 >= 4) && Math.abs(score1 - score2) >= 2;
	}
	
	private boolean isAdvantage() {
		return (score1 >= 4 || score2 >= 4) && Math.abs(score1 - score2) == 1;
	}	
	
	public Player gameWinner() {
		if(!gameWon()) return null;
		return score1 > score2 ? player1 : player2;
	}
	
	public static void main(String[] args) {
	    Player joe = new Player("Joe");
	    Player michael = new Player("Michael");

	    TennisGame gameTennis = new TennisGame(joe, michael);

	    gameTennis.wonPoint(joe);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(joe);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(joe);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(joe);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(joe);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());

	    gameTennis.wonPoint(michael);
	    System.out.println(gameTennis.getGameScore());
	}
}
