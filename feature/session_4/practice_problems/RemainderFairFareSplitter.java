import java.util.Arrays;

class FareSplitter {

    private String tripId;
    private double totalFare;
    private int passengerCount;

    // Full constructor
    public FareSplitter(String tripId,
                        double totalFare,
                        int passengerCount) {

        if (totalFare < 0) {
            throw new IllegalArgumentException(
                    "Fare cannot be negative");
        }

        if (passengerCount <= 0) {
            throw new IllegalArgumentException(
                    "Passenger count must be positive");
        }

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    // Constructor with fare
    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 1);
    }

    // Provisional constructor
    public FareSplitter(String tripId) {
        this(tripId, 0.0, 2);
    }

    public double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        if (passengerCount == 0) {
            return result;
        }

        double share = totalFare / passengerCount;

        // Give normal share to everyone except last passenger
        double sum = 0;

        for (int i = 0; i < passengerCount - 1; i++) {

            result[i] = Math.floor(share * 100) / 100.0;
            sum += result[i];
        }

        // Last passenger gets the remaining amount
        result[passengerCount - 1] =
                Math.round((totalFare - sum) * 100) / 100.0;

        return result;
    }

    public boolean isConfirmationOverdue(int confirmed,
                                         int expected) {

        return confirmed < expected;
    }
}

public class RemainderFairFareSplitter {
    public static void main(String[] args) {

        FareSplitter f1 =
                new FareSplitter("TRIP001", 100000, 3);

        System.out.println(
                Arrays.toString(f1.fareBreakdown())
        );

        FareSplitter f2 =
                new FareSplitter("TRIP003");

        System.out.println(
                Arrays.toString(f2.fareBreakdown())
        );

        System.out.println(
                f1.isConfirmationOverdue(2, 3)
        );
    }
}