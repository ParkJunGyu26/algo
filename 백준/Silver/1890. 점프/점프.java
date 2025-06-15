import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dx = {1, 0}, dy = {0, 1};
    static int[][] graph;
    static long[][] dp;

    static class Node {
        int x, y;

        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < y && y < n && -1 < x && x < n);
    }

    static long solve() {
        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0 || dp[i][j] == 0) continue;

                for (int k = 0; k < 2; k++) {
                    int nx = j + dx[k] * graph[i][j];
                    int ny = i + dy[k] * graph[i][j];

                    if (!inRange(nx, ny)) continue;

                    if (dp[ny][nx] == 0) dp[ny][nx] = dp[i][j];
                    else dp[ny][nx] += dp[i][j];
                }
            }
        }

        return dp[n-1][n-1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        dp = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 0L);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solve()));
        bw.flush();
        bw.close();
        br.close();
    }
}
