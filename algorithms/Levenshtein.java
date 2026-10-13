package algorithms;

public class Levenshtein {

    public static int distance(
            String first,
            String second) {

        if (first == null) {
            return second == null ? 0 : second.length();
        }

        if (second == null) {
            return first.length();
        }

        int m = first.length();
        int n = second.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                int cost;

                if (first.charAt(i - 1)
                        == second.charAt(j - 1)) {

                    cost = 0;

                } else {

                    cost = 1;
                }

                int insert =
                        dp[i][j - 1] + 1;

                int delete =
                        dp[i - 1][j] + 1;

                int replace =
                        dp[i - 1][j - 1] + cost;

                dp[i][j] =
                        Math.min(
                                Math.min(insert, delete),
                                replace
                        );
            }
        }

        return dp[m][n];
    }
}