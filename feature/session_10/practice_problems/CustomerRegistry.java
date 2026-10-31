
import java.util.*;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Customer))
            return false;

        Customer other = (Customer) obj;

        return this.id == other.id &&
               this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}

public class CustomerRegistry {
    public static void main(String[] args) {
        HashSet<Customer> registry = new HashSet<>();

        Customer c1 = new Customer(101, "Asha");
        Customer c2 = new Customer(101, "Asha");
        Customer c3 = new Customer(102, "Ravi");

        System.out.println(registry.add(c1));

        if (registry.add(c2)) {
            System.out.println("Added");
        } else {
            System.out.println("false (duplicate rejected)");
        }

        System.out.println(registry.add(c3));

        System.out.println("unique count " + registry.size());

        System.out.println("contains: " +
            registry.contains(new Customer(101, "Asha")));
    }
}
