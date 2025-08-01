import java.util.*;
import java.io.*;

// O(N * A) -> A 는 제곱수의 개수(최대 350)
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

        for (int i = 2; i <= n; i++) {
            for (int mulNum : list) {
                if (mulNum > i) break;
                dp[i] = Math.min(dp[i], dp[mulNum] + dp[i - mulNum]);
            }
        }

        System.out.println(dp[n]);
    }
}