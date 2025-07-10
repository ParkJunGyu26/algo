import java.io.*;
import java.util.*;

public class Main {
    static int T;

    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static void dijkstra(int start, int[][] res, ArrayList<ArrayList<Edge>> graph, int status) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.dist, e2.dist));
        pq.offer(new Edge(start, 0));
        res[start][status] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge next : graph.get(cur.to)) {
                if (res[next.to][status] > res[cur.to][status] + next.dist) {
                    Edge edge = new Edge(next.to, res[next.to][status]);
                    res[next.to][status] = res[cur.to][status] + next.dist;
                    pq.offer(edge);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // 초기화(INPUT)
            int n, m, t, s, g, h;
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            ArrayList<Integer> hubo = new ArrayList<>();
            ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
            for (int i = 0; i < m; i++) {
                int a, b, d;
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                // 양방향
                graph.get(a).add(new Edge(b, d));
                graph.get(b).add(new Edge(a, d));
            }

            // 목적지 후보는 오름차순으로
            for (int i = 0; i < t; i++) hubo.add(Integer.parseInt(br.readLine()));
            hubo.sort((i1, i2) -> Integer.compare(i1, i2));

            // 다익스트라 거리 배열
            int[][] res = new int[n+1][3];
            for (int i = 0; i <= n; i++) Arrays.fill(res[i], Integer.MAX_VALUE);

            // s 에서 다익스트라
            dijkstra(s, res, graph, 0);
    
            // g 에서 다익스트라
            dijkstra(g, res, graph, 1);

            // h 에서 다익스트라
            dijkstra(h, res, graph, 2);

            for (int node : hubo) {
                if (res[node][0] == res[g][0] + res[h][1] + res[node][2] || res[node][0] == res[h][0] + res[g][2] + res[node][1]) bw.write(node + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}