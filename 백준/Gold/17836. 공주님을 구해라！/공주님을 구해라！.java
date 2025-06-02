import java.util.*;
import java.io.*;

public class Main {
    static int n, m, t, answer = -1;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;
    static int[][][] res;

    static class Node {
        int x, y, life;
        Node(int x, int y, int life) {
            this.x = x;
            this.y = y;
            this.life = life;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        res[0][0][0] = 0;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int life = cur.life;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) continue;

                
                if (graph[ny][nx] == 0) {
                    if (res[y][x][1] != Integer.MAX_VALUE) {
                        if (res[ny][nx][1] == Integer.MAX_VALUE) {
                            res[ny][nx][1] = res[y][x][1] + 1;
                            q.offer(new Node(nx, ny, life));
                        }
                    }

                    if (res[ny][nx][0] <= res[y][x][0] + 1) continue;
                    
                    if (res[ny][nx][0] == Integer.MAX_VALUE && res[y][x][0] != Integer.MAX_VALUE) {
                        res[ny][nx][0] = res[y][x][0] + 1;
                        q.offer(new Node(nx, ny, life));
                    }
                } else if (graph[ny][nx] == 1) {
                    if (life == 0 || res[ny][nx][1] != Integer.MAX_VALUE) continue;
                    
                    res[ny][nx][1] = res[y][x][1] + 1;
                    q.offer(new Node(nx, ny, life));
                } else {
                    graph[ny][nx] = 0;
                    res[ny][nx][0] = res[ny][nx][1] = res[y][x][0]+1;
                    q.offer(new Node(nx, ny, life+1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        graph = new int[n][m];
        res = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                res[i][j][0] = res[i][j][1] = Integer.MAX_VALUE;
            }
        }

        
        bfs();

        answer = (res[n-1][m-1][0] <= t || res[n-1][m-1][1] <= t) ? Math.min(res[n-1][m-1][0], res[n-1][m-1][1]) : -1;

        System.out.println((answer == -1) ? "Fail" : answer);
    }
}
