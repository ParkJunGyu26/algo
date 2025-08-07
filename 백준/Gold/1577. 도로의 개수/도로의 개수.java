import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static long[][] dp;
    static int[][][] graph;

    private static boolean inRange(int x, int y) {
        return (-1 < x && x <= m && -1 < y && y <= n);
    }

    private static long bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][m+1];
        
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        dp[1][1] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 2; i++) {
                if (graph[cur[1]][cur[0]][i] == 1) {
                    continue;
                }
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!inRange(nx, ny) || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.offer(new int[] {nx, ny});

                int nnx = nx + 1;
                int nny = ny + 1;
                long top = (graph[ny][nx][3] == 0) ? dp[nny-1][nnx] : 0;
                long left = (graph[ny][nx][2] == 0) ? dp[nny][nnx-1] : 0;
                dp[nny][nnx] = top + left;
            }
        }

        return dp[n+1][m+1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        dp = new long[n+2][m+2];
        graph = new int[n+1][m+1][4];
        for (int i = 0; i < k; i++) {
            int a, b, c, d;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                int ny = b + dy[j];
                int nx = a + dx[j];

                if (!inRange(nx, ny)) continue;

                if (nx == c && ny == d) {
                    graph[b][a][j] = 1;
                    graph[d][c][(j+2)%4] = 1;
                    break;
                }
            }
        }

        System.out.println(bfs());
    }
}