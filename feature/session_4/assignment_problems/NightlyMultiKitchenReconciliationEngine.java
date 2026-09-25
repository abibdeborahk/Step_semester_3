class DeliveryAccount {

    protected String studentId;
    protected double orderValue;

    static {
        System.out.println(
                "Delivery Account System Initialized");
    }

    public DeliveryAccount(
            String studentId,
            double orderValue) {

        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    public DeliveryAccount(String studentId) {

        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(
            int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException(
                    "Delay cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double fee = 0.0;

        int firstTier =
                Math.min(delayMinutes, 5);

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

        return fee;
    }
}

class Premium extends DeliveryAccount {

    public Premium(String studentId,
                   double orderValue) {

        super(studentId, orderValue);
    }
}


class ReconciliationProcessor {

    public void processAccount(
            DeliveryAccount account,
            double amount,
            int delayMinutes) {

        double fee =
                account.calculateSurgeFee(delayMinutes);

        if (account instanceof Premium) {

            System.out.println(
                    account.studentId
                    + " -> Premium Account");

        } else {

            System.out.println(
                    account.studentId
                    + " -> Regular Account");
        }

        System.out.println(
                "Amount: " + amount);

        System.out.println(
                "Surge Fee: " + fee);
    }


    public static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        if (accounts.length != amounts.length
                || accounts.length
                != delayMinutesArray.length) {

            System.out.println(
                    "Array lengths do not match.");

            return;
        }

        ReconciliationProcessor processor =
                new ReconciliationProcessor();

        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;

        double totalSurgeFee = 0.0;

        for (int i = 0;
             i < accounts.length;
             i++) {

            // Handle null safely
            if (accounts[i] == null) {

                nullSkipped++;
                continue;
            }

            processor.processAccount(
                    accounts[i],
                    amounts[i],
                    delayMinutesArray[i]);

            double fee =
                    accounts[i].calculateSurgeFee(
                            delayMinutesArray[i]);

            totalSurgeFee += fee;

            processed++;

            if (accounts[i] instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println();
        System.out.println("----- Summary -----");

        System.out.println(
                "Processed: " + processed);

        System.out.println(
                "Null skipped: " + nullSkipped);

        System.out.println(
                "Premium: " + premiumCount);

        System.out.println(
                "Regular: " + regularCount);

        System.out.println(
                "Grand Total Surge Fees: "
                + totalSurgeFee);
    }
}


public class NightlyMultiKitchenReconciliationEngine {

    public static void main(String[] args) {

        DeliveryAccount[] accounts = {

            new Premium("STU001", 500),

            null,

            new DeliveryAccount(
                    "STU002",
                    300)
        };

        double[] amounts = {
            500,
            400,
            300
        };

        int[] delayMinutes = {
            10,
            5,
            0
        };

        ReconciliationProcessor.processBatch(
                accounts,
                amounts,
                delayMinutes);
    }
}