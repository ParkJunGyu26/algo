import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dx = {1, 0}, dy = {0, 1};
    static int[][] graph;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static void bfs() {
        boolean visited[][] = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == m-1 && y == n-1) {
                System.out.println("Yes");
                return;
            }

            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny) || graph[ny][nx] == 0 || visited[ny][nx]) continue;

                q.offer(new int[] {nx, ny});
                visited[ny][nx] = true;
            }
        }

        System.out.println("No");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        bfs();
    }    
}