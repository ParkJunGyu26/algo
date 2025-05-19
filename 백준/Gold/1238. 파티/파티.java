import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

// 다익스트라 -> O(E + logV)
public class Main {
    static int n, m, x;
    static ArrayList<ArrayList<Node>> graph;
    static int[] res;

    static class Node implements Comparable<Node> {
        int to, cost;

        Node(int t, int c) {
            to = t;
            cost = c;
        }

        @Override
        public int compareTo(Node n1) {
            return this.cost - n1.cost;
        }
    }

    static int dijkstra(int start, int target) {
        res = new int[n+1];
        for (int i = 1; i <= n; i++) res[i] = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        res[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int node = current.to;
            int cost = current.cost;

            for (Node next : graph.get(node)) {
                int nNode = next.to;
                int nCost = next.cost;

                if (res[nNode] <= cost + nCost) continue;

                res[nNode] = cost + nCost;
                if (nNode != target) pq.offer(new Node(nNode, res[nNode]));
            }
        }

        return res[target];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int u, v, p;
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, p));
        }

        for (int i = 1; i <= n; i++) {
            graph.get(i).sort((a, b) -> a.cost - b.cost);
        }

        int cnt = 0;
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (i == x) continue;
            cnt += dijkstra(i, x);
            cnt += dijkstra(x, i);

            answer = Math.max(cnt, answer);
            cnt = 0;
        }

        System.out.println(answer);
    }
}