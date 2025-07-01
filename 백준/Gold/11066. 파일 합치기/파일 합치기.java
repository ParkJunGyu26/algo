import java.io.*;
import java.util.*;

public class Main {
    static int T, K;
    static int[] arr, prefixSum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            K = Integer.parseInt(br.readLine());
            arr = new int[K + 1];
            prefixSum = new int[K + 1];
            dp = new int[K + 1][K + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }

            for (int len = 2; len <= K; len++) { // 길이 2부터 K까지
                for (int i = 1; i <= K - len + 1; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Integer.MAX_VALUE;

                    for (int k = i; k < j; k++) {
                        int cost = dp[i][k] + dp[k + 1][j] + prefixSum[j] - prefixSum[i - 1];
                        dp[i][j] = Math.min(dp[i][j], cost);
                    }
                }
            }

            System.out.println(dp[1][K]);
        }
    }
}
