class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {

        if (rooms == null) {
            return null;
        }

        for (HostelRoom room : rooms) {
            if (room != null && room.occupied < room.beds) {
                return room;
            }
        }

        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {

        HostelRoom room = findAvailableRoom(rooms);

        if (room != null) {
            room.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }
}

public class hostel_allocation_system {

    public static void main(String[] args) {

        HostelRoom[] rooms = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };

        System.out.println("Rooms: C-214 (2/3), C-507 (2/2)");

        HostelRoom.safeAllot(rooms, "Divya");

        System.out.println();

        System.out.println("Rooms: C-214 (3/3), C-507 (2/2)");

        HostelRoom.safeAllot(rooms, "Divya");

      
    }
}