
import java.util.*;

public class HackathonBannerPrinter {

    public static String expandBanner(String code) {

        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int num = 0;

        for (int i = 0; i < code.length(); i++) {

            char ch = code.charAt(i);

            // Read the repeat number
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            // Opening bracket
            else if (ch == '[') {
                countStack.push(num);
                stringStack.push(current);

                current = new StringBuilder();
                num = 0;
            }

            // Closing bracket
            else if (ch == ']') {

                int repeat = countStack.pop();
                StringBuilder previous = stringStack.pop();

                for (int j = 0; j < repeat; j++) {
                    previous.append(current);
                }

                current = previous;
            }

            // Ordinary character
            else {
                current.append(ch);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {

        String code = "2[x3[y]]z";

        System.out.println(expandBanner(code));
    }
}
