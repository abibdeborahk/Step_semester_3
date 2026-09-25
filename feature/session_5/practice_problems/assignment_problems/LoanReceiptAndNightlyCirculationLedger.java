final class LoanReceipt {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.out.println("LoanReceipt system initialized");
    }

    public LoanReceipt(String memberId, String[] bookIds) {

        if (bookIds == null || bookIds.length > 20) {
            throw new IllegalArgumentException("Invalid book IDs");
        }

        for (String id : bookIds) {

            if (!isValidBookId(id)) {
                throw new IllegalArgumentException(
                    "Invalid book ID: " + id
                );
            }
        }

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }

    private static boolean isValidBookId(String id) {

        if (id == null || id.length() != 6) {
            return false;
        }

        if (!id.startsWith("BK-")) {
            return false;
        }

        for (int i = 3; i < 6; i++) {

            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public String[] getBookIds() {

        // Defensive copy
        return bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(
            int index,
            String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException();
        }

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException(
                "Invalid book ID"
            );
        }

        String[] newBookIds = bookIds.clone();

        newBookIds[index] = newId;

        return new LoanReceipt(memberId, newBookIds);
    }
}

class ReferenceOnlyLoanReceipt extends LoanReceipt {

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

class CirculationProcessor {

    static String processNightlyCirculation(
            LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts == null) {
            return "0 processed | 0 null skipped | "
                    + "0 reference-only | 0 regular";
        }

        for (LoanReceipt receipt : receipts) {

            if (receipt == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipt instanceof ReferenceOnlyLoanReceipt) {
                referenceOnly++;
            }
            else {
                regular++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + referenceOnly + " reference-only | "
                + regular + " regular";
    }
}

public class LoanReceiptAndNightlyCirculationLedger {

    public static void main(String[] args) {

        try {

            LoanReceipt r =
                new LoanReceipt(
                    "LIB-8841",
                    new String[]{"BK-100", "bad"}
                );

        }
        catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        LoanReceipt r =
            new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"}
            );

        String[] ids = r.getBookIds();

        ids[0] = "HACKED";

        System.out.println(r.getBookIds()[0]);

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
            CirculationProcessor.processNightlyCirculation(receipts)
        );
    }
}