import java.util.*;
import java.io.*;

public class Main {
    static int n, m, answer = 0;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}, dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] graph, boongwoori;

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static int check(int x, int y, int target, boolean[][] visited, ArrayList<Node> hubo) {
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!inRange(nx, ny) || visited[ny][nx] || graph[ny][nx] == 0) continue;
            visited[ny][nx] = true;

            if (target < graph[ny][nx]) return 0;

            if (target == graph[ny][nx]) {
                hubo.add(new Node(nx, ny));
                if (check(nx, ny, target, visited, hubo) == 0) return 0;
            }
        }

        for (Node node : hubo) boongwoori[node.y][node.x] = 1;

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        boongwoori = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(boongwoori[i], 0);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0 || boongwoori[i][j] != 0) continue;

                boolean[][] visited = new boolean[n][m];
                for (int k = 0; k < n; k++) Arrays.fill(visited[k], false);
                visited[i][j] = true;

                ArrayList<Node> hubo = new ArrayList<>();
                hubo.add(new Node(j, i));

                answer += check(j, i, graph[i][j], visited, hubo);
            }
        }

        System.out.println(answer);
    }
}
