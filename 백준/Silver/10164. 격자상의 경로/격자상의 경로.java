import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] visited;
    static int[] dx = {1, 0}, dy = {0, 1};
    static int[][] graph, path;

    private static int bfs(int[] start, int[] end) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        for (int i = 0; i <= visited.length; i++) Arrays.fill(path[i], 0);

        visited[start[1]][start[0]] = true;
        path[start[1]+1][start[0]+1] = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 2; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if (-1 < nx && nx <= end[0] && -1 < ny && ny <= end[1]) {
                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        q.offer(new int[] {nx, ny});

                        int nnx = nx + 1;
                        int nny = ny + 1;
                        path[nny][nnx] = path[nny-1][nnx] + path[nny][nnx-1];
                    }
                }
            }
        }

        return path[end[1]+1][end[0]+1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Math.max(Integer.parseInt(st.nextToken()) - 1, 0);
        
        graph = new int[n][m];
        visited = new boolean[n][m];
        path = new int[n+1][m+1];

        if (k == 0) System.out.println(bfs(new int[] {0, 0}, new int[] {(m-1), (n-1)}));
        else System.out.println(bfs(new int[] {0, 0}, new int[] {(k % m), (k / m)}) * bfs(new int[] {(k % m), (k / m)}, new int[] {(m-1), (n-1)}));
    }
}