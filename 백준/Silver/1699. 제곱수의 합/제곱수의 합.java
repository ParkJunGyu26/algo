import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] dp;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n+1];
        Arrays.fill(dp, 100001);
        list = new ArrayList<>();
        int num = 1;
        while (num * num <= n) {
            list.add(num * num);
            dp[num * num] = 1;
            num++;
        }

        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }


        System.out.println(dp[n]);
    }
}