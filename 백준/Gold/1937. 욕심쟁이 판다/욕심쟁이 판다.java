import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;
    static long answer = 0;
    static long[][] dp;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < n && -1 < y && y < n);
    }

    static long dfs(int x, int y) {
        if (dp[y][x] != 1) return dp[y][x];

        long result = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny)) continue;

            if (graph[y][x] < graph[ny][nx]) result = Math.max(result, 1 + dfs(nx, ny));
        }

        return dp[y][x] = result;
    }
    
    // DFS + DP
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        dp = new long[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1L);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dfs(j, i);
                if (dp[i][j] > answer) answer = dp[i][j];
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}