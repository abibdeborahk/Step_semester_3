class BusTicketAccount {

    protected String bookingId;
    protected double ticketFare;

    static {
        System.out.println(
                "Bus Ticket System Initialized");
    }

    public BusTicketAccount(
            String bookingId,
            double ticketFare) {

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    public final double calculatePenalty(
            int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException(
                    "Invalid minutes");
        }

        if (minutesLate == 0) {
            return 0;
        }

        return ticketFare * 0.01 * minutesLate;
    }
}

// Child class
class Sleeper extends BusTicketAccount {

    public Sleeper(
            String bookingId,
            double ticketFare) {

        super(bookingId, ticketFare);
    }
}

class AccountProcessor {

    public static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts.length != amounts.length ||
            accounts.length != minutesLateArray.length) {

            System.out.println(
                    "Array lengths mismatch");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;

        double totalPenalty = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (accounts[i] instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }

            double penalty =
                    accounts[i].calculatePenalty(
                            minutesLateArray[i]);

            totalPenalty += penalty;
        }

        System.out.println(
                processed + " processed");
        System.out.println(
                nullSkipped + " null skipped");
        System.out.println(
                sleeperCount + " sleeper");
        System.out.println(
                regularCount + " regular");
        System.out.println(
                "Grand Total Penalty = "
                + totalPenalty);
    }
}

public class NightlyFleetReconciliationEngine {

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {

            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200, 900, 700
        };

        int[] minutes = {
            10, 5, 0
        };

        AccountProcessor.processBatch(
                accounts,
                amounts,
                minutes
        );
    }
}