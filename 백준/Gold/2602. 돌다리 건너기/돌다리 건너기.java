import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String magic = br.readLine();
        String devil = br.readLine();
        String angel = br.readLine();

        int L = devil.length();
        int N = magic.length();

        long[][][] dp = new long[N+1][L+1][2];

        for (int i = 0; i < L; i++) {
            if (devil.charAt(i) == magic.charAt(0)) dp[1][i][0] = 1; // 악마
            if (angel.charAt(i) == magic.charAt(0)) dp[1][i][1] = 1; // 천사
        }

        for (int m = 1; m < N; m++) {
            char next = magic.charAt(m);
            for (int i = 0; i < L; i++) {
                if (devil.charAt(i) == magic.charAt(m - 1)) {
                    for (int j = i + 1; j < L; j++) {
                        if (angel.charAt(j) == next) {
                            dp[m+1][j][1] += dp[m][i][0];
                        }
                    }
                }

                if (angel.charAt(i) == magic.charAt(m - 1)) {
                    for (int j = i + 1; j < L; j++) {
                        if (devil.charAt(j) == next) {
                            dp[m+1][j][0] += dp[m][i][1];
                        }
                    }
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < L; i++) {
            answer += dp[N][i][0];
            answer += dp[N][i][1];
        }

        System.out.println(answer);
    }
}
