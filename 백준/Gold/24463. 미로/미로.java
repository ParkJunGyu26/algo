import java.util.*;
import java.io.*;

// 16195번
// O(N * 3 log N)
// DP -> DP[N] = DP[N-1] + N 자릿수 순열 개수

// 24463번
// O(N^2)
// BFS(최단거리) + 역추적
public class Main {
    static char[][] graph;
    static boolean[][] visited;
    static int n, m;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] parent;

    private static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    private static void bfs(int[] start, int[] end) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start[0], start[1]});
        visited[start[1]][start[0]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            
            if (cur[0] == end[0] && cur[1] == end[1]) {
                int x = cur[0];
                int y = cur[1];

                while(true) {
                    graph[y][x] = '-';
                    if (parent[y][x] == -1) break;

                    int index = parent[y][x];

                    y += dy[index];
                    x += dx[index];
                }
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (!inRange(nx, ny) || graph[ny][nx] == '+' || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.offer(new int[] {nx, ny});
                parent[ny][nx] = (i + 2) % 4;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        visited = new boolean[n][m];
        parent = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(parent[i], -2);
        Queue<int[]> hubo = new LinkedList<>(); // (x, y)

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = tmp.charAt(j);
                if ((i == n-1 || j == m-1 || i == 0 || j == 0) && graph[i][j] == '.') {
                    hubo.offer(new int[] {j, i});
                }
            }
        }

        int[] start = hubo.poll();
        int[] end = hubo.poll();
        parent[start[1]][start[0]] = -1;
        bfs(start, end);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == '-') sb.append('.');
                else if (graph[i][j] == '+') sb.append('+');
                else sb.append('@');
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}