import java.util.*;
import java.io.*;

public class Main {
    static int n, l, r;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] A;

    static class Node {
        int x, y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < n && -1 < y && y < n);
    }

    static boolean conditionCheck(int nx, int ny, int x, int y) {
        int gap = Math.abs(A[ny][nx] - A[y][x]);

        return (l <= gap && gap <= r);
    }

    static int bfs(int xx, int yy, int[][] changeA, int number, int[][] visited) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(xx, yy));
        int totalSum = A[yy][xx];
        int cnt = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i ++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (inRange(nx, ny) && visited[ny][nx] == 0 && conditionCheck(nx, ny, x, y)) {
                    q.offer(new Node(nx, ny));
                    visited[ny][nx] = number;
                    totalSum += A[ny][nx];
                    cnt++;
                }
            }
        }

        if (cnt != 1) {
            int target = totalSum / cnt;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == number) {
                        changeA[i][j] = target;
                    }
                }
            }

            return cnt;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) A[i][j] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        while (true) {
            int[][] visited = new int[n][n];
            int[][] changeA = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) changeA[i][j] = A[i][j];
            }

            for (int i = 0; i < n; i++) {
                Arrays.fill(visited[i], 0);
            }

            int number = 1;
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j] == 0) {
                        visited[i][j] = number;
                        cnt += bfs(j, i, changeA, number++, visited);
                    }
                }
            }

            if (cnt == 0) break;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) A[i][j] = changeA[i][j];
            }
            answer++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}