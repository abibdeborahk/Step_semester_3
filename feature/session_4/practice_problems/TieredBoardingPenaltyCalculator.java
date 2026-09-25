final class BoardingPenaltyCalculator {

    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {

        if (minimumPenaltyPercent < 0) {
            throw new IllegalArgumentException(
                    "Minimum penalty cannot be negative");
        }

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(
            double ticketFare,
            int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Invalid input");
        }

        if (minutesLate == 0) {
            return 0;
        }

        double penalty = 0;

        int firstBracket = Math.min(minutesLate, 5);
        penalty += firstBracket *
                   (ticketFare * 0.005);

        if (minutesLate > 5) {

            int secondBracket =
                    Math.min(minutesLate - 5, 10);

            penalty += secondBracket *
                       (ticketFare * 0.01);
        }

        if (minutesLate > 15) {

            int thirdBracket =
                    minutesLate - 15;

            penalty += thirdBracket *
                       (ticketFare * 0.02);
        }

        double minimumPenalty =
                ticketFare *
                (minimumPenaltyPercent / 100);

        return Math.max(penalty, minimumPenalty);
    }
}

public class TieredBoardingPenaltyCalculator {

    public static void main(String[] args) {

        BoardingPenaltyCalculator calculator =
                new BoardingPenaltyCalculator(1);

        System.out.println(
                calculator.calculatePenalty(1000, 0)
        );

        System.out.println(
                calculator.calculatePenalty(1000, 1)
        );

        System.out.println(
                calculator.calculatePenalty(1000, 16)
        );
    }
}