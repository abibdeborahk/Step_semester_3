public class TypingSpeedTestAccuracyChecker {

    public static void checkTypingAccuracy(
            String original,
            String typed) {

        int matchedCharacters = 0;
        int firstMismatchPosition = -1;

        for (int i = 0; i < original.length(); i++) {

            if (original.charAt(i) == typed.charAt(i)) {

                matchedCharacters++;

            } else if (firstMismatchPosition == -1) {

                firstMismatchPosition = i;
            }
        }

        double accuracy =
            ((double) matchedCharacters / original.length()) * 100;

        System.out.printf(
            "Matched: %d/%d | Accuracy: %.2f%%",
            matchedCharacters,
            original.length(),
            accuracy
        );

        if (firstMismatchPosition == -1) {

            System.out.println(" | No Mismatches");

        } else {

            char originalCharacter =
                original.charAt(firstMismatchPosition);

            char typedCharacter =
                typed.charAt(firstMismatchPosition);

            // Position is displayed starting from 1
            int displayPosition =
                firstMismatchPosition + 1;

            System.out.println(
                " | First Mismatch at position "
                + displayPosition
                + " ('"
                + originalCharacter
                + "' vs '"
                + typedCharacter
                + "')"
            );
        }
    }

    public static void main(String[] args) {

        String original = "hello world";
        String typed = "hello worlt";

        checkTypingAccuracy(original, typed);
    }
}