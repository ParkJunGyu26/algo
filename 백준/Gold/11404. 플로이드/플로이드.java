import java.io.*;
import java.util.*;

public class Main {
    // 10^12
    static final long MAX_DISTANCE = 100L*100_000*100_000 + 1;

    static int n, m;
    static long[][] dist;
    static ArrayList<ArrayList<int[]>> graph;

    // 플로이드 워셜 : O(N^3)
    // N * 다익스트라 : O(N * M log N)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        dist = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], MAX_DISTANCE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int a, b, c;
            StringTokenizer st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            dist[a][b] = Math.min(dist[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append((dist[i][j] == MAX_DISTANCE ? 0 : dist[i][j])).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}