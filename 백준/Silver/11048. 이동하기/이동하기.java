import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dx = {1, 0, 1}, dy = {0, 1, 1};
    static int[][] graph, dp;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        dp = new int[n+1][m+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], 0);
            for (int j = 0; j < m; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = graph[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 3; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];
                    
                    if (!inRange(nx ,ny)) continue;
                    
                    if (dp[ny][nx] < dp[i][j] + graph[ny][nx]) dp[ny][nx] = (dp[i][j] + graph[ny][nx]);
                }
            }
        }

        bw.write(dp[n-1][m-1] + "");
        bw.flush();
        bw.close();
        br.close();
    }
} 