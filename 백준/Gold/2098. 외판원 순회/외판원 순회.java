import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] graph, dp;

    static int dfs(int now, int status) {
        if (status == ((1 << n) - 1)) {
            if (graph[now][0] == 0) {
                return 0;
            }
            return graph[now][0];
        }
        if (dp[now][status] != Integer.MAX_VALUE) return dp[now][status];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            if (graph[now][i] == 0 || (status & (1 << i)) != 0) continue;
            int result = dfs(i, (status | (1 << i)));
            if (result == 0) continue;
            
            result += graph[now][i];

            if (sum == 0) sum = result;
            else sum = Math.min(sum, result);
        }

        return dp[now][status] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n][(1 << n)];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(dfs(0, (1 << 0)));
    }
}