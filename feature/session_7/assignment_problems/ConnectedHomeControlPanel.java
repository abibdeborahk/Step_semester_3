abstract class HomeDevice {

    private static int counter = 1000;
    private final String serialNumber;

    public HomeDevice() {
        counter++;
        serialNumber = "HD-" + counter;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public abstract String activate();
}

interface RemoteControllable {
    String connect(String appId);
}

interface EnergyTrackable {
    double getConsumptionWatts();
}

class WashingMachine extends HomeDevice
        implements RemoteControllable, EnergyTrackable {

    double consumptionWatts;

    public WashingMachine(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Washing machine " + getSerialNumber()
                + " started a cycle";
    }

    @Override
    public String connect(String appId) {
        return getSerialNumber() + " connected to " + appId;
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class Refrigerator extends HomeDevice implements EnergyTrackable {

    double consumptionWatts;

    public Refrigerator(double consumptionWatts) {
        this.consumptionWatts = consumptionWatts;
    }

    @Override
    public String activate() {
        return "Refrigerator " + getSerialNumber()
                + " started";
    }

    @Override
    public double getConsumptionWatts() {
        return consumptionWatts;
    }
}

class MobileApp implements RemoteControllable {

    String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public String connect(String appId) {
        return appName + " connected to " + appId;
    }
}

public class ConnectedHomeControlPanel {

    static void connectAll(RemoteControllable[] items, String appId) {

        for (RemoteControllable item : items) {
            System.out.println(item.connect(appId));
        }
    }

    static double getConsumptionIfTrackable(HomeDevice d) {

        if (d instanceof EnergyTrackable) {

            EnergyTrackable e = (EnergyTrackable) d;

            return e.getConsumptionWatts();
        }

        return -1;
    }

    public static void main(String[] args) {

        WashingMachine wm = new WashingMachine(500.0);

        System.out.println(wm.activate());

        System.out.println(wm.connect("HomeConnect"));

        System.out.println("Washing Machine Consumption: "
                + wm.getConsumptionWatts() + " Watts");

        Refrigerator fridge = new Refrigerator(150.0);

        System.out.println("\nRefrigerator:");

        System.out.println(fridge.activate());

        System.out.println("Consumption: "
                + getConsumptionIfTrackable(fridge) + " Watts");

        MobileApp app = new MobileApp("HomeConnect App");

        System.out.println("\nMobile App:");

        System.out.println(app.connect("HomeConnect"));

        // Mixed array using interface reference
        RemoteControllable[] items = {wm, app};

        System.out.println("\nConnecting All:");

        connectAll(items, "HomeConnect");

        // Upcasting
        HomeDevice ref = wm;

        System.out.println("\nConsumption through HomeDevice reference:");

        System.out.println(getConsumptionIfTrackable(ref) + " Watts");
    }
}