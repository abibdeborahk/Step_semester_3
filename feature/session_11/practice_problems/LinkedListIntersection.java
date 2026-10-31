
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class LinkedListIntersection {

    static int getLength(Node head) {

        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    static Node findIntersection(Node a, Node b) {

        int lenA = getLength(a);
        int lenB = getLength(b);

        // Move the longer list ahead
        while (lenA > lenB) {
            a = a.next;
            lenA--;
        }

        while (lenB > lenA) {
            b = b.next;
            lenB--;
        }

        // Compare node references
        while (a != null && b != null) {

            if (a == b) {
                return a;
            }

            a = a.next;
            b = b.next;
        }

        return null;
    }

    public static void main(String[] args) {

        // Shared nodes
        Node common = new Node(8);
        common.next = new Node(4);
        common.next.next = new Node(5);

        // First list: 4 -> 1 -> 8 -> 4 -> 5
        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = common;

        // Second list: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = common;

        Node result = findIntersection(a, b);

        if (result != null) {
            System.out.println(
                "Intersection at node: " + result.data
            );
        } else {
            System.out.println("No intersection");
        }

        // Two lists without intersection
        Node c = new Node(2);
        c.next = new Node(6);
        c.next.next = new Node(4);

        Node d = new Node(1);
        d.next = new Node(5);

        Node result2 = findIntersection(c, d);

        System.out.println(result2);
    }
}
