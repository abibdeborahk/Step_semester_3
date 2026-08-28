class FeeAccount {
    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee, double amountPaid) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = amountPaid;
    }

    void pay(double amount) {

        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Payment must be positive.");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}


class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
        super(regNo, totalFee, amountPaid);
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }
}


class ScholarshipFeeAccount extends FeeAccount {

    private double scholarshipPercent;

    ScholarshipFeeAccount(String regNo, double totalFee,
                          double amountPaid, double scholarshipPercent) {

        super(regNo, totalFee, amountPaid);

        if (scholarshipPercent >= 0 && scholarshipPercent <= 100) {
            this.scholarshipPercent = scholarshipPercent;
        } else {
            this.scholarshipPercent = 0;
        }
    }

    double effectiveDue() {

        double due = getDue();

        return due - due * (scholarshipPercent / 100);
    }
}


public class extending_FeeAccount {

    public static void main(String[] args) {

        FeeAccount plain = new FeeAccount(
            "RA101", 150000, 0
        );

        HostelFeeAccount hostel = new HostelFeeAccount(
            "RA102", 200000, 0
        );

        ScholarshipFeeAccount scholarship =
            new ScholarshipFeeAccount(
                "RA103", 180000, 0, 20
            );


        FeeAccount[] accounts = {
            plain,
            hostel,
            scholarship
        };

        for (FeeAccount account : accounts) {

            if (account instanceof HostelFeeAccount) {

                ((HostelFeeAccount) account)
                    .payInTwoInstallments(140000);

            }

            else if (account instanceof ScholarshipFeeAccount) {

                System.out.println(
                    "Scholarship applied: 20%"
                );

            }

            else {

                account.pay(150000);
            }
        }

        System.out.println(
            "Plain account due: Rs " + plain.getDue()
        );

        System.out.println(
            "Hostel account due: Rs " + hostel.getDue()
        );

        System.out.println(
            "Scholarship account effective due: Rs "
            + scholarship.effectiveDue()
        );
    }
}