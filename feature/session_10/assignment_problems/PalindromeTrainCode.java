
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        next = null;
    }
}

public class PalindromeTrainCode {

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

    // Check whether linked list is palindrome
    static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find the middle
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half
        Node secondHalf = reverse(slow.next);

        // Connect the reversed half
        slow.next = secondHalf;

        // Step 3: Compare both halves
        Node first = head;
        Node second = secondHalf;

        boolean result = true;

        while (second != null) {
            if (first.data != second.data) {
                result = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Step 4: Restore the original list
        slow.next = reverse(secondHalf);

        return result;
    }

    // Display linked list
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

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);

        System.out.println("Original list:");
        display(head);

        boolean result = isPalindrome(head);

        System.out.println("Is Palindrome? " + result);

        System.out.println("List after checking:");
        display(head);
    }
}
