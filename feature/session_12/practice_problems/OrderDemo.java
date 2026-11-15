
import java.util.Scanner;

class Order {

    private LineItem[] items;
    private int count;

    // Static Nested Class
    static class LineItem {
        String productName;
        int quantity;
        double unitPrice;

        LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double total() {
            return quantity * unitPrice;
        }
    }

    // Constructor
    Order(int size) {
        items = new LineItem[size];
        count = 0;
    }

    // Add item
    void addItem(String name, int quantity, double price) {
        items[count++] = new LineItem(name, quantity, price);
    }

    // Member Inner Class
    class Cursor {
        int position = 0;

        boolean hasNext() {
            return position < count;
        }

        LineItem next() {
            return items[position++];
        }

        void reset() {
            position = 0;
        }
    }

    void display() {
        Cursor cursor = new Cursor();
        double orderTotal = 0;

        while (cursor.hasNext()) {
            LineItem item = cursor.next();

            System.out.println(item.productName + " " + item.total());

            orderTotal += item.total();
        }

        System.out.println("Total " + orderTotal);
    }
}

public class OrderDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Order order = new Order(n);

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int quantity = sc.nextInt();
            double price = sc.nextDouble();

            order.addItem(name, quantity, price);
        }

        order.display();

        System.out.println("cursor: inner (reads the order's array)");
        System.out.println("line item: static nested (needs no order)");
        
        sc.close();
    }
}
