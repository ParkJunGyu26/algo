import java.util.*;
import java.io.*;

// 다익스트라 -> O(E + logV)
public class Main {
    static int[] res, globalRes;
    static int MAC_NODE, STAR_NODE, v, e, m, x, s, y, answer = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<Integer> tmp;

    static class Node implements Comparable<Node> {
        int to, dist;

        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return this.dist - node.dist;
        }
    }

    static void dijkstra(int start_node, int limit) {
        int cnt = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] res = new int[v+3];
        for (int i = 0; i <= v+2; i++) res[i] = Integer.MAX_VALUE;
        res[start_node] = 0;
        pq.offer(new Node(start_node, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int node = current.to;
            int dist = current.dist;

            for (Node next : graph.get(node)) {
                int next_node = next.to;
                int plus_dist = next.dist;

                if (res[next_node] > res[node] + plus_dist) {
                    res[next_node] = res[node] + plus_dist;
                    pq.offer(new Node(next_node, res[next_node]));
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            if (res[i] != Integer.MAX_VALUE && res[i] <= limit && res[i] != 0 && globalRes[i] != Integer.MAX_VALUE) globalRes[i] += res[i];
            else globalRes[i] = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        globalRes = new int[v+3];
        for (int i = 1; i <= v+2; i++) globalRes[i] = 0;
        MAC_NODE = v+1;
        STAR_NODE = v+2;
        graph = new ArrayList<>();
        tmp = new ArrayList<>();
        for (int i = 0; i <= v+2; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < e; i++) {
            int to, from, dist;
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
            graph.get(to).add(new Node(from, dist));
        }

        // 맥날
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        if (m > 1) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                graph.get(MAC_NODE).add(new Node(Integer.parseInt(st.nextToken()), 0));
            }
        } else graph.get(MAC_NODE).add(new Node(Integer.parseInt(br.readLine()), 0));

        // 스벅
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        if (s > 1) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < s; i++) {
                graph.get(STAR_NODE).add(new Node(Integer.parseInt(st.nextToken()), 0));
            }
        } else graph.get(STAR_NODE).add(new Node(Integer.parseInt(br.readLine()), 0));

        dijkstra(MAC_NODE, x);
        dijkstra(STAR_NODE, y);

        for (int i = 1; i <= v; i++) {
            answer = Math.min(answer, globalRes[i]);
        }

        System.out.println((answer == Integer.MAX_VALUE) ? -1 : answer);
    }
}