
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class CancelKthLastOrder {

    static Node removeKthFromEnd(Node head, int k) {

        // Dummy node points to the original head
        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        // Move fast k+1 steps ahead
        for (int i = 0; i <= k; i++) {
            fast = fast.next;
        }

        // Move both pointers together
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete the required node
        slow.next = slow.next.next;

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

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        int k = 2;

        head = removeKthFromEnd(head, k);

        System.out.println("After cancellation:");
        display(head);
    }
}
