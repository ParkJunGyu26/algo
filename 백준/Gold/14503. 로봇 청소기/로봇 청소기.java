import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] clean;
    static int n, m, answer = 0;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static void dfs(int x, int y, int dire) {
        boolean notClean = false;
        if (graph[y][x] == 0 && !clean[y][x]) {
            answer++;
            clean[y][x] = true;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny)) continue;

            if (graph[ny][nx] == 0 && !clean[ny][nx]) notClean = true;
        }

        if (notClean) {
            for (int i = 0; i < 4; i++) {
                dire = (dire + 3) % 4;

                int nx = x + dx[dire];
                int ny = y + dy[dire];

                if (graph[ny][nx] == 0 && !clean[ny][nx]) break;
            }

            dfs(x + dx[dire], y + dy[dire], dire);
        } else {
            int nx = x + dx[(dire + 2) % 4];
            int ny = y + dy[(dire + 2) % 4];

            if (inRange(nx, ny)) {
                if (graph[ny][nx] == 1) return;
                
                dfs(nx, ny, dire);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r, c, d;
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        clean = new boolean[n][m];
        for (int i = 0; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        dfs(c, r, d);
        System.out.println(answer);
    }
}