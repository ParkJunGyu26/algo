import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] graph, dxdy = new int[][] {{1, 0}, {1, 1}, {0, 1}};
    static int[][][] dp;

    private static boolean inRange(int x, int y) {
        return (-1 < x && x < n && -1 < y && y < n);
    }
    
    private static int dfs(int x, int y, int dire) {
        if (x == (n-1) && y == (n-1)) return 1;

        if (dp[y][x][dire] != 0) return dp[y][x][dire];

        // 다음 좌표 탐색 dxdy 테크닉
        int result = 0;
        for (int i = 0; i < 3; i++) {
            // dire : 이전에 놓여진 파이프라인 방향
            // i : 다음 진행 파이프라인 방향
            // 가로 방향으로 왔다면, 세로 방향 스킵 || 세로 방향으로 왔다면, 가로 방향 스킵
            if (dire == 0 && i == 2 || dire == 2 && i == 0) continue;

            int nx = x + dxdy[i][0];
            int ny = y + dxdy[i][1];

            // 범위 밖 또는 1인 경우 스킵
            if (!inRange(nx, ny) || graph[ny][nx] == 1) continue;

            // 다음 파이프라인이 대각선인 경우 양 옆 중 하나가 1인 경우 스킵
            if (i == 1) {
                if (graph[ny-1][nx] == 1 || graph[ny][nx-1] == 1) continue;
            }

            result += dfs(nx, ny, i);
        }
        
        return dp[y][x][dire] = result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        dp = new int[n][n][3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(1, 0, 0));
    }
}