import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dp;
    static String S;
    static HashMap<String, Integer> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        m = Integer.parseInt(br.readLine());
        n = S.length();

        hm = new HashMap<>();
        dp = new int[n+1];

        for (int i = 0; i < m; i++) {
            String a;
            int x;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = st.nextToken();
            x = Integer.parseInt(st.nextToken());

            if (hm.getOrDefault(a, 0) < x) hm.put(a, x);
        }

        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();

            for (int j = i; j <= n; j++) {
                sb.append(S.charAt(j-1));
                String target = sb.toString();

                if (hm.containsKey(target)) {
                    dp[j] = Math.max(dp[j], dp[i-1] + Math.max((j-i+1), hm.get(target)));
                } else {
                    dp[j] = Math.max(dp[j-1]+1, dp[j]);
                }
            }
        }

        System.out.println(dp[n]);
    }
}