
class LibraryMember {
    private String membershipId;
    private String branchCode;
    private double finesOwed;
    private String displayName;

    public LibraryMember(String membershipId,
                         String branchCode,
                         double finesOwed,
                         String displayName) {

        String id = membershipId.trim();

        if (id.isEmpty() || id.length() < 4) {
            throw new IllegalArgumentException(
                "Invalid membership ID"
            );
        }

        this.membershipId = id;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class MembershipFieldReachChecker {

    static String classifyAccess(String fieldModifier,
                                  String accessorContext) {

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private", "default", "protected", "public"
        };

        String result = "";

        for (int i = 0; i < modifiers.length; i++) {

            int allowed = 0;
            int denied = 0;

            for (int j = 0; j < attempts.length; j++) {

                if (attempts[j][0].equals(modifiers[i])) {

                    String answer = classifyAccess(
                        attempts[j][0], attempts[j][1]
                    );

                    if (answer.equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (i > 0) {
                result += " | ";
            }

            result += modifiers[i] + ": "
                    + allowed + " allowed / "
                    + denied + " denied";
        }

        return result;
    }

    public static void main(String[] args) {

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        System.out.println(summarizeByModifier(attempts));

       
    }
}
