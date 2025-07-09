import java.io.*;
import java.util.*;

public class Main {
    static int v, m;
    static ArrayList<ArrayList<Edge>> graph;

    static class Edge {
        int to, dist;

        Edge (int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static void dijkstra(int startNode, int[] res) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(res[n1.to], res[n2.to]));
        pq.offer(new Edge(startNode, 0));
        res[startNode] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            for (Edge next : graph.get(edge.to)) {
                int nDist = res[edge.to] + next.dist;

                if (nDist >= res[next.to]) continue;

                res[next.to] = nDist;
                pq.offer(new Edge(next.to, nDist));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int J = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] JR = new int[v+1];
        int[] SR = new int[v+1];

        Arrays.fill(JR, Integer.MAX_VALUE);
        Arrays.fill(SR, Integer.MAX_VALUE);
        
        dijkstra(J, JR);
        dijkstra(S, SR);


        int totalTime = Integer.MAX_VALUE;
        ArrayList<Integer> hubo = new ArrayList<>();
        for (int i = 1; i <= v; i++) {
            if (i == J || i == S) continue;

            totalTime = Math.min(totalTime, JR[i] + SR[i]);
        }

        for (int i = 1; i <= v; i++) {
            if (i == J || i == S) continue;
            if (JR[i] + SR[i] != totalTime) continue;
            if (SR[i] < JR[i]) continue;

            hubo.add(i);
        }
        
        hubo.sort((i1, i2) -> {
            int result = Integer.compare(JR[i1], JR[i2]);
            if (result != 0) return result;
            result = Integer.compare(i1, i2);
            return result;
        });
        System.out.println(hubo.size() == 0 ? -1 : hubo.get(0));
    }
}