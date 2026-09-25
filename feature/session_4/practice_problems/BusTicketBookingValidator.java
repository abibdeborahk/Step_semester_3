import java.util.*;

class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {

        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid passenger name");
        }

        for (char ch : passengerName.trim().toCharArray()) {
            if (!Character.isLetter(ch) && ch != ' ') {
                throw new IllegalArgumentException("Invalid passenger name");
            }
        }

        if (destination == null || destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid destination");
        }

        this.passengerName = passengerName.trim();
        this.destination = destination.trim();
        this.checkedIn = false;
    }

    public void markCheckedIn() {
        if (checkedIn) {
            throw new IllegalStateException("Already checked in");
        }

        checkedIn = true;
    }

    public static void processBatch(String[][] rawBookings) {

        Set<String> acceptedBookings = new HashSet<>();

        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        for (String[] booking : rawBookings) {

            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                String passengerName = booking[0];
                String destination = booking[1];

                String key = passengerName.trim().toLowerCase()
                        + "|" + destination.trim().toLowerCase();

                if (acceptedBookings.contains(key)) {
                    duplicates++;
                    continue;
                }

                BusTicket ticket =
                        new BusTicket(passengerName, destination);

                acceptedBookings.add(key);
                valid++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates skipped: " + duplicates);
    }
}

public class BusTicketBookingValidator {
    public static void main(String[] args) {

        String[][] bookings = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };

        BusTicket.processBatch(bookings);
    }
}