package problems.Tennis;

public class TennisMatch {

	private TennisSet currSet;
	private final int setsToWin;
	private final Player player1;
	private final Player player2;
	private int setsPlayer1;
	private int setsPlayer2;
	
	public TennisMatch(Player player1, Player player2, MatchFormat matchFormat) {

		this.player1 = player1;
		this.player2 = player2;
		this.setsPlayer1 = 0;
		this.setsPlayer2 = 0;
		this.setsToWin = matchFormat == MatchFormat.BEST_OF_3 ? 2 : 3;
		this.currSet = new TennisSet(player1, player2);
	}
	
	public void wonPoint(Player player) {
		if(isFinished()) {
			throw new IllegalArgumentException("Match finished");
		}
		
		currSet.wonPoint(player);
		
		if(currSet.isFinished()) {
			Player winner = currSet.setWinner();
			
			if(winner.equals(player1)) {
				setsPlayer1++;
			} else {
				setsPlayer2++;
			}
			
			if(!isFinished()) currSet = new TennisSet(player1, player2);
		}
	}
	
	public boolean isFinished() {
		return setsPlayer1 == setsToWin || setsPlayer2 == setsToWin;
	}
	
	public Player matchWinner() {
		if(!isFinished()) return null;
		return setsPlayer1 > setsPlayer2 ? player1 : player2;
	}
	
	public String getMatchScore() {
		return setsPlayer1 + ":" + setsPlayer2;
	}
	
	public TennisSet getCurrSet() {
		return currSet;
	}
	
}
