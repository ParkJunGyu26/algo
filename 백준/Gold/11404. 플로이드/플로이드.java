import java.util.*;
import java.io.*;

public class Main {
    static int INF = 10_000_001;
    static int n, m;
    static int[][] answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        answer = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) Arrays.fill(answer[i], INF);

        for (int i = 0; i < m; i++) {
            int a, b, c;
            StringTokenizer st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            answer[a][b] = Math.min(answer[a][b], c);
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) answer[i][j] = 0;
                    else answer[i][j] = Math.min(answer[i][j], answer[i][k] + answer[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (answer[i][j] == INF) bw.write(0 + " ");
                else bw.write(answer[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}