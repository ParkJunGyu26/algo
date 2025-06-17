import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;
    static long[][] dp;

    static boolean inRange(int x, int y) {
        return (x >= 0 && x < m && y >= 0 && y < n);
    }

    // DFS + DP: (x,y)에서 목적지까지 가는 경로 수를 반환
    static long dfs(int x, int y) {
        if (y == n-1 && x == m-1) {
            return 1;
        }
        
        if (dp[y][x] != -1) {
            return dp[y][x];
        }
        
        long paths = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (!inRange(nx, ny) || graph[ny][nx] >= graph[y][x]) {
                continue;
            }
            
            paths += dfs(nx, ny);
        }
        
        return dp[y][x] = paths;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        dp = new long[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        long result = dfs(0, 0);
        
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
} 