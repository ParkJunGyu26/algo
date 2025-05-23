import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] grape;
    static long[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        grape = new int[n];
        for (int i = 0; i < n; i++) grape[i] = Integer.parseInt(br.readLine());

        if (n < 3) {
            int answer = 0;
            for (int g : grape) answer += g;
            bw.write(String.valueOf(answer));
            bw.flush();
            bw.close();
            br.close();

            return;
        }
        
        dp = new long[4];
        dp[0] = grape[0] + grape[1];
        dp[1] = grape[0] + grape[2];
        dp[2] = grape[1] + grape[2];
        dp[3] = grape[0];

        for (int i = 3; i < n; i++) {
            long zero = dp[0];      // __OX
            long one = dp[1];       // __XO
            long two = dp[2];       // __OO
            long three = dp[3];     // __XX

            dp[0] = Math.max(one, two);
            dp[1] = Math.max(zero, three) + grape[i];
            dp[2] = one + grape[i];
            dp[3] = zero;
        }

        long answer = Math.max(dp[0], Math.max(dp[1], Math.max(dp[2], dp[3])));

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}