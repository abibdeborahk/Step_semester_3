
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class ReorderLinkedList {

    // Reverse a linked list
    static Node reverse(Node head) {

        Node prev = null;
        Node current = head;

        while (current != null) {

            Node nextNode = current.next;

            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    static void reorderList(Node head) {

        if (head == null || head.next == null) {
            return;
        }

        Node slow = head;
        Node fast = head;

        // Step 1: Find middle
        while (fast.next != null &&
               fast.next.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Split and reverse second half
        Node secondHalf = reverse(slow.next);

        slow.next = null;

        Node first = head;
        Node second = secondHalf;

        // Step 3: Merge alternately
        while (second != null) {

            Node firstNext = first.next;
            Node secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }
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

        // Even-length list
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        reorderList(head);

        display(head);

        // Odd-length list
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

        reorderList(head2);

        display(head2);
    }
}
