class EventTicket {

    private static int ticketCounter = 0;

    protected final String ticketId;

    protected double basePrice;
    protected double amountPaid;


    public EventTicket(double basePrice) {

        ticketCounter++;

        ticketId =
            "TCK-" + (1000 + ticketCounter);

        this.basePrice = basePrice;
        this.amountPaid = 0;
    }


    public void pay(double amount) {

        amountPaid += amount;
    }


    public void pay(double amount, String mode) {

        System.out.println(
            "Payment Mode: " + mode
        );

        pay(amount);
    }


    public double getBalanceDue() {

        return basePrice - amountPaid;
    }


    public static boolean isValidPromoCode(
        String code) {

        if (code == null ||
            code.length() != 5) {

            return false;
        }


        // First character must be F
        if (code.charAt(0) != 'F') {
            return false;
        }


        // Next three characters must be digits
        for (int i = 1; i <= 3; i++) {

            if (!Character.isDigit(
                    code.charAt(i))) {

                return false;
            }
        }


        // Last character must be uppercase
        if (!Character.isUpperCase(
                code.charAt(4))) {

            return false;
        }


        return true;
    }


    public static int getTicketsIssued() {

        return ticketCounter;
    }


    public String getTicketId() {

        return ticketId;
    }
}


class GroupTicket extends EventTicket {

    private int groupSize;


    public GroupTicket(double basePrice,
                       int groupSize) {

        super(basePrice);

        this.groupSize = groupSize;
    }


    public int getGroupSize() {

        return groupSize;
    }
}


public class FestWideTicket {


    public static String processNightlySettlement(
        EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;


        for (EventTicket ticket : tickets) {

            if (ticket == null) {

                nullSkipped++;
                continue;
            }


            processed++;


            if (ticket instanceof GroupTicket) {

                groupCount++;

            } else {

                individualCount++;
            }
        }


        return processed +
               " processed | " +
               nullSkipped +
               " null skipped | " +
               groupCount +
               " group | " +
               individualCount +
               " individual";
    }


    public static void main(String[] args) {

        EventTicket t1 =
            new EventTicket(500);


        System.out.println(
            t1.getTicketId()
        );


        System.out.println(
            EventTicket.getTicketsIssued()
        );


        System.out.println(
            EventTicket.isValidPromoCode(
                "F123A"
            )
        );


        System.out.println(
            EventTicket.isValidPromoCode(
                "F12A"
            )
        );


        System.out.println(
            EventTicket.isValidPromoCode(
                "X123A"
            )
        );


        t1.pay(200);

        t1.pay(200, "UPI");


        System.out.println(
            t1.getBalanceDue()
        );


        EventTicket[] tickets = {

            new GroupTicket(2000, 5),

            null,

            new EventTicket(500)
        };


        System.out.println(
            processNightlySettlement(tickets)
        );
    }
}