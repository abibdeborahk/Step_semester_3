
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class MergeTicketCounters {

    static Node mergeSorted(Node a, Node b) {

        // Dummy node
        Node dummy = new Node(0);
        Node tail = dummy;

        // Compare both lists
        while (a != null && b != null) {

            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }

            tail = tail.next;
        }

        // Attach remaining nodes
        if (a != null) {
            tail.next = a;
        } else {
            tail.next = b;
        }

        return dummy.next;
    }

    static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        // First list: 3 -> 8 -> 15
        Node a = new Node(3);
        a.next = new Node(8);
        a.next.next = new Node(15);

        // Second list: 5 -> 8 -> 12 -> 20
        Node b = new Node(5);
        b.next = new Node(8);
        b.next.next = new Node(12);
        b.next.next.next = new Node(20);

        Node result = mergeSorted(a, b);

        System.out.println("Merged ticket list:");
        display(result);
    }
}
