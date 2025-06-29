import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coin = new int[n];
        for (int i = 0; i < n; i++) coin[i] = Integer.parseInt(br.readLine());
        Arrays.sort(coin);

        dp = new int[k+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                if (dp[j-coin[i]] == Integer.MAX_VALUE) continue;
                dp[j] = Math.min(dp[j], dp[j-coin[i]] + 1);
            }
        }

        System.out.println(dp[k] == Integer.MAX_VALUE ? -1 : dp[k]);
    }
}