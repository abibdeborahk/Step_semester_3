
public class AccessChecker {

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

        if (fieldModifier.equals("default")) {
            return "DENIED";
        }

        // Remaining modifier is protected

        if (accessorContext.equals(
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String describeContext(String accessorContext) {

        String[] words = accessorContext.toLowerCase().split("_");

        String result = "";

        for (String word : words) {

            result += Character.toUpperCase(word.charAt(0))
                    + word.substring(1) + " ";
        }

        return result.trim();
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );

        System.out.println(
            describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );
    }
}
