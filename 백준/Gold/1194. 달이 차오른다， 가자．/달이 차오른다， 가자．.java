import java.util.*;
import java.io.*;

public class Main {
    static char[][] graph;
    static int n, m;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[][][] visited;
    static Node start;

    static class Node {
        int x, y;

        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Move {
        Node node;
        int res;
        int key;

        Move (Node node, int res, int key) {
            this.node = node;
            this.res = res;
            this.key = key;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static boolean haveThisKey(int key, char now) {
        int result = key & (1 << (now - 'A'));
        if (result != 0) return true;
        return false;
    }

    static int bfs() {
        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(start, 0, 0));

        visited = new boolean[n][m][1 << 6];
        visited[start.y][start.x][0] = true;

        while (!q.isEmpty()) {
            Move cur = q.poll();

            if (graph[cur.node.y][cur.node.x] == '1') {
                return cur.res;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.node.x + dx[i];
                int ny = cur.node.y + dy[i];

                if (!inRange(nx, ny) || graph[ny][nx] == '#' || visited[ny][nx][cur.key]) continue;

                Node nNode = new Node(nx, ny);
                if (graph[ny][nx] == '.' || graph[ny][nx] == '1') {
                    visited[ny][nx][cur.key] = true;
                    q.offer(new Move(nNode, cur.res+1, cur.key));
                } else if ('a' <= graph[ny][nx] && graph[ny][nx] <= 'f') {
                    int nKey = cur.key | (1 << (graph[ny][nx] - 'a'));
                    visited[ny][nx][nKey] = true;
                    q.offer(new Move(nNode, cur.res+1, nKey));
                } else if ('A' <= graph[ny][nx] && graph[ny][nx] <= 'F') {
                    if (haveThisKey(cur.key, graph[ny][nx])) {
                        visited[ny][nx][cur.key] = true;
                        q.offer(new Move(nNode, cur.res+1, cur.key));
                    }
                }
            }
        }

        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        start = new Node(-1, -1);
        
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = tmp.charAt(j);
                if (graph[i][j] == '0') {
                    start.x = j;
                    start.y = i;
                    graph[i][j] = '.';
                }
            }
        }

        System.out.println(bfs());
    }
}