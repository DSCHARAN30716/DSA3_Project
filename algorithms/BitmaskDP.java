package algorithms;

import java.util.Arrays;

public class BitmaskDP {

    private static final int INF = Integer.MAX_VALUE / 4;

    private static int[][] distance;
    private static int[][] dp;
    private static int[][] parent;

    public static int[] solve(int[][] distances) {

        if (distances == null ||
                distances.length == 0) {

            return new int[0];
        }

        int n = distances.length;

        if (n == 1) {
            return new int[]{0};
        }

        distance = distances;

        int totalMasks = 1 << n;

        dp = new int[totalMasks][n];
        parent = new int[totalMasks][n];

        for (int i = 0; i < totalMasks; i++) {

            Arrays.fill(dp[i], INF);
            Arrays.fill(parent[i], -1);
        }

        // Start from node 0
        dp[1][0] = 0;

        for (int mask = 1;
             mask < totalMasks;
             mask++) {

            for (int current = 0;
                 current < n;
                 current++) {

                if ((mask & (1 << current)) == 0) {
                    continue;
                }

                if (dp[mask][current] == INF) {
                    continue;
                }

                for (int next = 0;
                     next < n;
                     next++) {

                    if ((mask & (1 << next)) != 0) {
                        continue;
                    }

                    int newMask =
                            mask | (1 << next);

                    int newCost =
                            dp[mask][current]
                                    + distance[current][next];

                    if (newCost <
                            dp[newMask][next]) {

                        dp[newMask][next] =
                                newCost;

                        parent[newMask][next] =
                                current;
                    }
                }
            }
        }

        int finalMask =
                totalMasks - 1;

        int bestCost = INF;
        int lastNode = -1;

        for (int i = 0; i < n; i++) {

            if (dp[finalMask][i] < bestCost) {

                bestCost =
                        dp[finalMask][i];

                lastNode = i;
            }
        }

        // Reconstruct route
        int[] route = new int[n];

        int mask = finalMask;
        int current = lastNode;

        for (int i = n - 1; i >= 0; i--) {

            route[i] = current;

            int previous =
                    parent[mask][current];

            mask =
                    mask & ~(1 << current);

            current = previous;

            if (current == -1 &&
                    i > 0) {

                break;
            }
        }

        return route;
    }
}