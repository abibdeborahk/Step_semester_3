public class product_inventory_csv_parser {
    public static void main (String args[]) {
    parseInventoryRecord("Wireless Mouse,WM-2201,150");
    }

    static void parseInventoryRecord(String csvLine) {
        String[] parts = csvLine.split(",");
        if(parts.length != 3)
        {
            System.out.println("Invalid Record");
        }
        else {
            System.out.println("Product: "+ parts[0] +
                                " | SKU: " + parts[1] +
                               " | Qty: " + parts[2]);
        }
    }
}