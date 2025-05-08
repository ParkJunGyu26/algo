import java.util.*;
import java.io.*;

class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static boolean check = false;
    static int m, n;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[][] vec;

    public static boolean inRange(int x, int y) {
        return (-1 < x && x < n && -1 < y && y < m);
    }

    public static void bfs(int xx, int yy) {
        boolean[][] visited = new boolean[m][n];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(xx, yy));
        visited[yy][xx] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;

            if (y == m-1) {
                check = true;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (inRange(nx, ny) && !visited[ny][nx] && vec[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        vec = new int[m][n];

        for (int i = 0; i < m; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                vec[i][j] = tmp.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            if (vec[0][i] == 0) {
                bfs(i, 0);
                if (check) {
                    bw.write("YES");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
            }
        }

        bw.write("NO");
        bw.flush();
        bw.close();
        br.close();
    }
}