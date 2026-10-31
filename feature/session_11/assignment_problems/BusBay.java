
public class BusBay {

    int[] buses;
    int front;
    int size;
    int capacity;

    // Constructor
    public BusBay(int capacity) {
        this.capacity = capacity;
        buses = new int[capacity];
        front = 0;
        size = 0;
    }

    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if full
    public boolean isFull() {
        return size == capacity;
    }

    // Insert at front
    public boolean insertFront(int bus) {

        if (isFull()) {
            return false;
        }

        front = (front - 1 + capacity) % capacity;
        buses[front] = bus;
        size++;

        return true;
    }

    // Insert at rear
    public boolean insertLast(int bus) {

        if (isFull()) {
            return false;
        }

        int rear = (front + size) % capacity;

        buses[rear] = bus;
        size++;

        return true;
    }

    // Delete from front
    public boolean deleteFront() {

        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        size--;

        return true;
    }

    // Delete from rear
    public boolean deleteLast() {

        if (isEmpty()) {
            return false;
        }

        size--;

        return true;
    }

    // Get front element
    public int getFront() {

        if (isEmpty()) {
            return -1;
        }

        return buses[front];
    }

    // Get rear element
    public int getRear() {

        if (isEmpty()) {
            return -1;
        }

        int rear = (front + size - 1) % capacity;

        return buses[rear];
    }

    // Main method
    public static void main(String[] args) {

        BusBay b = new BusBay(3);

        System.out.println(b.insertLast(101));
        System.out.println(b.insertLast(102));
        System.out.println(b.insertFront(100));
        System.out.println(b.insertFront(99));

        System.out.println(b.getRear());
        System.out.println(b.isFull());

        System.out.println(b.deleteLast());

        System.out.println(b.insertFront(98));
        System.out.println(b.getFront());
    }
}
