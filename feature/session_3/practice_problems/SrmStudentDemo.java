// =========================================================
// BROKEN VERSION
// =========================================================

class BrokenSrmStudent {

    static String name;
    static String regNo;
    static int attendance;

   
    BrokenSrmStudent(String name, String regNo, int attendance) {
        BrokenSrmStudent.name = name;
        BrokenSrmStudent.regNo = regNo;
        BrokenSrmStudent.attendance = attendance;
    }
}


class SrmStudent {

    // Instance fields - unique for every student
    String name;
    String regNo;
    int attendance;

    // Static fields - shared by every student
    static String university = "SRM University";
    static int admissionCount = 0;

    // Constructor
    SrmStudent(String name, int attendance) {

        this.name = name;
        this.attendance = attendance;

        admissionCount++;

        // Automatically generate registration number
        this.regNo = "SRM" + admissionCount;
    }

    // Instance method
    void printIdCard() {

        System.out.println("University: " + university);
        System.out.println("Name: " + name);
        System.out.println("Registration No: " + regNo);
        System.out.println("Attendance: " + attendance);
        System.out.println();
    }

    // Static method
    static void printTotalAdmissions() {
        System.out.println("Total Admissions: " + admissionCount);
    }
}


// =========================================================
// MAIN CLASS
// =========================================================

public class SrmStudentDemo {

    public static void main(String[] args) {

        // -----------------------------------------------------
        // BROKEN VERSION
        // -----------------------------------------------------

        System.out.println("===== BROKEN VERSION =====");

        BrokenSrmStudent ravi =
            new BrokenSrmStudent("Ravi", "SRM001", 85);

        BrokenSrmStudent meera =
            new BrokenSrmStudent("Meera", "SRM002", 90);

        // Both objects access the same static variable
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);

        System.out.println(
            "(Ravi's data was overwritten - both students now show \"Meera\")"
        );


        // -----------------------------------------------------
        // CORRECTED VERSION
        // -----------------------------------------------------

        System.out.println("\n===== CORRECTED VERSION =====");

        SrmStudent student1 = new SrmStudent("Ravi", 85);
        SrmStudent student2 = new SrmStudent("Meera", 90);

        System.out.println("Ravi's ID Card:");
        student1.printIdCard();

        System.out.println("Meera's ID Card:");
        student2.printIdCard();

        SrmStudent.printTotalAdmissions();
    }
}