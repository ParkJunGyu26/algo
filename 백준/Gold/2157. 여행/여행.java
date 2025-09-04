import java.io.*;
import java.util.*;

public class Main {
    // 오른쪽(동쪽 1번) -> 왼쪽(서쪽 N번)
    // 진행 방향은 오름차순(1, 2, ..., N)
    static int n, m, k;
    static int[][] edge, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n+1][m+1]; // {현재 노드, 지나온 개수} = 누적합
        for (int i = 0; i <= n; i++) Arrays.fill(dp[i], -1);
        edge = new int[n+1][n+1];

        for (int i = 0; i < k; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (a > b) continue; // 서 -> 동

            edge[a][b] = Math.max(edge[a][b], c); // 여러 개 항로 중 최대값으로
        }

        dp[1][1] = 0;

        for (int i = 1; i <= n; i++) { // 현재 노드
            for (int k = 1; k < m; k++) { // 이동 횟수
                if (dp[i][k] == -1) continue;

                for (int j = i+1; j <= n; j++) { // 다음 노드
                    if (edge[i][j] == 0) continue;
                    
                    dp[j][k+1] = Math.max(dp[j][k+1], dp[i][k] + edge[i][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= m; i++) answer = Math.max(answer, dp[n][i]);
        System.out.println(answer);
    }
}