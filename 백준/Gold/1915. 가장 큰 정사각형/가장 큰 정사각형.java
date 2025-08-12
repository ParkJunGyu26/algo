import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] graph;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n+1][m+1][3];
        graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = tmp.charAt(j);
                if (graph[i][j] == '1') dp[i+1][j+1][0] = dp[i+1][j+1][1] = dp[i+1][j+1][2] = 1;
            }
        }

        boolean check = false;
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (graph[i-1][j-1] == '0') continue;
                check = true;

                if (j-2 >= 0 && graph[i-1][j-2] == '1') dp[i][j][0] = dp[i][j-1][0] + 1; // 왼쪽
                if (i-2 >= 0 && graph[i-2][j-1] == '1') dp[i][j][1] = dp[i-1][j][1] + 1; // 위쪽
                if ((i-2) >= 0 && (j-2) >= 0 && graph[i-2][j-2] == '1') dp[i][j][2] = Math.min(dp[i-1][j-1][0], Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2])) +1; // 왼쪽 위쪽 대각선

                answer = Math.max(answer, Math.min(dp[i][j][0], Math.min(dp[i][j][1], dp[i][j][2])));
            }
        }

        if (answer == 0 && check) answer++;
        System.out.println(answer * answer);
    }
}
