import java.io.*;
import java.util.*;

public class Main {
    static int n, m, t, k, a, b;
    static char[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) graph[i][j] = str.charAt(j);
        }

        while (t-- > 0) {
            char[][] nextGraph = new char[n][m];
            int[][] prefixSum = new int[n+1][m+1];

            for (int i = 0; i < n; i++) Arrays.fill(nextGraph[i], '.');

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + (graph[i-1][j-1] == '*' ? 1: 0);
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    int target = prefixSum[Math.min(n, i+k)][Math.min(m, j+k)] - prefixSum[Math.min(n, i+k)][Math.max(0, j-k-1)] - prefixSum[Math.max(0, i-k-1)][Math.min(m, j+k)] + prefixSum[Math.max(0, i-k-1)][Math.max(0, j-k-1)];

                    if (graph[i-1][j-1] == '*') {
                        target--;
                        if (a <= target && target <= b) nextGraph[i-1][j-1] = '*';
                    } else {
                        if (a < target && target <= b) nextGraph[i-1][j-1] = '*';
                    }
                }
            }

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) graph[i][j] = nextGraph[i][j];

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) sb.append(graph[i][j]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}