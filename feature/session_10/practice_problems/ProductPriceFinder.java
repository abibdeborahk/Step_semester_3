
public class ProductPriceFinder {

    static int lowerBound(int[] prices, int target) {
        int low = 0;
        int high = prices.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    static int upperBound(int[] prices, int target) {
        int low = 0;
        int high = prices.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};

        int lower = 150;
        int upper = 300;

        int lb = lowerBound(prices, lower);
        int ub = upperBound(prices, upper);

        System.out.println("Lower bound index " + lb);
        System.out.println("Upper bound index " + ub);
        System.out.println("Count " + (ub - lb));
    }
}
