import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] res;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        int to, dist;

        Node (int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static void dijkstra() {
        res = new int[n+1];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.dist, n2.dist));
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int node = cur.to;
            
            if (node == n) {
                System.out.println(res[node]);
                return;
            }

            for (Node next : graph.get(node)) {
                int nNode = next.to;
                if (res[nNode] > res[node] + next.dist) {
                    res[nNode] = res[node] + next.dist;
                    pq.offer(new Node(nNode, res[nNode]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    
        dijkstra();
    }
}
