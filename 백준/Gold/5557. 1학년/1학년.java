import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[21];

        dp[arr[0]] = 1;

        for (int i = 1; i < n - 1; i++) {
            long[] newDp = new long[21];

            for (int currentNum = 0; currentNum <= 20; currentNum++) {
                if (dp[currentNum] == 0) continue;

                int plusResult = currentNum + arr[i];
                if (plusResult >= 0 && plusResult <= 20) {
                    newDp[plusResult] += dp[currentNum];
                }

                int minusResult = currentNum - arr[i];
                if (minusResult >= 0 && minusResult <= 20) {
                    newDp[minusResult] += dp[currentNum];
                }
            }
            System.arraycopy(newDp, 0, dp, 0, 21);
        }

        bw.write(dp[arr[n - 1]] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}