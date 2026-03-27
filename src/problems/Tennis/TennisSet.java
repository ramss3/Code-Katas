package problems.Tennis;

public class TennisSet {
	
	private TennisGame currGame;
	private Tiebreak tiebreak = null;
	private final Player player1;
	private final Player player2;
	private int gamesPlayer1, gamesPlayer2;
	
	public TennisSet(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.gamesPlayer1 = 0;
		this.gamesPlayer2 = 0;
		currGame = new TennisGame(player1, player2);
	}
	
	public void wonPoint(Player player) {
		if(isFinished()) {
			throw new IllegalArgumentException("Set already finished");
		}
		
		if(isTiebreak()) {
			if(tiebreak == null) {
				tiebreak = new Tiebreak(player1, player2);
			}
			
			tiebreak.pointWon(player);
			
			if(tiebreak.isFinished()) {
				Player winner = tiebreak.tiebreakWinner();
				if(winner.equals(player1)) {
					gamesPlayer1++;
				} else {
					gamesPlayer2++;
				}
			}
			
			return;
		} else {
			currGame.wonPoint(player);
			
			if(currGame.gameWinner() != null) {
				playGame();
			}
		}		
	}
	
	public void playGame() {
		Player winner = currGame.gameWinner();
		
		if(player1.equals(winner)) {
			gamesPlayer1++;
		} else if(player2.equals(winner)) {
			gamesPlayer2++;
		} else {
			throw new IllegalArgumentException("Unkown player");
		}
		
		if(!isFinished()) {
			if(isTiebreak()) {
				tiebreak = new Tiebreak(player1, player2);
			} else {				
				currGame = new TennisGame(player1, player2);
			}
		}
	}
	
	public String getSetScore() {
		return gamesPlayer1 + "-" + gamesPlayer2;
	}
	
	public boolean isFinished() {
		int maxGames = Math.max(gamesPlayer1, gamesPlayer2);
		int minGames = Math.min(gamesPlayer1, gamesPlayer2);
		return (maxGames == 6 && minGames <= 4) || 
				(maxGames == 7 && (minGames == 5 || minGames == 6)) ;
	}
	
	 public boolean isTiebreak() {
		 return gamesPlayer1 == 6 && gamesPlayer2 == 6;
	 }
	
	public Player setWinner() {
		if(!isFinished()) return null;
		return gamesPlayer1 > gamesPlayer2 ? player1 : player2;
	}	
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public TennisGame getCurrGame() {
		return currGame;
	}
	
	public Tiebreak getTiebreak() {
		return tiebreak;
	}
	
}
