class BrokenLibraryMember {

    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(
            String name,
            String memberId,
            int booksIssued) {

        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}


class LibraryMember {


    private String name;
    private String memberId;
    private int booksIssued;

    private static String libraryName = "City Library";
    private static int memberCount = 0;

    // Constructor
    public LibraryMember(
            String name,
            int booksIssued) {

        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;

        // Automatically generate member ID
        this.memberId =
            "LM-" + (1000 + memberCount);
    }

    // Print individual member card
    public void printMemberCard() {

        System.out.println(
            name + " | " + memberId
        );
    }

    // Print total number of members
    public static void printTotalMembers() {

        System.out.println(
            "Total members: "
            + memberCount
        );
    }
}


// Main class
public class LibraryMembershipSystem {

    public static void main(String[] args) {

        // -----------------------------------
        // BROKEN VERSION
        // -----------------------------------

        System.out.println("Broken version:");

        BrokenLibraryMember firstMember =
            new BrokenLibraryMember(
                "Aditi",
                "LM-1001",
                2
            );

        BrokenLibraryMember secondMember =
            new BrokenLibraryMember(
                "Rohan",
                "LM-1002",
                3
            );

        System.out.println(firstMember.name);
        System.out.println(secondMember.name);


        System.out.println();
        System.out.println("Fixed version:");

        LibraryMember firstLibraryMember =
            new LibraryMember(
                "Aditi",
                2
            );

        LibraryMember secondLibraryMember =
            new LibraryMember(
                "Rohan",
                3
            );

        firstLibraryMember.printMemberCard();
        secondLibraryMember.printMemberCard();

        LibraryMember.printTotalMembers();
    }
}