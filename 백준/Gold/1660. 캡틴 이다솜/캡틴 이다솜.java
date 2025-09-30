import java.io.*;
import java.util.*;

// DP
public class Main {
    static int n, m;
    static int[] dp;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        list = new ArrayList<>();
        int num = 1;
        int plus = 2;
        int prefixSum = num;
        list.add(prefixSum);
        while (prefixSum <= n) {
            num += plus;
            plus++;
            prefixSum += num;
            list.add(prefixSum);
        }

        m = list.size();
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int target = list.get(j);
                if (i < target) break;
                dp[i] = Math.min(dp[i], dp[i-target] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
