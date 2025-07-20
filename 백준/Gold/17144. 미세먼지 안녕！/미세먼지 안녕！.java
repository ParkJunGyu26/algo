import java.util.*;
import java.io.*;

// 경사로 높이는 1이다
public class Main {
    static int r, c, t;
    static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    static int[][] graph;

    private static int countDust() {
        int result = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) if (graph[i][j] > 0) result += graph[i][j];
        }

        return result;
    }

    private static boolean inRange(int x, int y) {
        return (-1 < x && x < c && -1 < y && y < r);
    }

    private static void spread() {
        int[][] newGraph = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) newGraph[i][j] = graph[i][j];
        
        // 미세먼지 확산
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (graph[i][j] < 5) continue;

                int prefixSum = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = j + dx[k];
                    int ny = i + dy[k];

                    if (!inRange(nx, ny) || graph[ny][nx] == -1) continue;

                    int plus = graph[i][j] / 5;
                    newGraph[ny][nx] += plus;
                    prefixSum += plus;
                }

                newGraph[i][j] -= prefixSum;
            }
        }

        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) graph[i][j] = newGraph[i][j];
    }

    private static void airPure(int[] dot, int[] ddx, int[] ddy) {
        int[][] newGraph = new int[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++) newGraph[i][j] = graph[i][j];

        int index = 0;
        int[] now = new int[] {dot[0] + ddx[index], dot[1] + ddy[index]};
        while (true) {
            int nx = now[0] + ddx[index];
            int ny = now[1] + ddy[index];

            if (nx == dot[0] && ny == dot[1]) {
                newGraph[now[1]][now[0]] -= graph[now[1]][now[0]];
                for (int i = 0; i < r; i++)
                    for (int j = 0; j < c; j++) graph[i][j] = newGraph[i][j];
                return;
            }

            if (!inRange(nx, ny)) {
                index++;
                continue;
            }

            newGraph[ny][nx] += graph[now[1]][now[0]];
            newGraph[now[1]][now[0]] -= graph[now[1]][now[0]];

            now[0] = nx;
            now[1] = ny;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        int[] top = new int[] {-1, -1};
        int[] bottom = new int[] {-1, -1};
        graph = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == -1) {
                    if (top[0] == -1) {
                        top[0] = j;
                        top[1] = i;
                    } else {
                        bottom[0] = j;
                        bottom[1] = i;
                    }
                }
            }
        }

        int[] bx = new int[] {1, 0, -1, 0};
        int[] by = new int[] {0, 1, 0, -1};

        for (int a = 0; a < t; a++) {
            spread();
            airPure(top, dx, dy);
            airPure(bottom, bx, by);
        }

        System.out.println(countDust());
    }
}