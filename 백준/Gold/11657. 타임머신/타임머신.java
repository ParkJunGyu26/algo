import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] bus;
    static long[] dist;

    private static boolean BellmanFord(int start) {
        dist[start] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int cur = bus[j][0];
                int next = bus[j][1];
                int cost = bus[j][2];

                if (dist[bus[j][0]] == Integer.MAX_VALUE) continue;

                if (dist[next] > (dist[cur] + cost)) {
                    dist[next] = dist[cur] + cost;

                    if (i == n) return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        bus = new int[m][3];
        dist = new long[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            bus[i] = new int[] {a, b, c};
        }

        if (BellmanFord(1)) sb.append(-1);
        else {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE) sb.append(-1);
                else sb.append(dist[i]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}