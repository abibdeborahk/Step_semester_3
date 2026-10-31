
interface Reservable {
    void reserve(String member);
}

interface Downloadable {
    void download();
}

abstract class Resource {
    String id;

    Resource(String id) {
        this.id = id;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id);
    }

    public void reserve(String member) {
        System.out.println(id + " reserved for " + member);
    }
}

class EBook extends Resource implements Downloadable {
    EBook(String id) {
        super(id);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class LibraryPlatform {

    static Resource[] resources = new Resource[100];
    static int size = 0;

    static void add(Resource resource) {
        int pos = 0;

        while (pos < size &&
               resources[pos].id.compareTo(resource.id) < 0) {
            pos++;
        }

        if (pos < size &&
            resources[pos].id.equals(resource.id)) {
            System.out.println("duplicate rejected");
            return;
        }

        // Shift elements to make space
        for (int i = size; i > pos; i--) {
            resources[i] = resources[i - 1];
        }

        resources[pos] = resource;
        size++;

        System.out.println(resource.id + " added");
    }

    static int find(String id) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            int result = resources[mid].id.compareTo(id);

            if (result == 0)
                return mid;
            else if (result < 0)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return -1;
    }

    static void reserve(String id, String member) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource r = resources[index];

        if (r instanceof Reservable) {
            ((Reservable) r).reserve(member);
        } else {
            System.out.println(id + " rejected: reserve unsupported");
        }
    }

    static void download(String id) {
        int index = find(id);

        if (index == -1) {
            System.out.println(id + " not found");
            return;
        }

        Resource r = resources[index];

        if (r instanceof Downloadable) {
            ((Downloadable) r).download();
        } else {
            System.out.println(id + " rejected: download unsupported");
        }
    }

    public static void main(String[] args) {
        add(new Book("B1"));
        add(new Book("B1"));
        add(new EBook("E1"));

        reserve("B1", "M1");
        download("B1");
        download("E1");

        int index = find("E1");

        if (index != -1) {
            System.out.println("E1 found at index " + index);
        } else {
            System.out.println("E1 not found");
        }
    }
}
