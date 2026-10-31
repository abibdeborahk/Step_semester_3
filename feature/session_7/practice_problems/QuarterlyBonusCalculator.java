abstract class StaffMember {

    private double baseSalary;
    protected double bonusRate;

    // Constructor 1
    public StaffMember(double baseSalary) {
        this(baseSalary, 0.10);
    }

    // Constructor 2
    public StaffMember(double baseSalary, double bonusRate) {

        if (baseSalary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }

        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public double getSalary() {
        return baseSalary;
    }

    public void setSalary(double baseSalary) {

        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        } else {
            System.out.println("Invalid salary!");
        }
    }

    public abstract double calculateBonus();
}

interface Auditable {
    String auditRecord();
}

class TeamLead extends StaffMember implements Auditable {

    int teamSize;

    // Constructor 1
    public TeamLead(double baseSalary, int teamSize) {
        super(baseSalary);
        this.teamSize = teamSize;
    }

    // Constructor 2
    public TeamLead(double baseSalary, double bonusRate, int teamSize) {
        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {
        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {
        return "TeamLead audit: " + teamSize
                + " team members, salary $" + getSalary();
    }
}

public class QuarterlyBonusCalculator {

    static String getAuditIfApplicable(StaffMember s) {

        if (s instanceof Auditable) {

            Auditable a = (Auditable) s;

            return a.auditRecord();
        }

        return "No audit required";
    }

    public static void main(String[] args) {

        TeamLead t = new TeamLead(60000, 5);

        System.out.println("Bonus: " + t.calculateBonus());

        TeamLead t2 = new TeamLead(60000, 0.20, 5);

        System.out.println("Bonus: " + t2.calculateBonus());

        // Testing setter
        t.setSalary(-5000);

        System.out.println("Salary: " + t.getSalary());

        // Upcasting
        StaffMember ref = t;

        System.out.println(getAuditIfApplicable(ref));
    }
}