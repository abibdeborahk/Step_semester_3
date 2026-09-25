class FoodOrder {

    private String studentName;
    private String dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {

        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    public void markDelivered() {

        if (delivered) {
            System.out.println("Order is already delivered.");
        } else {
            delivered = true;
            System.out.println("Order marked as delivered.");
        }
    }

    public static void processBatch(String[][] rawOrders) {

        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < rawOrders.length; i++) {

            try {
                FoodOrder order = new FoodOrder(
                        rawOrders[i][0],
                        rawOrders[i][1]
                );

                valid++;

            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
    }
}

public class GhostOrderValidator {

    public static void main(String[] args) {

        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        FoodOrder.processBatch(orders);

        FoodOrder order =
                new FoodOrder("Ravi", "Biryani");

        order.markDelivered();
        order.markDelivered();
    }
}