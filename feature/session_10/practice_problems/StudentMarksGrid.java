
public class StudentMarksGrid {
    public static void main(String[] args) {

        String[] students = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};

        int[][] marks = {
            {80, 90, 70},
            {60, 85, 0},
            {95, 75, 88}
        };

        // Calculate total marks for each student
        System.out.println("Student Totals:");

        for (int i = 0; i < students.length; i++) {
            int total = 0;

            for (int j = 0; j < subjects.length; j++) {
                total += marks[i][j];
            }

            System.out.println(students[i] + " " + total);
        }

        // Find topper in each subject
        System.out.println("\nSubject Toppers:");

        for (int j = 0; j < subjects.length; j++) {
            int max = -1;
            int topperIndex = -1;

            for (int i = 0; i < students.length; i++) {
                if (marks[i][j] > max) {
                    max = marks[i][j];
                    topperIndex = i;
                }
            }

            System.out.println(
                subjects[j] + " " +
                students[topperIndex] + " " + max
            );
        }
    }
}
