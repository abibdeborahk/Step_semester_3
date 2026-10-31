public class MaxSumSubarray {

    public static int maxSumSubarray(int[] sales, int k) {

        if (k <= 0 || k > sales.length) {
            throw new IllegalArgumentException("Invalid k");
        }

        int sum = 0;

        // Calculate first window sum
        for (int i = 0; i < k; i++) {
            sum += sales[i];
        }

        int maxSum = sum;

        // Slide the window
        for (int i = k; i < sales.length; i++) {

            sum = sum - sales[i - k] + sales[i];

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] sales = {2, 1, 5, 1, 3, 2};

        int k = 3;

        int result = maxSumSubarray(sales, k);

        System.out.println("Maximum sum: " + result);
    }
}