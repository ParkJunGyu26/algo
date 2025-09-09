import java.io.*;
import java.util.*;

// 중복 사용안되는 냅색
public class Main {
    static final int MAX_ANSWER = 100*100+1;
    static int n, m, MAX_VALUE;
    static int[][] app; // 0 : 사용 중 메모리, 1 : 비활성화 비용
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        app = new int[n][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            app[i][0] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            app[i][1] = Integer.parseInt(st.nextToken());
            MAX_VALUE += app[i][1];
        }

        int[][] dp = new int[n+1][MAX_VALUE+1];

        for (int j = 0; j <= MAX_VALUE; j++) {

            for (int i = 1; i <= n; i++) {
                if (j < app[i-1][1]) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j - app[i-1][1]] + app[i-1][0]);
                }
            }
        }

        int answer = MAX_ANSWER;
        for (int j = 0; j <= MAX_VALUE; j++) {
            for (int i = 1; i <= n; i++) if (dp[i][j] >= m && answer > j) answer = j;
        }

        System.out.println(answer == MAX_ANSWER ? 0 : answer);
    }
}