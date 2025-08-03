import java.util.*;
import java.io.*;

public class Main {
    static int k;
    static int[][] dp = new int[46][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        dp[1][1] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;

        for (int i = 3; i <= k; i++) {
            for (int j = 0; j <= 1; j++) dp[i][j] = dp[i-1][j] + dp[i-2][j];
        }

        System.out.println(dp[k][0] + " " + dp[k][1]);
    }
}