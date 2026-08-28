class FeeAccount {

    protected String regNo;
    protected double totalFee;
    protected double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {

        if (amount > 0 && amount <= totalFee - amountPaid) {
            amountPaid += amount;
            System.out.println("Payment successful: Rs " + amount);
        } 
        else {
            System.out.println("Payment rejected: Rs " + amount);
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    double hostelFee;

    HostelFeeAccount(String regNo, double totalFee, double hostelFee) {
        super(regNo, totalFee);
        this.hostelFee = hostelFee;
    }
}

class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {

        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        if (rooms == null) {
            return null;
        }

        for (HostelRoom room : rooms) {

            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
        } 
        else {
            System.out.println("No rooms available for " + studentName);
        }
    }
}

class SrmStudent {

    String name;
    String regNo;

    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo, double totalFee, double hostelFee) {

        this.name = name;
        this.regNo = regNo;


        this.feeAccount =
            new HostelFeeAccount(regNo, totalFee, hostelFee);

        this.room = null;

        totalStudents++;
    }

    String fullStatus() {

        String roomNumber;

        if (room != null) {
            roomNumber = room.roomNo;
        } 
        else {
            roomNumber = "unallotted";
        }

        return name + " | Due: Rs " + feeAccount.getDue()
                + " | Room: " + roomNumber;
    }
}



public class SrmStudentSystem {

    public static void main(String[] args) {

        // --------------------------------------------------
        // Create rooms
        // --------------------------------------------------

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };


        
        SrmStudent ravi =
            new SrmStudent("Ravi", "SRM001", 150000, 10000);

        SrmStudent anitha =
            new SrmStudent("Anitha", "SRM002", 190000, 10000);

        SrmStudent karthik =
            new SrmStudent("Karthik", "SRM003", 200000, 10000);


        HostelRoom.safeAllot(rooms, ravi.name);
        ravi.room = rooms[0];

        HostelRoom.safeAllot(rooms, anitha.name);
        anitha.room = rooms[1];

        ravi.feeAccount.pay(10000);       

        anitha.feeAccount.pay(10000);     

        karthik.feeAccount.pay(-5000);   


        System.out.println();
        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());


        System.out.println("Total students: "
                + SrmStudent.totalStudents);
    }
}