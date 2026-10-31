
import java.util.*;

public class FireDrillExits {

    public static int[][] exitDistance(char[][] plan) {

        int rows = plan.length;
        int cols = plan[0].length;

        int[][] distance = new int[rows][cols];

        // Initially, all cells are unreachable
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distance[i], -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Add all exits to the queue
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (plan[i][j] == 'E') {
                    queue.offer(new int[]{i, j});
                    distance[i][j] = 0;
                }
            }
        }

        // Directions: up, down, left, right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // BFS
        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for (int k = 0; k < 4; k++) {

                int nr = r + dr[k];
                int nc = c + dc[k];

                // Check boundaries and whether the cell is open
                if (nr >= 0 && nr < rows &&
                    nc >= 0 && nc < cols &&
                    plan[nr][nc] != '#' &&
                    distance[nr][nc] == -1) {

                    distance[nr][nc] = distance[r][c] + 1;

                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {

        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
