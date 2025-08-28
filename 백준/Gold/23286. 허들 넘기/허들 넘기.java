import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1_000_001;
    static int n, m, t;
    static int[][] dist;
    static ArrayList<ArrayList<int[]>> graph;
    static StringBuilder sb = new StringBuilder();

    private static void dijkstra(int start) {
        int[] res = new int[n+1];
        Arrays.fill(res, MAX_VALUE);
        res[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1])); 
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int nowNode = cur[0];
            int nowDist = cur[1];

            for (int[] next : graph.get(nowNode)) {
                int nextNode = next[0];
                int nextDist = next[1];

                int max = Math.max(nextDist, nowDist);

                if (res[nextNode] > max) {
                    res[nextNode] = max;
                    pq.offer(new int[] {nextNode, max});
                }
            }
        }

        for (int i = 1; i <= n; i++) dist[start][i] = res[i];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        dist = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], -1);
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int u, v, h;
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            graph.get(u).add(new int[] {v, h});
        }

        for (int i = 1; i <= n; i++) dijkstra(i);

        for (int i = 0; i < t; i++) {
            int s, e;
            st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            sb.append(dist[s][e] == MAX_VALUE ? -1 : dist[s][e]).append("\n");
        }
        System.out.println(sb);
    }
}