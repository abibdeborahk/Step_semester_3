
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class AddTwoNumbers {

    static Node addNumbers(Node a, Node b) {

        Node dummy = new Node(0);
        Node tail = dummy;

        int carry = 0;

        while (a != null || b != null || carry != 0) {

            int sum = carry;

            if (a != null) {
                sum += a.data;
                a = a.next;
            }

            if (b != null) {
                sum += b.data;
                b = b.next;
            }

            int digit = sum % 10;
            carry = sum / 10;

            tail.next = new Node(digit);
            tail = tail.next;
        }

        return dummy.next;
    }

    static void display(Node head) {

        while (head != null) {
            System.out.print(head.data);

            if (head.next != null) {
                System.out.print(" -> ");
            }

            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // 342
        Node a = new Node(2);
        a.next = new Node(4);
        a.next.next = new Node(3);

        // 465
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(4);

        Node result = addNumbers(a, b);

        display(result);

        // 99 + 1
        Node c = new Node(9);
        c.next = new Node(9);

        Node d = new Node(1);

        Node result2 = addNumbers(c, d);

        display(result2);
    }
}
