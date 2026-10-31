
import java.util.*;

public class QuizScoringFormula {

    public static int evaluate(String expr) {

        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {

            char ch = expr.charAt(i);

            // Ignore spaces
            if (ch == ' ') {
                continue;
            }

            // Read multi-digit numbers
            if (Character.isDigit(ch)) {

                int num = 0;

                while (i < expr.length() &&
                       Character.isDigit(expr.charAt(i))) {

                    num = num * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                i--;
                operands.push(num);
            }

            // Opening bracket
            else if (ch == '(') {
                operators.push(ch);
            }

            // Closing bracket
            else if (ch == ')') {

                while (operators.peek() != '(') {
                    calculate(operands, operators);
                }

                operators.pop();
            }

            // Operators: +, -, *, /
            else {

                while (!operators.isEmpty() &&
                       operators.peek() != '(' &&
                       precedence(operators.peek()) >= precedence(ch)) {

                    calculate(operands, operators);
                }

                operators.push(ch);
            }
        }

        // Evaluate remaining operators
        while (!operators.isEmpty()) {
            calculate(operands, operators);
        }

        return operands.pop();
    }

    // Operator precedence
    public static int precedence(char op) {

        if (op == '*' || op == '/') {
            return 2;
        }

        if (op == '+' || op == '-') {
            return 1;
        }

        return 0;
    }

    // Perform calculation
    public static void calculate(Stack<Integer> operands,
                                 Stack<Character> operators) {

        int b = operands.pop();
        int a = operands.pop();

        char op = operators.pop();

        int result = 0;

        switch (op) {

            case '+':
                result = a + b;
                break;

            case '-':
                result = a - b;
                break;

            case '*':
                result = a * b;
                break;

            case '/':
                result = a / b;
                break;
        }

        operands.push(result);
    }

    public static void main(String[] args) {

        String expr = "2 * (3 + 4) - 10 / 5";

        System.out.println(evaluate(expr));
    }
}
