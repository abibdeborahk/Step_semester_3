class EventTicket {

    protected double basePrice;
    protected double amountPaid;

    private double[] lateFeeHistory;
    private int feeCount;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
        this.amountPaid = 0;

        lateFeeHistory = new double[10];
        feeCount = 0;
    }


    public void pay(double amount) {
        amountPaid += amount;
    }


    public double getBalanceDue() {
        return basePrice - amountPaid;
    }


    protected void applyLateFee(double amount) {

        basePrice += amount;

        lateFeeHistory[feeCount] = amount;
        feeCount++;
    }


    public double[] getLateFeeHistory() {

        double[] copy = new double[feeCount];

        for (int i = 0; i < feeCount; i++) {
            copy[i] = lateFeeHistory[i];
        }

        return copy;
    }
}


class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }


    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}


public class LateRegistrationPenalty {

    public static void main(String[] args) {

        WorkshopTicket w =
            new WorkshopTicket(1200);

        w.pay(1200);

        w.applyLateFee(100);

        System.out.println(
            w.getBalanceDue()
        );


        double[] history =
            w.getLateFeeHistory();

        System.out.println(
            history[0]
        );

        history[0] = 999;

        double[] newHistory =
            w.getLateFeeHistory();

        System.out.println(
            newHistory[0]
        );
    }
}