import java.util.*;
import java.io.*;

public class Main {
    static char[][] graph = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int answer = 0;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < 6 && -1 < y && y < 12);
    }

    static int bfs(int xx, int yy) {
        char target = graph[yy][xx];
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> tracking = new ArrayList<>();
        q.offer(new Node(xx, yy));
        tracking.add(new Node(xx, yy));

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny) || visited[ny][nx] || graph[ny][nx] != target) continue;

                Node next = new Node(nx, ny);
                tracking.add(next);
                visited[ny][nx] = true;
                q.offer(next);
            }
        }

        if (tracking.size() < 4) return 0;

        for (Node node : tracking) {
            graph[node.y][node.x] = '.';
        }

        return 1;
    }

    static void changeGraph() {
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (graph[i][j] != '.') {
                    int y = i;
                    while(true) {
                        if (y+1 == 12 || graph[y+1][j] != '.') {
                            if (y != i) {
                                graph[y][j] = graph[i][j];
                                graph[i][j] = '.';
                            }
                            break;
                        }
                        y++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 12; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 6; j++) graph[i][j] = tmp.charAt(j);
        }

        while (true) {
            int check = 0;
            for (int i = 0; i < 12; i++) Arrays.fill(visited[i], false);

            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (!visited[i][j] && graph[i][j] != '.') {
                        visited[i][j] = true;
                        check += bfs(j, i);
                    }
                }
            }

            if (check == 0) break;

            changeGraph();
            answer++;
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
