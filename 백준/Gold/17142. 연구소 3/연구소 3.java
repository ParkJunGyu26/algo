import java.util.*;
import java.io.*;

public class Main {
    static int n, m, answer = Integer.MAX_VALUE;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;
    static ArrayList<Node> virus;

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

    static int bfs(ArrayList<Node> hubo) {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(res[i], Integer.MAX_VALUE);
        Queue<Node> q = new LinkedList<>();
        for (Node node : hubo) {
            res[node.y][node.x] = 0;
            q.offer(node);
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!inRange(nx, ny) || graph[ny][nx] == 1 || res[cur.y][cur.x] <= res[ny][nx] + 1) continue;

                res[ny][nx] = res[cur.y][cur.x] + 1;
                q.offer(new Node(nx, ny));
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 0) answer = Math.max(answer, res[i][j]);
            }
        }

        return answer;
    }

    static void dfs(int index, ArrayList<Node> hubo) {
        if (hubo.size() == m) {
            answer = Math.min(answer, bfs(hubo));
            return;
        }

        for (int i = index; i < virus.size(); i++) {
            hubo.add(virus.get(i));
            dfs(i+1, hubo);
            hubo.remove(hubo.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        virus = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) {
                    virus.add(new Node(j, i));
                }
            }
        }

        ArrayList<Node> hubo = new ArrayList<>();
        dfs(0, hubo);

        answer = (answer == Integer.MAX_VALUE) ? -1 : answer;
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}