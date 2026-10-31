
class Node {
    int coefficient;
    int exponent;
    Node next;

    Node(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        next = null;
    }
}

public class PolynomialCalculator {

    static Node addPolynomials(Node p, Node q) {

        Node dummy = new Node(0, 0);
        Node tail = dummy;

        while (p != null && q != null) {

            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent);
                p = p.next;
            }

            else if (p.exponent < q.exponent) {
                tail.next = new Node(q.coefficient, q.exponent);
                q = q.next;
            }

            else {
                // Same exponent: add coefficients
                int sum = p.coefficient + q.coefficient;

                // Ignore zero coefficient terms
                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                }

                p = p.next;
                q = q.next;
            }

            if (tail.next != null) {
                tail = tail.next;
            }
        }

        // Copy remaining terms of first polynomial
        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent);
            tail = tail.next;
            p = p.next;
        }

        // Copy remaining terms of second polynomial
        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent);
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    // Display polynomial in standard form
    static void display(Node head) {

        Node temp = head;
        boolean first = true;

        while (temp != null) {

            if (!first && temp.coefficient > 0) {
                System.out.print(" + ");
            } else if (!first && temp.coefficient < 0) {
                System.out.print(" - ");
            } else if (first && temp.coefficient < 0) {
                System.out.print("-");
            }

            int absCoeff = Math.abs(temp.coefficient);

            if (temp.exponent == 0) {
                System.out.print(absCoeff);
            }
            else if (temp.exponent == 1) {
                if (absCoeff != 1)
                    System.out.print(absCoeff);
                System.out.print("x");
            }
            else {
                if (absCoeff != 1)
                    System.out.print(absCoeff);
                System.out.print("x^" + temp.exponent);
            }

            first = false;
            temp = temp.next;
        }

        if (first) {
            System.out.print("0");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // P = 5x^3 + 4x^2 + 2
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        // Q = 5x^2 - 3x + 1
        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        Node result = addPolynomials(p, q);

        System.out.print("Result: ");
        display(result);
    }
}
