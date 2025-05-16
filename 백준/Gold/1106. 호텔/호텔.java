import java.util.*;
import java.io.*;

public class Main {
    static int c, n;
    static int[] dp, cost, client;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        cost = new int[n];
        client = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            client[i] = Integer.parseInt(st.nextToken());
        }

        int max = c + 100 * 100;
        dp = new int[max+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = client[i]; j <= max; j++) {
                if (dp[j - client[i]] != Integer.MAX_VALUE) dp[j] = Math.min(dp[j], dp[j - client[i]] + cost[i]);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = c; i <= max; i++) answer = Math.min(answer, dp[i]);
        System.out.print(answer);
    }
}