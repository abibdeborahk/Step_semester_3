class Employee {

    private int empId;
    private String empName;
    private double salary;

    // Constructor
    public Employee(
            int empId,
            String empName,
            double salary) {

        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}


class ManagerEmployee extends Employee {

    private double teamBonus;

    public ManagerEmployee(
            int empId,
            String empName,
            double salary,
            double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    // Manager's effective salary
    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


// InternEmployee inherits from Employee
class InternEmployee extends Employee {

    private double stipendCap;

    public InternEmployee(
            int empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    // Intern's effective salary
    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}


// ParkingSlot class
class ParkingSlot {

    private String slotNo;
    private int capacity;
    private int occupiedCount;

    public ParkingSlot(
            String slotNo,
            int capacity,
            int occupiedCount) {

        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Allot a vehicle
    public void allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                vehicleNo
                + " allotted to slot "
                + slotNo
            );
        }
    }

    // Find an available slot
    public static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    // Safely allot a vehicle
    public static ParkingSlot safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot availableSlot =
            findAvailableSlot(slots);

        if (availableSlot != null) {

            availableSlot.allot(vehicleNo);

            return availableSlot;

        } else {

            System.out.println(
                "No slots available for "
                + vehicleNo
            );

            return null;
        }
    }

    public String getSlotNo() {
        return slotNo;
    }
}

class CompanyEmployeeRecord {

    private String name;
    private String empId;

    private Employee employee;
    private ParkingSlot slot;

    // Static counter shared by all records
    public static int totalRecords = 0;

    // Constructor
    public CompanyEmployeeRecord(
            String name,
            String empId,
            Employee employee,
            ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    // Generate complete employee profile
    public String fullProfile() {

        double effectivePay;

        // Determine effective salary
        if (employee instanceof ManagerEmployee) {

            ManagerEmployee manager =
                (ManagerEmployee) employee;

            effectivePay =
                manager.effectiveSalary();

        } else if (employee instanceof InternEmployee) {

            InternEmployee intern =
                (InternEmployee) employee;

            effectivePay =
                intern.effectiveSalary();

        } else {

            effectivePay =
                employee.getSalary();
        }


        String parkingInformation;

        // Null-safe parking check
        if (slot != null) {

            parkingInformation =
                slot.getSlotNo();

        } else {

            parkingInformation =
                "no parking assigned";
        }


        return name
            + " | Pay: Rs "
            + effectivePay
            + " | Slot: "
            + parkingInformation;
    }
}


// Main class
public class HRParkingMiniSystem {

    public static void main(String[] args) {

        ManagerEmployee manager =
            new ManagerEmployee(
                101,
                "Divya",
                70000,
                8000
            );

        Employee employee =
            new Employee(
                102,
                "Karan",
                40000
            );

        InternEmployee intern =
            new InternEmployee(
                103,
                "Meera",
                12000,
                10000
            );

        ParkingSlot[] parkingSlots = {

            new ParkingSlot(
                "A1",
                1,
                0
            ),

            new ParkingSlot(
                "A2",
                1,
                0
            )
        };

        ParkingSlot divyaSlot =
            ParkingSlot.safeAllot(
                parkingSlots,
                "DIVYA-CAR"
            );

        ParkingSlot karanSlot =
            ParkingSlot.safeAllot(
                parkingSlots,
                "KARAN-CAR"
            );

        // Meera intentionally has no parking
        ParkingSlot meeraSlot = null;

        CompanyEmployeeRecord divyaRecord =
            new CompanyEmployeeRecord(
                "Divya",
                "101",
                manager,
                divyaSlot
            );

        CompanyEmployeeRecord karanRecord =
            new CompanyEmployeeRecord(
                "Karan",
                "102",
                employee,
                karanSlot
            );

        CompanyEmployeeRecord meeraRecord =
            new CompanyEmployeeRecord(
                "Meera",
                "103",
                intern,
                meeraSlot
            );

        System.out.println(
            divyaRecord.fullProfile()
        );

        System.out.println(
            karanRecord.fullProfile()
        );

        System.out.println(
            meeraRecord.fullProfile()
        );


        System.out.println(
            "Total records: "
            + CompanyEmployeeRecord.totalRecords
        );
    }
}