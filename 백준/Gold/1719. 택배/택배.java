import java.util.*;
import java.io.*;

public class Main {
    static String[][] answer;
    static int n, m;
    static ArrayList<ArrayList<Edge>> graph;

    static class Edge {
        int node, dist;

        Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.dist, e2.dist));
        pq.offer(new Edge(start, 0));

        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (distance[cur.node] < cur.dist) continue;

            for (Edge next : graph.get(cur.node)) {
                if (distance[next.node] > distance[cur.node] + next.dist) {
                    if (answer[start-1][cur.node-1] == "-") answer[start-1][next.node-1] = Integer.toString(next.node);
                    else answer[start-1][next.node-1] = answer[start-1][cur.node-1];
                    distance[next.node] = distance[cur.node] + next.dist;
                    pq.offer(new Edge(next.node, distance[next.node]));
                }
            }
        }
    }


    // 다익스트라(우선순위 큐) : E log V
    // 해당 문제의 시간복잡도 -> N * M log N
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        answer = new String[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(answer[i], "-");

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }

        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) bw.write(answer[i][j] + " ");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}