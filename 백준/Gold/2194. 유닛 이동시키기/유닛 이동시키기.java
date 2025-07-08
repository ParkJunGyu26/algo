import java.io.*;
import java.util.*;

public class Main {
    static int n, m, a, b, k;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph, res;
    static Node start, end;

    static class Node {
        int x, y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static boolean isWall(int x, int y) {
        return (graph[y][x] == -1);
    }

    static boolean isCheck(int x, int y, int dire) {
        switch (dire) {
            case 0:
                for (int nx = x; nx <= x + b; nx++) if (!inRange(nx, y) || isWall(nx, y)) return false;
                break;
            case 1:
                for (int ny = y; ny <= y + a; ny++) if (!inRange(x + b, ny) || isWall(x + b, ny)) return false;
                break;
            case 2:
                for (int nx = x; nx <= x + b; nx++) if (!inRange(nx, y + a) || isWall(nx, y + a)) return false;
                break;
            case 3:
                for (int ny = y; ny <= y + a; ny++) if (!inRange(x, ny) || isWall(x, ny)) return false;
                break;
        }

        return true;
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(start);
        res[start.y][start.x] = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.y == end.y && cur.x == end.x) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isCheck(nx, ny, i)) continue;

                if (res[ny][nx] != Integer.MAX_VALUE) continue;

                q.offer(new Node(nx, ny));
                res[ny][nx] = res[cur.y][cur.x] + 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        a--;
        b--;
        res = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(res[i], Integer.MAX_VALUE);

        graph = new int[n][m];
        int y, x;
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            graph[y-1][x-1] = -1;
        }
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        start = new Node(x-1, y-1);
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        end = new Node(x-1, y-1);

        bfs();
        System.out.println(res[end.y][end.x] == Integer.MAX_VALUE ? -1 : res[end.y][end.x]);
    }
}