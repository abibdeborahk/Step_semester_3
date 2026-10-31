
import java.util.Arrays;

class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static String ledgerStatus;

    // One-time shared initialization
    static {
        ledgerStatus = "Nightly Circulation";
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        if (bookIds == null) {
            throw new IllegalArgumentException(
                "Book IDs cannot be null"
            );
        }

        for (String id : bookIds) {

            if (id == null || !id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException(
                    "Invalid book ID: " + id
                );
            }
        }

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = Arrays.copyOf(
            bookIds, bookIds.length
        );
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {

        // Defensive copy
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(
        int index, String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException();
        }

        if (newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException(
                "Invalid book ID"
            );
        }

        String[] updated = getBookIds();

        updated[index] = newId;

        return new LoanReceipt(memberId, updated);
    }
}

// Specialized reference-only receipt
final class ReferenceOnlyLoanReceipt extends LoanReceipt {

    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(
        String memberId,
        String[] bookIds,
        String roomNumber) {

        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

public class LoanReceiptDemo {

    static String processNightlyCirculation(
        LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped"
                 + " | 0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            } else {
                regular++;
            }
        }

        return processed + " processed | "
             + nullSkipped + " null skipped | "
             + referenceOnly + " reference-only | "
             + regular + " regular";
    }

    public static void main(String[] args) {

        LoanReceipt[] receipts = {

            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"
            ),

            null,

            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"}
            )
        };

        System.out.println(
            processNightlyCirculation(receipts)
        );

        // Defensive-copy test
        LoanReceipt r = new LoanReceipt(
            "LIB-8841",
            new String[]{"BK-100", "BK-101"}
        );

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

        // Wither method test
        LoanReceipt corrected =
            r.withCorrectedBookId(0, "BK-999");

        System.out.println(corrected.getBookIds()[0]);
        System.out.println(r.getBookIds()[0]);
    }
}
