import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] visited;
    static char[][] graph;
    static int n, m, answer = 0;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] dp;
    static HashMap<Character, Integer> map = new HashMap<>(4);

    static boolean inRange(int x, int y) {
        return (-1 < x && x < m && -1 < y && y < n);
    }

    static int dfs(int x, int y) {
        if (!inRange(x, y)) return 1;
        if (visited[y][x]) return 0;
        if (dp[y][x] != 0) return dp[y][x];

        visited[y][x] = true;
        int sum = 0;
        int nx = x + dx[map.get(graph[y][x])];
        int ny = y + dy[map.get(graph[y][x])];

        sum += dfs(nx, ny);
        visited[y][x] = false;

        return dp[y][x] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        visited = new boolean[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) graph[i][j] = tmp.charAt(j);
        }

        map.put('U', 0);
        map.put('R', 1);
        map.put('D', 2);
        map.put('L', 3);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    answer += dfs(j, i);
                }
            }
        }

        System.out.println(answer);
    }
}