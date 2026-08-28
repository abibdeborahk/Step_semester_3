public class FirstNonRepeatingCharacterFinder {

    public static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[Character.MAX_VALUE + 1];

        // Count the frequency of every character.
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        // Scan from left to right to find the first character
        // whose frequency is exactly 1.
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0';
    }

    public static void main(String[] args) {
        String text = "swiss";

        char result = findFirstNonRepeatingChar(text);

        if (result == '\0') {
            System.out.println("No Non-Repeating Character Found");
        } else {
            System.out.println(
                "First Non-Repeating Character: '" + result + "'"
            );
        }
    }
}
