class AccessRuleEngine {

    public static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        // PRIVATE
        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // DEFAULT
        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // PROTECTED
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        // PUBLIC
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static String describeContext(
            String accessorContext) {

        String[] words =
            accessorContext.toLowerCase().split("_");

        String result = "";

        for (int i = 0; i < words.length; i++) {

            String word = words[i];

            result += Character.toUpperCase(word.charAt(0))
                    + word.substring(1)
                    + " ";
        }

        return result.trim();
    }
}


public class CrossPackageInheritance {

    public static void main(String[] args) {

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(
            AccessRuleEngine.classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));

        System.out.println(
            AccessRuleEngine.describeContext(
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}