class Employee {

    private int empId;
    private String empName;
    private double salary;

    // Constructor
    public Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    // Return salary
    public double getSalary() {
        return salary;
    }
}


// ManagerEmployee inherits from Employee
class ManagerEmployee extends Employee {

    private double teamBonus;

    // Constructor
    public ManagerEmployee(
            int empId,
            String empName,
            double salary,
            double teamBonus) {

        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    // Calculate effective salary
    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}


// InternEmployee inherits from Employee
class InternEmployee extends Employee {

    private double stipendCap;

    // Constructor
    public InternEmployee(
            int empId,
            String empName,
            double salary,
            double stipendCap) {

        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    // Calculate effective salary
    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}


// Main class
public class EmployeeManagementSystem {

    public static void main(String[] args) {

        Employee plainEmployee =
            new Employee(
                101,
                "Aditi",
                40000
            );

        ManagerEmployee managerEmployee =
            new ManagerEmployee(
                102,
                "Rohan",
                70000,
                8000
            );

        InternEmployee internEmployee =
            new InternEmployee(
                103,
                "Meera",
                12000,
                10000
            );

        // Store all employees in one array
        Employee[] employees = {
            plainEmployee,
            managerEmployee,
            internEmployee
        };

        // Use instanceof to identify employee type
        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {

                ManagerEmployee manager =
                    (ManagerEmployee) employee;

                System.out.println(
                    "Manager effective pay: Rs "
                    + manager.effectiveSalary()
                );

            } else if (employee instanceof InternEmployee) {

                InternEmployee intern =
                    (InternEmployee) employee;

                System.out.println(
                    "Intern effective pay: Rs "
                    + intern.effectiveSalary()
                );

            } else {

                System.out.println(
                    "Plain employee pay: Rs "
                    + employee.getSalary()
                );
            }
        }
    }
}