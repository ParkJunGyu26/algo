import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] graph;
    static int[][][] dp; // {길이, 기계 숫자}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n][n][2];
        graph = new char[n][n];
        int total = 0;

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = tmp.charAt(j);
                if (graph[i][j] == '1') total++;
            }
        }

        dp[0][0][0] = 1;
        dp[0][0][1] = (graph[0][0] == '1') ? 1 : 0;

        // 첫 행
        for (int j = 1; j < n; j++) {
            dp[0][j][0] = dp[0][j-1][0] + 1;
            dp[0][j][1] = dp[0][j-1][1] + ((graph[0][j] == '1') ? 1 : 0);
        }

        int[][] leftToRight = new int[n][2];
        int[][] rightToLeft = new int[n][2];

        for (int i = 1; i < n; i++) {
            // 왼쪽 → 오른쪽
            leftToRight[0][0] = dp[i-1][0][0] + 1;
            leftToRight[0][1] = dp[i-1][0][1] + ((graph[i][0] == '1') ? 1 : 0);
            for (int j = 1; j < n; j++) {
                int cnt = (graph[i][j] == '1') ? 1 : 0;
                if (leftToRight[j-1][1] < dp[i-1][j][1]) {
                    leftToRight[j][0] = dp[i-1][j][0] + 1;
                    leftToRight[j][1] = dp[i-1][j][1] + cnt;
                } else if (leftToRight[j-1][1] > dp[i-1][j][1]) {
                    leftToRight[j][0] = leftToRight[j-1][0] + 1;
                    leftToRight[j][1] = leftToRight[j-1][1] + cnt;
                } else {
                    if (leftToRight[j-1][0] > dp[i-1][j][0]) {
                        leftToRight[j][0] = dp[i-1][j][0] + 1;
                        leftToRight[j][1] = dp[i-1][j][1] + cnt;
                    } else {
                        leftToRight[j][0] = leftToRight[j-1][0] + 1;
                        leftToRight[j][1] = leftToRight[j-1][1] + cnt;
                    }
                }
            }

            // 오른쪽 → 왼쪽
            rightToLeft[n-1][0] = dp[i-1][n-1][0] + 1;
            rightToLeft[n-1][1] = dp[i-1][n-1][1] + ((graph[i][n-1] == '1') ? 1 : 0);
            for (int j = n-2; j >= 0; j--) {
                int cnt = (graph[i][j] == '1') ? 1 : 0;
                if (rightToLeft[j+1][1] < dp[i-1][j][1]) {
                    rightToLeft[j][0] = dp[i-1][j][0] + 1;
                    rightToLeft[j][1] = dp[i-1][j][1] + cnt;
                } else if (rightToLeft[j+1][1] > dp[i-1][j][1]) {
                    rightToLeft[j][0] = rightToLeft[j+1][0] + 1;
                    rightToLeft[j][1] = rightToLeft[j+1][1] + cnt;
                } else {
                    if (rightToLeft[j+1][0] > dp[i-1][j][0]) {
                        rightToLeft[j][0] = dp[i-1][j][0] + 1;
                        rightToLeft[j][1] = dp[i-1][j][1] + cnt;
                    } else {
                        rightToLeft[j][0] = rightToLeft[j+1][0] + 1;
                        rightToLeft[j][1] = rightToLeft[j+1][1] + cnt;
                    }
                }
            }

            // dp 업데이트: 칸별로 최적값 선택
            for (int j = 0; j < n; j++) {
                if (leftToRight[j][1] > rightToLeft[j][1] || 
                    (leftToRight[j][1] == rightToLeft[j][1] && leftToRight[j][0] <= rightToLeft[j][0])) {
                    dp[i][j][0] = leftToRight[j][0];
                    dp[i][j][1] = leftToRight[j][1];
                } else {
                    dp[i][j][0] = rightToLeft[j][0];
                    dp[i][j][1] = rightToLeft[j][1];
                }
            }
        }

        System.out.println(dp[n-1][n-1][1] == total ? dp[n-1][n-1][0] : -1);
    }
}
