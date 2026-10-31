
interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

abstract class Device {
    String id;
    boolean isOn;

    Device(String id) {
        this.id = id;
        this.isOn = false;
    }

    void turnOn() {
        isOn = true;
        System.out.println(id + " is ON");
    }

    void turnOff() {
        isOn = false;
        System.out.println(id + " is OFF");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    double energy;

    Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class SmartHomePlatform {
    static void process(Device device, String command, String value) {
        switch (command) {
            case "ON":
                device.turnOn();
                break;

            case "OFF":
                device.turnOff();
                break;

            case "DIM":
                if (device instanceof Dimmable) {
                    ((Dimmable) device).dim(Integer.parseInt(value));
                } else {
                    System.out.println(device.id + " rejected: DIM unsupported");
                }
                break;

            case "SCHEDULE":
                if (device instanceof Schedulable) {
                    ((Schedulable) device).schedule(value);
                } else {
                    System.out.println(device.id + " rejected: SCHEDULE unsupported");
                }
                break;

            case "ENERGY":
                if (device instanceof EnergyMonitor) {
                    ((EnergyMonitor) device).showEnergy();
                } else {
                    System.out.println(device.id + " rejected: ENERGY unsupported");
                }
                break;
        }
    }

    public static void main(String[] args) {
        Device l1 = new Light("L1");
        Device f1 = new Fan("F1");
        Device p1 = new Plug("P1", 12);

        process(l1, "ON", "");
        process(l1, "DIM", "40");
        process(f1, "DIM", "30");
        process(f1, "SCHEDULE", "22:00");
        process(p1, "ENERGY", "");
        process(l1, "ENERGY", "");
    }
}
