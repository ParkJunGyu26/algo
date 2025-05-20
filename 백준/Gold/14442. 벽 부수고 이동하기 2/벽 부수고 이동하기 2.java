import java.util.*;
import java.io.*;

public class Main {
    static int n, m, k;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;
    static int[][][] res;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static class Node {
        int x, y, possible;

        Node (int x, int y, int p) {
            this.x = x;
            this.y = y;
            possible = p;
        }
    }

    static void bfs() {
        int answer = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, k));
        for (int i = 0; i <= k; i++) res[0][0][i] = 1;

        while (!q.isEmpty()) {
            Node current = q.poll();
            int x = current.x;
            int y = current.y;
            int possible = current.possible;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (inRange(nx, ny)) {
                    if (graph[ny][nx] == 0) {
                        if (res[ny][nx][possible] == 0 || res[ny][nx][possible] > res[y][x][possible] + 1) {
                            res[ny][nx][possible] = res[y][x][possible]+1;
                            q.offer(new Node(nx, ny, possible));
                        }

                    } else {
                        if (possible == 0 || res[ny][nx][possible-1] != 0) continue;

                        res[ny][nx][possible-1] = res[y][x][possible] + 1;
                        q.offer(new Node(nx, ny, possible-1));
                    }
                }
            }
        }

        for (int i = 0; i <= k; i++) if (res[n-1][m-1][i] != 0) answer = Math.min(answer, res[n-1][m-1][i]);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        res = new int[n][m][k+1];

        for (int i = 0; i < n; i++) {
            String temp = br.readLine();

            for (int j = 0; j < m; j++) graph[i][j] = temp.charAt(j) - '0';
        }

        bfs();
    }
}