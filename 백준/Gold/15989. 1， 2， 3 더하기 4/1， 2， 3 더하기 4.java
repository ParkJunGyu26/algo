import java.util.*;
import java.io.*;

public class Main {
    static int t, n;
    static boolean[] num;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());
        dp = new long[10001];
        num = new boolean[10001];
        dp[1] = 1L;
        dp[2] = 2L;
        dp[3] = 3L;
        for (int i = 4; i < 10001; i++) {
            dp[i] = dp[i-1];
            if (i % 2 == 0) dp[i]++;
            
            int num = i;
            while (num >= 2) {
                num -= 3;
                if (num == 3) {
                    dp[i]++;
                    break;
                }
                if (num % 2 == 0) dp[i]++;
            }
        }

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());

            bw.write(dp[n] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}