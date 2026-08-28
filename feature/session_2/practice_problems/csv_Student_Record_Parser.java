public class csv_Student_Record_Parser {
public static void main(String[] args) {
parseStudentRecord("Ananya Verma,RA2211003010123,CSE");
    }
static void parseStudentRecord(String csvLine) {
    String[] details = csvLine.split(",");
    if (details.length != 3) {
            System.out.println("Invalid Record");
        }
        else {
            System.out.println("Name: " + details[0] +
                               " | Roll No: " + details[1] +
                               " | Dept: " + details[2]);
        }
    }
}