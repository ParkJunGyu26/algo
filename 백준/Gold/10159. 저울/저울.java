import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new boolean[n+1][n+1];

        for (int i = 0; i < m; i++) {
            int a, b;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph[b][a] = true;
        }

        for (int k = 1; k <= n; k++) { // 중간
            for (int i = 1; i <= n; i++) { // 시작
                for (int j = 1; j <= n; j++) { // 도착
                    if (!graph[i][j]) {
                        graph[i][j] = (graph[i][k] && graph[k][j]);
                    }
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            int cnt = 1;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (graph[j][i] || graph[i][j]) cnt++;
            }

            sb.append((n - cnt)).append("\n");
        }

        System.out.println(sb);
    }
}