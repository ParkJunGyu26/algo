import java.util.*;
import java.io.*;

public class Main {
    static int r, c;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] waterRes;
    static char[][] graph;
    static Node target;
    static Queue<Node> water = new LinkedList<>(), dochi = new LinkedList<>();

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < c && -1 < y && y < r);
    }

    static void bfs(Queue<Node> targetQ, int[][] res, boolean status) {
        Queue<Node> q = new LinkedList<>();

        while (!targetQ.isEmpty()) {
            Node cur = targetQ.poll();
            if (status) {
                waterRes[cur.y][cur.x] = 0;
            } else {
                res[cur.y][cur.x] = 0;
            }
            q.offer(cur);
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            if (graph[y][x] == 'D') return;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (inRange(nx, ny)) {
                    if (status) { // 물
                        if (waterRes[ny][nx] == Integer.MAX_VALUE && graph[ny][nx] == '.') {
                            waterRes[ny][nx] = waterRes[y][x] + 1;
                            q.offer(new Node(nx, ny));
                        }
                    } else { // 도치
                        if (res[ny][nx] == Integer.MAX_VALUE && waterRes[ny][nx] > res[y][x] + 1 && res[ny][nx] == Integer.MAX_VALUE && graph[ny][nx] == '.' || graph[ny][nx] == 'D') {
                            res[ny][nx] = res[y][x] + 1;
                            q.offer(new Node(nx, ny));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new char[r][c];
        waterRes = new int[r][c];
        int[][] dochiRes = new int[r][c];

        for (int i = 0; i < waterRes.length; i++) {
            for (int j = 0; j < waterRes[i].length; j++) waterRes[i][j] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < dochiRes.length; i++) {
            for (int j = 0; j < dochiRes[i].length; j++) dochiRes[i][j] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            String tmp = st.nextToken();

            for (int j = 0; j < c; j++) {
                graph[i][j] = tmp.charAt(j);
                if (graph[i][j] == 'S') dochi.offer(new Node(j, i));
                else if (graph[i][j] == '*') water.offer(new Node(j, i));
                else if (graph[i][j] == 'D') target = new Node(j, i);
            }
        }
        int[][] tmp = new int[1][1];

        bfs(water, tmp, true);
        bfs(dochi, dochiRes, false);
        
        if (dochiRes[target.y][target.x] == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(dochiRes[target.y][target.x]);
    }
}