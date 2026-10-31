abstract class LibraryItem {

    private static int counter = 1000;
    private final String itemId;
    protected String title;

    public LibraryItem(String title) {
        counter++;
        this.itemId = "LIB-" + counter;
        this.title = title;
    }

    public String getItemId() {
        return itemId;
    }

    public abstract int getLoanPeriodDays();
}

interface Renewable {
    String renew();
}

interface Reservable {
    String reserve();
}

class Textbook extends LibraryItem implements Renewable, Reservable {

    public Textbook(String title) {
        super(title);
    }

    @Override
    public int getLoanPeriodDays() {
        return 14;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }

    @Override
    public String reserve() {
        return title + " reserved";
    }
}

class Magazine extends LibraryItem implements Renewable {

    public Magazine(String title) {
        super(title);
    }

    @Override
    public int getLoanPeriodDays() {
        return 7;
    }

    @Override
    public String renew() {
        return title + " renewed";
    }
}

class DigitalPass implements Renewable {

    String resourceName;

    public DigitalPass(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String renew() {
        return resourceName + " renewed";
    }
}

public class CommunityLibraryCheckout {

    static void processCheckouts(LibraryItem[] items) {

        for (LibraryItem item : items) {

            System.out.println(
                item.title + " - Loan period: "
                + item.getLoanPeriodDays() + " days"
            );
        }
    }

    static String reserveIfSupported(Object o) {

        if (o instanceof Reservable) {

            Reservable r = (Reservable) o;

            return r.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Textbook t = new Textbook("Java Fundamentals");

        System.out.println("Loan period: "
                + t.getLoanPeriodDays());

        System.out.println(t.renew());

        System.out.println(t.reserve());

        Magazine m = new Magazine("Tech Monthly");

        System.out.println("\nMagazine:");

        System.out.println("Loan period: "
                + m.getLoanPeriodDays());

        System.out.println(reserveIfSupported(m));

        DigitalPass d = new DigitalPass("E-Journal Access");

        System.out.println("\nDigital Pass:");

        System.out.println(d.renew());

        System.out.println(reserveIfSupported(d));

        // Upcasting
        LibraryItem ref = t;

        System.out.println("\nChecking Reservation:");

        System.out.println(reserveIfSupported(ref));

        // Polymorphism
        LibraryItem[] items = {t, m};

        System.out.println("\nProcessing Checkouts:");

        processCheckouts(items);
    }
}