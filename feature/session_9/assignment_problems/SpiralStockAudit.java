import java.util.ArrayList;

public class SpiralStockAudit {

    public static ArrayList<Integer> auditRoute(int[][] grid) {

        ArrayList<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = grid.length - 1;

        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {

            // 1. Traverse top row: left to right
            for (int i = left; i <= right; i++) {
                result.add(grid[top][i]);
            }

            top++;

            // 2. Traverse right column: top to bottom
            for (int i = top; i <= bottom; i++) {
                result.add(grid[i][right]);
            }

            right--;

            // 3. Traverse bottom row: right to left
            if (top <= bottom) {

                for (int i = right; i >= left; i--) {
                    result.add(grid[bottom][i]);
                }

                bottom--;
            }

            // 4. Traverse left column: bottom to top
            if (left <= right) {

                for (int i = bottom; i >= top; i--) {
                    result.add(grid[i][left]);
                }

                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println(auditRoute(grid));
    }
}