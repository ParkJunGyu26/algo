import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static long[][] dp;
    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new long[n+1][10];
        for (int i = 1; i <= 9; i++) dp[1][i] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j-1 >= 0) {
                    dp[i][j-1] += dp[i-1][j];
                    dp[i][j-1] %= MOD;
                }

                if (j+1 <= 9) {
                    dp[i][j+1] += dp[i-1][j];
                    dp[i][j+1] %= MOD;
                }
            }
        }

        long answer = 0;
        for (int i = 0; i <= 9; i++) {
            answer += dp[n][i];
            answer %= MOD;
        }

        System.out.println(answer);
    }
}