import java.util.ArrayList;

// Food Item
class FoodItem {

    String name;
    double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Restaurant
class Restaurant {

    String restaurantName;

    ArrayList<FoodItem> menu = new ArrayList<>();

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void addFoodItem(FoodItem item) {
        menu.add(item);
    }
}

// LineItem
class LineItem {

    FoodItem item;
    int quantity;

    public LineItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public double getTotal() {
        return item.price * quantity;
    }
}

// Payment Interface
interface IPaymentMethod {
    boolean pay(double amount);

    String getPaymentName();
}

// Credit Card Payment
class CreditCardPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Credit Card";
    }
}

// Digital Wallet Payment
class DigitalWalletPayment implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        return false;
    }

    @Override
    public String getPaymentName() {
        return "Digital Wallet";
    }
}

// Cash on Delivery
class CashOnDelivery implements IPaymentMethod {

    @Override
    public boolean pay(double amount) {
        return true;
    }

    @Override
    public String getPaymentName() {
        return "Cash on Delivery";
    }
}

// Notification Service
class NotificationService {

    public void notifyCustomer(String message) {
        System.out.println("Notification: " + message);
    }
}

// Customer
class Customer {

    String name;

    ArrayList<Order> orders = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public Order createOrder(Restaurant restaurant) {

        Order order = new Order(this, restaurant);

        orders.add(order);

        System.out.println("Order created.");

        return order;
    }
}

// Order
class Order {

    private static int counter = 122;

    int orderId;

    Customer customer;
    Restaurant restaurant;

    ArrayList<LineItem> items = new ArrayList<>();

    String status = "Created";

    NotificationService notification =
            new NotificationService();

    public Order(Customer customer, Restaurant restaurant) {

        counter++;

        orderId = counter;

        this.customer = customer;
        this.restaurant = restaurant;
    }

    public void addItem(FoodItem item, int quantity) {

        if (quantity <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        if (!restaurant.menu.contains(item)) {
            System.out.println("Item not available in restaurant.");
            return;
        }

        LineItem line = new LineItem(item, quantity);

        items.add(line);

        System.out.println("Added " + item.name
                + " (Qty " + quantity + ")");
    }

    public double calculateTotal() {

        double total = 0;

        for (LineItem item : items) {
            total += item.getTotal();
        }

        return total;
    }

    public void placeOrder(IPaymentMethod payment) {

        if (items.isEmpty()) {

            System.out.println(
                    "Cannot place order: Order must contain at least one item."
            );

            return;
        }

        System.out.println("Order placed successfully.");

        double total = calculateTotal();

        boolean success = payment.pay(total);

        if (success) {

            status = "Paid";

            System.out.println("Payment via "
                    + payment.getPaymentName()
                    + " successful.");

            System.out.println("Order status: " + status);

            notification.notifyCustomer(
                    "Order #" + orderId + " placed and paid."
            );

        } else {

            status = "Pending Payment";

            System.out.println("Payment via "
                    + payment.getPaymentName()
                    + " failed.");

            System.out.println("Order status: " + status);

            notification.notifyCustomer(
                    "Order #" + orderId
                    + " placed, awaiting payment."
            );
        }
    }
}

public class FoodOrderSystem {

    public static void main(String[] args) {

        // Create restaurant
        Restaurant restaurant =
                new Restaurant("Food Palace");

        // Add food items
        FoodItem pizza = new FoodItem("Pizza", 200);
        FoodItem soda = new FoodItem("Soda", 50);
        FoodItem burger = new FoodItem("Burger", 150);

        restaurant.addFoodItem(pizza);
        restaurant.addFoodItem(soda);
        restaurant.addFoodItem(burger);

        // Create customer
        Customer customer = new Customer("John");

        // First order
        Order order1 = customer.createOrder(restaurant);

        order1.addItem(pizza, 2);
        order1.addItem(soda, 1);

        // Attempt empty order
        Order emptyOrder = customer.createOrder(restaurant);

        emptyOrder.placeOrder(new CreditCardPayment());

        // Place first order using Credit Card
        order1.placeOrder(new CreditCardPayment());

        // Second order
        Order order2 = customer.createOrder(restaurant);

        order2.addItem(burger, 1);

        // Payment fails using Digital Wallet
        order2.placeOrder(new DigitalWalletPayment());
    }
}