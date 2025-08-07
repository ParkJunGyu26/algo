import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k, answer = 0;
    static int[] dx = {1, 0}, dy = {0, 1};
    static int[][] graph;

    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll(); // {x, y, k 만났는지}

            if (cur[0] == (m-1) && cur[1] == (n-1)) {
                if (cur[2] == 1) answer++;
                continue;
            }

            for (int i = 0; i < 2; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!(-1 < nx && nx < m && -1 < ny && ny < n)) continue;

                int status = cur[2];
                if (status == 1) q.offer(new int[] {nx, ny, status});
                else {
                    status = (graph[ny][nx] == 1) ? 1 : 0;
                    q.offer(new int[] {nx, ny, status});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Math.max(Integer.parseInt(st.nextToken()) - 1, 0);
        
        graph = new int[n][m];
        if (k == 0) for (int i = 0; i < n; i++) Arrays.fill(graph[i], 1);
        else graph[k / m][k % m] = 1;

        bfs();

        System.out.println(answer);
    }
}