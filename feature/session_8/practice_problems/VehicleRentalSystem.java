abstract class Vehicle {

    String vehicleName;
    double pricePerDay;
    boolean available = true;

    public Vehicle(String vehicleName, double pricePerDay) {
        this.vehicleName = vehicleName;
        this.pricePerDay = pricePerDay;
    }

    public abstract double calculateCharge(int days);

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getVehicleName() {
        return vehicleName;
    }
}

// Standard Car
class StandardCar extends Vehicle {

    public StandardCar(String name) {
        super(name, 50);
    }

    @Override
    public double calculateCharge(int days) {
        return pricePerDay * days;
    }
}

// Luxury Car
class LuxuryCar extends Vehicle {

    public LuxuryCar(String name) {
        super(name, 100);
    }

    @Override
    public double calculateCharge(int days) {
        return pricePerDay * days;
    }
}

// SUV
class SUV extends Vehicle {

    public SUV(String name) {
        super(name, 80);
    }

    @Override
    public double calculateCharge(int days) {
        return pricePerDay * days;
    }
}

class Customer {

    String customerName;

    public Customer(String customerName) {
        this.customerName = customerName;
    }
}

class Rental {

    Customer customer;
    Vehicle vehicle;
    int days;
    double totalCharge;
    boolean active = true;

    public Rental(Customer customer, Vehicle vehicle, int days) {

        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;

        totalCharge = vehicle.calculateCharge(days);
    }

    public void returnVehicle() {

        if (!active) {
            System.out.println("Vehicle already returned.");
            return;
        }

        vehicle.setAvailable(true);
        active = false;

        System.out.println(vehicle.getVehicleName()
                + " returned. Now available.");
    }
}

class RentalService {

    public Rental rentVehicle(Customer customer,
                              Vehicle vehicle, int days) {

        if (days <= 0) {
            System.out.println("Invalid rental duration.");
            return null;
        }

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getVehicleName()
                    + " is not available.");
            return null;
        }

        vehicle.setAvailable(false);

        Rental rental = new Rental(customer, vehicle, days);

        System.out.println(vehicle.getVehicleName()
                + " rented for " + days + " days.");

        System.out.printf("Total charge: $%.2f%n",
                rental.totalCharge);

        return rental;
    }
}

public class VehicleRentalSystem {

    public static void main(String[] args) {

        Customer customer = new Customer("John");

        Vehicle luxury = new LuxuryCar("Luxury Car A");
        Vehicle standard = new StandardCar("Standard Car B");

        RentalService service = new RentalService();

        // Rent luxury car
        Rental r1 = service.rentVehicle(
                customer, luxury, 3
        );

        // Rent standard car
        Rental r2 = service.rentVehicle(
                customer, standard, 5
        );

        // Try renting the same car again
        service.rentVehicle(customer, luxury, 2);

        // Return luxury car
        if (r1 != null) {
            r1.returnVehicle();
        }

        // Rent again after return
        service.rentVehicle(customer, luxury, 1);
    }
}