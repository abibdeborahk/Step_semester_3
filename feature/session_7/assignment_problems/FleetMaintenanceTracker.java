abstract class ServiceableVehicle {

    private double mileage;

    public ServiceableVehicle() {
        mileage = 0;
    }

    public double getMileage() {
        return mileage;
    }

    public void addMileage(double km) {

        if (km >= 0) {
            mileage = mileage + km;
        } else {
            System.out.println("Invalid distance!");
        }
    }

    public abstract String performMaintenance();
}

interface Insurable {
    String getInsuranceInfo();
}

class Forklift extends ServiceableVehicle implements Insurable {

    String assetTag;

    public Forklift(String assetTag) {
        this.assetTag = assetTag;
    }

    @Override
    public String performMaintenance() {
        return "Forklift " + assetTag
                + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {
        return "Insured under fleet policy - Asset " + assetTag;
    }
}

class HeavyDutyForklift extends Forklift {

    public HeavyDutyForklift(String assetTag) {
        super(assetTag);
    }

    @Override
    public String performMaintenance() {
        return super.performMaintenance()
                + " | high-pressure hydraulic check complete";
    }
}

public class FleetMaintenanceTracker {

    static String getInsuranceIfApplicable(ServiceableVehicle v) {

        if (v instanceof Insurable) {

            Insurable i = (Insurable) v;

            return i.getInsuranceInfo();
        }

        return "No insurance record exists";
    }

    public static void main(String[] args) {

        Forklift f = new Forklift("FL-22");

        f.addMileage(120);

        System.out.println("Mileage: " + f.getMileage());

        System.out.println(f.performMaintenance());

        HeavyDutyForklift hd = new HeavyDutyForklift("HD-9");

        System.out.println(hd.performMaintenance());

        System.out.println("\nInsurance Details:");

        System.out.println(getInsuranceIfApplicable(f));

        System.out.println(getInsuranceIfApplicable(hd));

        // Testing negative mileage
        f.addMileage(-50);

        System.out.println("Mileage: " + f.getMileage());

        // Upcasting
        ServiceableVehicle ref = f;

        System.out.println(getInsuranceIfApplicable(ref));
    }
}