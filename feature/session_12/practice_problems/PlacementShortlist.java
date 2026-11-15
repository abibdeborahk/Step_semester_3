
import java.util.*;

class Candidate {

    String name;
    int[] scores = new int[6];
    int total;
    int bestGain;
    int startTest;
    int endTest;
    int registrationOrder;

    Candidate(String name, int[] scores, int order) {

        this.name = name;
        this.scores = scores;
        this.registrationOrder = order;

        for (int s : scores) {
            total += s;
        }

        findBestClimb();
    }

    // Kadane's Algorithm
    void findBestClimb() {

        int currentSum = 0;
        int maxSum = 0;

        int currentStart = 1;

        bestGain = 0;
        startTest = 1;
        endTest = 1;

        for (int i = 0; i < 5; i++) {

            int change = scores[i + 1] - scores[i];

            if (currentSum + change < change) {
                currentSum = change;
                currentStart = i + 1;
            } else {
                currentSum += change;
            }

            // Strict comparison preserves earliest tie
            if (currentSum > maxSum) {

                maxSum = currentSum;

                bestGain = maxSum;

                startTest = currentStart;
                endTest = i + 2;
            }
        }
    }
}

public class PlacementShortlist {

    static void mergeSort(Candidate[] a, int left, int right) {

        if (left >= right)
            return;

        int mid = (left + right) / 2;

        mergeSort(a, left, mid);
        mergeSort(a, mid + 1, right);

        merge(a, left, mid, right);
    }

    static void merge(Candidate[] a, int left, int mid, int right) {

        Candidate[] temp = new Candidate[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            // Stable sorting
            if (a[i].total >= a[j].total) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = a[i++];
        }

        while (j <= right) {
            temp[k++] = a[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            a[left + x] = temp[x];
        }
    }

    // Binary Search: First index with total < cutoff
    static int cutoff(Candidate[] a, int n, int x) {

        int low = 0;
        int high = n;

        while (low < high) {

            int mid = (low + high) / 2;

            if (a[mid].total >= x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    // Two Pointer Technique
    static void pair(Candidate[] a, int n, int target) {

        int left = 0;
        int right = n - 1;

        while (left < right) {

            int sum = a[left].total + a[right].total;

            if (sum == target) {

                System.out.println(
                    "PAIR " + target + ": " +
                    a[left].name + " + " + a[right].name
                );

                return;
            }

            else if (sum < target) {
                left++;
            }

            else {
                right--;
            }
        }

        System.out.println("PAIR " + target + ": no pair");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Candidate[] candidates = new Candidate[n];

        // Input
        for (int i = 0; i < n; i++) {

            String name = sc.next();

            int[] scores = new int[6];

            for (int j = 0; j < 6; j++) {
                scores[j] = sc.nextInt();
            }

            candidates[i] = new Candidate(name, scores, i);
        }

        // Sorting
        mergeSort(candidates, 0, n - 1);

        // Shortlist
        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {

            Candidate c = candidates[i];

            System.out.println(
                (i + 1) + ". " + c.name +
                " " + c.total +
                " | best climb +" + c.bestGain +
                " (test " + c.startTest +
                " to " + c.endTest + ")"
            );
        }

        // Queries
        while (sc.hasNext()) {

            String command = sc.next();

            if (command.equalsIgnoreCase("CUTOFF")) {

                int x = sc.nextInt();

                int count = cutoff(candidates, n, x);

                System.out.println(
                    "CUTOFF " + x + ": " +
                    count + " candidates"
                );
            }

            else if (command.equalsIgnoreCase("PAIR")) {

                int target = sc.nextInt();

                pair(candidates, n, target);
            }
        }

        sc.close();
    }
}
