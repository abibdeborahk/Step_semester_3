import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

abstract class Room {

    String roomName;
    double pricePerNight;

    public Room(String roomName, double pricePerNight) {
        this.roomName = roomName;
        this.pricePerNight = pricePerNight;
    }

    public abstract double calculatePrice(int days);

    public String getRoomName() {
        return roomName;
    }
}

class StandardRoom extends Room {

    public StandardRoom(String name) {
        super(name, 150);
    }

    @Override
    public double calculatePrice(int days) {
        return pricePerNight * days;
    }
}

class DeluxeRoom extends Room {

    public DeluxeRoom(String name) {
        super(name, 200);
    }

    @Override
    public double calculatePrice(int days) {
        return pricePerNight * days;
    }
}

class SuiteRoom extends Room {

    public SuiteRoom(String name) {
        super(name, 300);
    }

    @Override
    public double calculatePrice(int days) {
        return pricePerNight * days;
    }
}

class Customer {

    String name;

    public Customer(String name) {
        this.name = name;
    }
}

class Reservation {

    Room room;
    Customer customer;
    LocalDate startDate;
    LocalDate endDate;

    boolean active = true;

    public Reservation(Room room, Customer customer,
                       LocalDate startDate, LocalDate endDate) {

        this.room = room;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean overlaps(LocalDate start, LocalDate end) {

        return active &&
                start.isBefore(endDate) &&
                end.isAfter(startDate);
    }

    public boolean cancel(LocalDate today) {

        // Cancellation allowed until one day before check-in
        LocalDate deadline = startDate.minusDays(1);

        if (!active) {
            System.out.println("Reservation already cancelled.");
            return false;
        }

        if (today.isAfter(deadline)) {
            System.out.println("Cancellation deadline has passed.");
            return false;
        }

        active = false;

        System.out.println("Reservation for "
                + room.roomName + " cancelled successfully.");

        return true;
    }
}

class BookingManager {

    ArrayList<Reservation> reservations = new ArrayList<>();

    public Reservation bookRoom(Room room, Customer customer,
                                LocalDate start, LocalDate end) {

        if (!start.isBefore(end)) {
            System.out.println("Invalid booking dates.");
            return null;
        }

        // Check overlapping reservations
        for (Reservation r : reservations) {

            if (r.room == room && r.overlaps(start, end)) {

                System.out.println("Booking failed: "
                        + room.roomName + " is not available for "
                        + start + " to " + end);

                return null;
            }
        }

        int days = (int) ChronoUnit.DAYS.between(start, end);

        Reservation reservation =
                new Reservation(room, customer, start, end);

        reservations.add(reservation);

        System.out.println(room.roomName + " booked from "
                + start + " to " + end + ".");

        System.out.printf("Total price: $%.2f%n",
                room.calculatePrice(days));

        return reservation;
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {

        Customer c1 = new Customer("John");
        Customer c2 = new Customer("Jane");

        Room deluxe = new DeluxeRoom("Deluxe Room 101");
        Room standard = new StandardRoom("Standard Room 205");

        BookingManager manager = new BookingManager();

        LocalDate start1 = LocalDate.of(2024, 12, 1);
        LocalDate end1 = LocalDate.of(2024, 12, 5);

        LocalDate start2 = LocalDate.of(2024, 12, 3);
        LocalDate end2 = LocalDate.of(2024, 12, 7);

        // First booking
        Reservation r1 = manager.bookRoom(
                deluxe, c1, start1, end1
        );

        // Second booking
        manager.bookRoom(
                standard, c2, start2, end2
        );

        // Attempt overlapping booking
        manager.bookRoom(
                deluxe, c2, start2, end2
        );

        // Cancel before the cancellation deadline
        if (r1 != null) {
            r1.cancel(LocalDate.of(2024, 11, 20));
        }
    }
}