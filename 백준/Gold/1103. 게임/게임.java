import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] visited;
    static char[][] graph;
    static int n, m;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] dp;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static int dfs(int x, int y) {
        if (!inRange(x, y) || graph[y][x] == 'H') return 0;
        if (visited[y][x]) return -1;
        if (dp[y][x] != -1) return dp[y][x];

        visited[y][x] = true;
        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * (graph[y][x] - '0');
            int ny = y + dy[i] * (graph[y][x] - '0');

            int result = dfs(nx, ny);
            if (result == -1) return -1;
            dp[y][x] = Math.max(dp[y][x], result + 1);
        }
        visited[y][x] = false;

        return dp[y][x];
    }

    // DP + DFS + (무한루프 방지)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = (tmp.charAt(j));
            }
        }
        
        bw.write(dfs(0, 0) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}