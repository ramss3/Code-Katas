package problems.Tennis;

public class Tiebreak {

	private final Player player1;
	private final Player player2;
	private int pointsPlayer1;
	private int pointsPlayer2;
	
	public Tiebreak(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		pointsPlayer1 = 0;
		pointsPlayer2 = 0;
	}
	
	public void pointWon(Player player) {
		if(player.equals(player1)) {
			pointsPlayer1++;
		} else if(player.equals(player2)) {
			pointsPlayer2++;
		} else {
			throw new IllegalArgumentException("Unkown player");
		}
	}
	
	public boolean isFinished() {
		return (Math.max(pointsPlayer2, pointsPlayer1) >= 7) && (Math.abs(pointsPlayer1 - pointsPlayer2) > 1);
	}
	
	public Player tiebreakWinner() {
		if(!isFinished()) return null;
		return pointsPlayer1 > pointsPlayer2 ? player1 : player2;
	}	
	
	public String getScore() {
		return pointsPlayer1 + " - " + pointsPlayer2;
	}
}
