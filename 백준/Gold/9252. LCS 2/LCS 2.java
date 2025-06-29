import java.util.*;
import java.io.*;

public class Main {
    static int[][] dp;
    static String first, second;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        first = br.readLine();
        second = br.readLine();

        dp = new int[first.length()+1][second.length()+1];

        for (int i = 1; i <= first.length(); i++) {
            for (int j = 1; j <= second.length(); j++) {
                if (first.charAt(i-1) == second.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        StringBuilder answer = new StringBuilder();
        int i = first.length();
        int j = second.length();
        while (i != 0 && j != 0) {
            int nowCount = dp[i][j];
            if (dp[i-1][j] == nowCount-1 && dp[i][j-1] == nowCount-1) {
                answer.append(first.charAt(i-1));
                i--;
                j--;
                continue;
            }

            if (nowCount == dp[i-1][j]) i--;
            if (nowCount == dp[i][j-1]) j--;
        }

        if (answer.length() == 0) {
            System.out.println(0);
            return;
        }
        System.out.println(answer.length());
        System.out.println(answer.reverse().toString());
    }
}