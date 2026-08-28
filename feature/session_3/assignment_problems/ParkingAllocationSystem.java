class ParkingSlot {

    private String slotNo;
    private int capacity;
    private int occupiedCount;

    // Constructor
    public ParkingSlot(
            String slotNo,
            int capacity,
            int occupiedCount) {

        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    // Allot a vehicle to the parking slot
    public void allot(String vehicleNo) {

        if (occupiedCount < capacity) {

            occupiedCount++;

            System.out.println(
                vehicleNo
                + " allotted to slot "
                + slotNo
            );
        }
    }

    // Find the first available parking slot
    public static ParkingSlot findAvailableSlot(
            ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {

            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        // Return null when all slots are full
        return null;
    }

    /*
     * Passing a ParkingSlot array does not copy the ParkingSlot
     * objects. The array contains references to the objects.
     * Therefore, changes made through those references affect
     * the original ParkingSlot objects.
     */
    public static void safeAllot(
            ParkingSlot[] slots,
            String vehicleNo) {

        ParkingSlot availableSlot =
            findAvailableSlot(slots);

        // Check for null before using the object
        if (availableSlot != null) {

            availableSlot.allot(vehicleNo);

        } else {

            System.out.println(
                "No slots available for "
                + vehicleNo
            );
        }
    }
}


// Main class
public class ParkingAllocationSystem {

    public static void main(String[] args) {

        // Test 1: One slot is available
        ParkingSlot[] availableSlots = {
            new ParkingSlot("A1", 4, 3),
            new ParkingSlot("A2", 5, 5)
        };

        ParkingSlot.safeAllot(
            availableSlots,
            "TN09AB1234"
        );


        // Test 2: All slots are full
        ParkingSlot[] fullSlots = {
            new ParkingSlot("A1", 4, 4),
            new ParkingSlot("A2", 5, 5)
        };

        ParkingSlot.safeAllot(
            fullSlots,
            "TN09AB1234"
        );
    }
}