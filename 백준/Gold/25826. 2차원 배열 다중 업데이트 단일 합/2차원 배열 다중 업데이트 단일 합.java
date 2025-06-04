import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static long[][] A, diff, prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        A = new long[n][n];
        diff = new long[n+2][n+2]; // 2차원 차 배열
        prefixSum = new long[n+1][n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Long.parseLong(st.nextToken());
            }
        }

        for (int q = 0; q < m-1; q++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            // 2차원 차 배열로 구간 업데이트
            diff[y1][x1] += k;
            diff[y1][x2+1] -= k;
            diff[y2+1][x1] -= k;
            diff[y2+1][x2+1] += k;
        }

        // 누적합으로 diff를 실제 값으로 변환
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i > 0) diff[i][j] += diff[i-1][j];
                if (j > 0) diff[i][j] += diff[i][j-1];
                if (i > 0 && j > 0) diff[i][j] -= diff[i-1][j-1];
                A[i][j] += diff[i][j];
            }
        }

        // 2차원 prefix sum 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + A[i-1][j-1];
            }
        }

        // 마지막 쿼리(유형 2)
        st = new StringTokenizer(br.readLine());
        int type = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());

        long ans = prefixSum[y2+1][x2+1] - prefixSum[y1][x2+1] - prefixSum[y2+1][x1] + prefixSum[y1][x1];
        bw.write(ans + "\n");
        bw.flush();
    }
}
