import java.util.*;

public class MallFootfallReport {

    public static ArrayList<Integer> footfallReport(
            int[] visitors, int[][] queries) {

        int n = visitors.length;

        // Create prefix sum array
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        ArrayList<Integer> result = new ArrayList<>();

        // Answer each query
        for (int i = 0; i < queries.length; i++) {

            int start = queries[i][0];
            int end = queries[i][1];

            int sum = prefix[end + 1] - prefix[start];

            result.add(sum);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] visitors = {12, 7, 3, 9, 15, 4, 8};

        int[][] queries = {
            {0, 2},
            {2, 5},
            {4, 6},
            {3, 3}
        };

        System.out.println(
            footfallReport(visitors, queries)
        );
    }
}