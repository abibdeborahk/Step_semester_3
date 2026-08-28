class SrmStudent {
    String name;
    String regno;
    double attendance;

    SrmStudent(String name, String regno, double attendance) {
        this.name = name;
        this.regno = regno;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance>=75;
    }

    static double classAverage(SrmStudent[] students) {
        int total = 0;
        for(SrmStudent student : students) {
            total += student.attendance;
        }
        return (double)total/students.length;
    }

public class attendance_System {
    public static void main (String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA101", 82),
            new SrmStudent("Anitha", "RA102", 68),
            new SrmStudent("Karthik", "RA103", 91),
            new SrmStudent("Meera", "RA104", 74),
            new SrmStudent("Suresh", "RA105", 60)
        };
        String status;
        for(SrmStudent student : students) {
            if(student.isEligible()) {
                status = "Eligible";
            }
            else {
                status = "Detained";
            }
            System.out.println(
                student.name + " - " +
                student.attendance + "% - " +
                status);
        }

        double Average = SrmStudent.classAverage(students);
        System.out.println("Class average: "+ Average +"%");
    }
}
}