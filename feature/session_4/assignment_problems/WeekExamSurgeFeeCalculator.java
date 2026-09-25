final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {

        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException(
                    "Invalid minimum surge percent");
        }

        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(
            double orderValue,
            int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Invalid input");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int firstTier = Math.min(delayMinutes, 5);

        fee += firstTier
                * 0.005
                * orderValue;

        if (delayMinutes > 5) {

            int secondTier =
                    Math.min(delayMinutes - 5, 10);

            fee += secondTier
                    * 0.01
                    * orderValue;
        }

        if (delayMinutes > 15) {

            int thirdTier =
                    delayMinutes - 15;

            fee += thirdTier
                    * 0.02
                    * orderValue;
        }

        double minimumFee =
                (minimumSurgePercent / 100)
                * orderValue;

        return Math.max(fee, minimumFee);
    }
}

public class WeekExamSurgeFeeCalculator {

    public static void main(String[] args) {

        SurgeFeeCalculator calculator =
                new SurgeFeeCalculator(1);

        System.out.println(
                calculator.calculateSurgeFee(500, 0));

        System.out.println(
                calculator.calculateSurgeFee(500, 1));

        System.out.println(
                calculator.calculateSurgeFee(500, 16));
    }
}