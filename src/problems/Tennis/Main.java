package problems.Tennis;

public class Main {
	public static void main(String[] args) {
        Player tomas = new Player("Tomás");
        Player rafael = new Player("Rafael");

        TennisMatch match = new TennisMatch(tomas, rafael, MatchFormat.BEST_OF_3); // best of 3

        printFullState(match);

        // =========================
        // SET 1
        // Joe wins set 6-4
        // =========================

        winGame(match, tomas);     // 1-0
        winGame(match, rafael); // 1-1
        winGame(match, tomas);     // 2-1
        winGame(match, tomas);     // 3-1
        winGame(match, rafael); // 3-2
        winGame(match, tomas);     // 4-2
        winGame(match, rafael); // 4-3
        winGame(match, tomas);     // 5-3
        winGame(match, rafael); // 5-4
        winGame(match, tomas);     // 6-4 -> Tomas wins set

        // =========================
        // SET 2
        // Rafael wins set 7-6 via tiebreak
        // =========================

        winGame(match, tomas);     // 1-0
        winGame(match, rafael); // 1-1
        winGame(match, tomas);     // 2-1
        winGame(match, rafael); // 2-2
        winGame(match, tomas);     // 3-2
        winGame(match, rafael); // 3-3
        winGame(match, tomas);     // 4-3
        winGame(match, rafael); // 4-4
        winGame(match, tomas);     // 5-4
        winGame(match, rafael); // 5-5
        winGame(match, tomas);     // 6-5
        winGame(match, rafael); // 6-6 -> tiebreak starts

        winTiebreak(match, rafael, 7, 4); // Rafael wins tiebreak -> set 7-6

        // =========================
        // SET 3
        // Joe wins set 6-0
        // =========================

        winGame(match, tomas); // 1-0
        winGame(match, tomas); // 2-0
        winGame(match, tomas); // 3-0
        winGame(match, tomas); // 4-0
        winGame(match, tomas); // 5-0
        winGame(match, tomas); // 6-0 -> Tomas wins match

        System.out.println("\n===== FINAL RESULT =====");
        printFullState(match);
    }

    private static void winGame(TennisMatch match, Player player) {
        System.out.println("\n--- New game started ---");
        printFullState(match);

        match.wonPoint(player);
        printPointState(match, player);

        match.wonPoint(player);
        printPointState(match, player);

        match.wonPoint(player);
        printPointState(match, player);

        match.wonPoint(player);
        printPointState(match, player);

        System.out.println("Game won by: " + player.getName());
        printFullState(match);
    }

    private static void winTiebreak(TennisMatch match, Player winner, int winnerPoints, int loserPoints) {
        Player loser = match.getCurrSet().getPlayer1().equals(winner)
                ? match.getCurrSet().getPlayer2()
                : match.getCurrSet().getPlayer1();

        System.out.println("\n--- Tiebreak started ---");
        printFullState(match);

        for (int i = 0; i < winnerPoints - 1; i++) {
            match.wonPoint(winner);
            printPointState(match, winner);
        }

        for (int i = 0; i < loserPoints; i++) {
            match.wonPoint(loser);
            printPointState(match, loser);
        }

        // final tiebreak point
        match.wonPoint(winner);

        System.out.println("Point won by: " + winner.getName());
        System.out.println("Tiebreak won by: " + winner.getName());
        System.out.println("Set score is now reflected in the match.");
        printFullState(match);
    }

    private static void printPointState(TennisMatch match, Player lastPointWinner) {
        TennisSet set = match.getCurrSet();

        System.out.println("Point won by: " + lastPointWinner.getName());

        if (set.isTiebreak() && set.getTiebreak() != null) {
            System.out.println("Tiebreak score: " + set.getTiebreak().getScore());
        } else if (!set.isFinished()) {
            System.out.println("Current game score: " + set.getCurrGame().getGameScore());
        }

        System.out.println("Current set score: " + set.getSetScore());
        System.out.println("Current match score: " + match.getMatchScore());
    }

    private static void printFullState(TennisMatch match) {
        TennisSet set = match.getCurrSet();

        System.out.println("Match score: " + match.getMatchScore());
        System.out.println("Set score: " + set.getSetScore());

        if (set.isTiebreak() && set.getTiebreak() != null) {
            System.out.println("Tiebreak score: " + set.getTiebreak().getScore());
        } else if (!set.isFinished()) {
            System.out.println("Game score: " + set.getCurrGame().getGameScore());
        }

        if (set.setWinner() != null) {
            System.out.println("Set winner: " + set.setWinner().getName());
        }

        if (match.matchWinner() != null) {
            System.out.println("Match winner: " + match.matchWinner().getName());
        }
    }
}
