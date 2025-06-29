import java.util.*;
import java.io.*;

public class Main {
    static int[][] jungleSum, seaSum, iceSum;
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        jungleSum = new int[n + 1][m + 1];
        seaSum = new int[n + 1][m + 1];
        iceSum = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String row = br.readLine();
            for (int j = 1; j <= m; j++) {
                char terrain = row.charAt(j - 1);

                int isJungle = terrain == 'J' ? 1 : 0;
                int isSea    = terrain == 'O' ? 1 : 0;
                int isIce    = terrain == 'I' ? 1 : 0;

                jungleSum[i][j] = jungleSum[i - 1][j] + jungleSum[i][j - 1] - jungleSum[i - 1][j - 1] + isJungle;
                seaSum[i][j]    = seaSum[i - 1][j]    + seaSum[i][j - 1]    - seaSum[i - 1][j - 1]    + isSea;
                iceSum[i][j]    = iceSum[i - 1][j]    + iceSum[i][j - 1]    - iceSum[i - 1][j - 1]    + isIce;
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int jungle = getSum(jungleSum, a, b, c, d);
            int sea = getSum(seaSum, a, b, c, d);
            int ice = getSum(iceSum, a, b, c, d);

            bw.write(jungle + " " + sea + " " + ice + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int getSum(int[][] sum, int a, int b, int c, int d) {
        return sum[c][d] - sum[a - 1][d] - sum[c][b - 1] + sum[a - 1][b - 1];
    }
}
