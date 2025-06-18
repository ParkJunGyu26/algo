import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    static int[][] dp;
    static int answer = -1000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        dp = new int[n][2];
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            answer = Math.max(answer, arr[i]);
        }

        if (answer < 0) {
            System.out.println(answer);
            return ;
        }

        dp[0][0] = Math.max(0, arr[0]);
        dp[0][1] = (arr[0] > 0) ? arr[0] : 0;

        for (int i = 1; i < n; i++) {
            if (arr[i] < 0) {
                // 제거 안함
                if (arr[i] + dp[i-1][0] <= 0) dp[i][0] = 0;
                else dp[i][0] = arr[i] + dp[i-1][0];

                // 제거
                dp[i][1] = Math.max(dp[i-1][1] + arr[i], dp[i-1][0]);
            } else {
                dp[i][0] = dp[i-1][0] + arr[i];
                dp[i][1] = dp[i-1][1] + arr[i];
            }

            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
10
1 -1 1 -1 1 -1 1 -1 1 -1

3
-1 1 -1

3
-1 -1 1

3
-1 1 1

4
20 -40 -6 40
 */