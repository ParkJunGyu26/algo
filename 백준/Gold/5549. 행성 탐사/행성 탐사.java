import java.util.*;
import java.io.*;

public class Main {
    static char[][] graph;
    static int n, m, k;
    static Triple[][] prefixSum;

    static class Triple {
        int jungle, sea, ice;
        Triple(int j, int s, int i) {
            jungle = j;
            sea = s;
            ice = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(br.readLine());

        graph = new char[n][m];
        prefixSum = new Triple[n+1][m+1];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) graph[i][j] = tmp.charAt(j);
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                prefixSum[i][j] = new Triple(0, 0, 0);
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (graph[i-1][j-1] == 'J') {
                    prefixSum[i][j].jungle = prefixSum[i-1][j].jungle + prefixSum[i][j-1].jungle + 1 - prefixSum[i-1][j-1].jungle;
                    prefixSum[i][j].sea = prefixSum[i-1][j].sea + prefixSum[i][j-1].sea - prefixSum[i-1][j-1].sea;
                    prefixSum[i][j].ice = prefixSum[i-1][j].ice + prefixSum[i][j-1].ice - prefixSum[i-1][j-1].ice;
                } else if (graph[i-1][j-1] == 'I') {
                    prefixSum[i][j].ice = prefixSum[i-1][j].ice + prefixSum[i][j-1].ice + 1 - prefixSum[i-1][j-1].ice;
                    prefixSum[i][j].sea = prefixSum[i-1][j].sea + prefixSum[i][j-1].sea - prefixSum[i-1][j-1].sea;
                    prefixSum[i][j].jungle = prefixSum[i-1][j].jungle + prefixSum[i][j-1].jungle - prefixSum[i-1][j-1].jungle;
                } else {
                    prefixSum[i][j].sea = prefixSum[i-1][j].sea + prefixSum[i][j-1].sea + 1 - prefixSum[i-1][j-1].sea;
                    prefixSum[i][j].ice = prefixSum[i-1][j].ice + prefixSum[i][j-1].ice - prefixSum[i-1][j-1].ice;
                    prefixSum[i][j].jungle = prefixSum[i-1][j].jungle + prefixSum[i][j-1].jungle - prefixSum[i-1][j-1].jungle;
                }
            }
        }
        // System.out.println(prefixSum[2][4].jungle);

        for (int i = 0; i < k; i++) {
            int a, b, c, d;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            bw.write((prefixSum[c][d].jungle - prefixSum[a-1][d].jungle - prefixSum[c][b-1].jungle + prefixSum[a-1][b-1].jungle) + " ");
            bw.write((prefixSum[c][d].sea - prefixSum[a-1][d].sea - prefixSum[c][b-1].sea + prefixSum[a-1][b-1].sea) + " ");
            bw.write((prefixSum[c][d].ice - prefixSum[a-1][d].ice - prefixSum[c][b-1].ice + prefixSum[a-1][b-1].ice) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}