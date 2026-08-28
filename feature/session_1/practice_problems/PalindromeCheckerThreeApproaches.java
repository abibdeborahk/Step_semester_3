public class PalindromeCheckerThreeApproaches {

    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean isPalindromeRecursive(String text) {
        return isPalindromeRecursive(text, 0, text.length() - 1);
    }

    private static boolean isPalindromeRecursive(String text, int left, int right) {
        if (left >= right) {
            return true;
        }

        if (text.charAt(left) != text.charAt(right)) {
            return false;
        }

        return isPalindromeRecursive(text, left + 1, right - 1);
    }

    public static boolean isPalindromeArrayReversal(String text) {
        char[] characters = text.toCharArray();

        int left = 0;
        int right = characters.length - 1;

        while (left < right) {
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;

            left++;
            right--;
        }

        String reversedText = new String(characters);
        return text.equals(reversedText);
    }

    public static String formatResult(boolean result) {
        return result ? "Palindrome" : "Not Palindrome";
    }

    public static void main(String[] args) {
        String text = "madam";

        boolean iterativeResult = isPalindromeIterative(text);
        boolean recursiveResult = isPalindromeRecursive(text);
        boolean arrayResult = isPalindromeArrayReversal(text);

        System.out.println(
            "Iterative: " + formatResult(iterativeResult)
            + " | Recursive: " + formatResult(recursiveResult)
            + " | Array Reversal: " + formatResult(arrayResult)
        );

        if (iterativeResult == recursiveResult && recursiveResult == arrayResult) {
            System.out.println("All three approaches agree.");
        } else {
            System.out.println("The three approaches do not agree.");
        }
    }
}
