class BookIssue {

    private String title;
    private String borrowerName;
    private int daysOverdue;

    // Constructor
    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Calculate fine for one book
    public double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        }

        return 0;
    }

    // Check whether the book is severely overdue
    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    public static double totalFineCollected(BookIssue[] issues) {

        double totalFine = 0;

        for (BookIssue issue : issues) {
            totalFine += issue.fineAmount();
        }

        return totalFine;
    }

    public String getTitle() {
        return title;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }
}


// Main class
public class LibraryFineSystem {

    public static void main(String[] args) {

        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aditi", 18),
            new BookIssue("Effective Java", "Rohan", 5),
            new BookIssue("Refactoring", "Meera", 0),
            new BookIssue("DSA Handbook", "Karan", 21),
            new BookIssue("Design Patterns", "Divya", 9)
        };

        // Display book details
        for (BookIssue issue : issues) {

            String status;

            if (issue.isSeverelyOverdue()) {
                status = "Severely overdue";
            } else {
                status = "OK";
            }

            System.out.println(
                issue.getTitle()
                + " - "
                + issue.getDaysOverdue()
                + " days - "
                + status
            );
        }


        double totalFine =
            BookIssue.totalFineCollected(issues);

        System.out.println(
            "Total fine collected: Rs " + totalFine
        );
    }
}