import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Employee {

    String name;

    public Employee(String name) {
        this.name = name;
    }

    public abstract int getAllowedLeaveDays();
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public int getAllowedLeaveDays() {
        return 30;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public int getAllowedLeaveDays() {
        return 15;
    }
}

class ContractEmployee extends Employee {

    public ContractEmployee(String name) {
        super(name);
    }

    @Override
    public int getAllowedLeaveDays() {
        return 10;
    }
}

class LeaveRequest {

    Employee employee;
    LocalDate startDate;
    LocalDate endDate;

    private String status = "Pending";

    public LeaveRequest(Employee employee,
                        LocalDate startDate,
                        LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {

        if (!status.equals("Pending")) {

            System.out.println("Cannot change status: "
                    + status + " request cannot revert to Pending "
                    + "or be reviewed again.");

            return;
        }

        if (!newStatus.equals("Approved")
                && !newStatus.equals("Rejected")) {

            System.out.println("Invalid status.");
            return;
        }

        status = newStatus;

        System.out.println("Leave request for "
                + employee.name + " " + status.toLowerCase() + ".");

        System.out.println("Status: " + status);
    }
}

class LeaveManager {

    public LeaveRequest submitRequest(Employee employee,
                                      LocalDate start,
                                      LocalDate end) {

        if (!start.isBefore(end)) {
            System.out.println("Invalid leave dates.");
            return null;
        }

        int days = (int) ChronoUnit.DAYS.between(start, end);

        if (days > employee.getAllowedLeaveDays()) {

            System.out.println("Leave request rejected: "
                    + "Exceeds allowed leave days.");

            return null;
        }

        LeaveRequest request =
                new LeaveRequest(employee, start, end);

        System.out.println("Leave request submitted by "
                + employee.name + " for "
                + start + " to " + end + ".");

        System.out.println("Status: " + request.getStatus());

        return request;
    }

    public void reviewRequest(LeaveRequest request,
                              String decision) {

        if (request == null) {
            return;
        }

        request.updateStatus(decision);
    }
}

public class EmployeeLeaveManagement {

    public static void main(String[] args) {

        // Create employees
        Employee e1 = new FullTimeEmployee("John Doe");

        Employee e2 = new PartTimeEmployee("Jane Smith");

        LeaveManager manager = new LeaveManager();

        // John submits leave request
        LeaveRequest r1 = manager.submitRequest(
                e1,
                LocalDate.of(2024, 10, 10),
                LocalDate.of(2024, 10, 12)
        );

        // Manager approves
        manager.reviewRequest(r1, "Approved");

        // Jane submits leave request
        LeaveRequest r2 = manager.submitRequest(
                e2,
                LocalDate.of(2024, 11, 1),
                LocalDate.of(2024, 11, 5)
        );

        // Try changing approved request back to pending
        manager.reviewRequest(r1, "Pending");
    }
}