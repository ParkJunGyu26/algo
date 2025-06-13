import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], 0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            dp[i][i] = Math.max(1, dp[i-1][i]);

            for (int j = i+1; j <= n; j++) {
                dp[i][j] = dp[i-1][j];
                if (arr[i-1] < arr[j-1]) {
                    if (dp[i-1][j] < dp[i][i]+1) dp[i][j] = dp[i][i] + 1;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) answer = Math.max(dp[i][j], answer);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
