class EventTicket {

    protected double basePrice;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getBalanceDue() {
        return basePrice;
    }

    public void printTicket(StringBuilder sb) {
        sb.append(
            "Standard | Balance: "
        ).append(getBalanceDue());
    }
}


class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(double basePrice,
                          String track) {

        super(basePrice);
        this.track = track;
    }

    public String getTrack() {
        return track;
    }


    @Override
    public void printTicket(StringBuilder sb) {

        sb.append(
            "Workshop | Track: "
        ).append(track)
         .append(" | Balance: ")
         .append(getBalanceDue());
    }
}


public class TheNightlyTicketAnnouncer {


    public static String batchPrint(
        EventTicket[] tickets) {

        StringBuilder sb =
            new StringBuilder();


        for (EventTicket ticket : tickets) {

            // Polymorphic method call
            ticket.printTicket(sb);


            // Safe downcasting
            if (ticket instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                    (WorkshopTicket) ticket;

                sb.append(
                    " [Track via downcast: "
                ).append(workshop.getTrack())
                 .append("]");
            }

            sb.append(" | ");
        }

        return sb.toString();
    }


    public static void main(String[] args) {

        EventTicket[] tickets = {

            new EventTicket(500),

            new WorkshopTicket(
                1200,
                "AI/ML"
            )
        };


        String result =
            batchPrint(tickets);

        System.out.println(result);


        EventTicket plain =
            new EventTicket(500);

       
    }
}