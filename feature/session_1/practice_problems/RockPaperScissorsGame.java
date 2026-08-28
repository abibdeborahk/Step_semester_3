import java.util.Random;

public class RockPaperScissorsGame {

    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Random random = new Random();

        // Predefined player moves make the program easy to run as a live demo.
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};

        String[] computerMoves = new String[playerMoves.length];
        String[] results = new String[playerMoves.length];

        int wins = 0;
        int losses = 0;
        int draws = 0;

        for (int i = 0; i < playerMoves.length; i++) {
            computerMoves[i] = MOVES[random.nextInt(MOVES.length)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("Round | Player Move | Computer Move | Result");
        System.out.println("---------------------------------------------");

        for (int i = 0; i < playerMoves.length; i++) {
            System.out.printf(
                "%5d | %-11s | %-13s | %s%n",
                i + 1,
                playerMoves[i],
                computerMoves[i],
                results[i]
            );
        }

        double winPercentage = (double) wins / playerMoves.length * 100;

        System.out.println();
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.printf("Win Percentage: %.1f%%%n", winPercentage);
    }
}
