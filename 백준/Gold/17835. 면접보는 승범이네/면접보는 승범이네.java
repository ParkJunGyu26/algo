import java.util.*;
import java.io.*;

// 다익스트라 -> O(E + logV)
public class Main {
    static boolean[] interview;
    static int n, m, k;
    static ArrayList<Integer> hubo;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        int to;
        long cost;

        Node (int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Long.compare(n1.cost, n2.cost));
        long[] res = new long[n+2];
        boolean[] interview = new boolean[n+2];
        Arrays.fill(res, Long.MAX_VALUE);
        Arrays.fill(interview, false);

        for (int index : hubo) {
            pq.offer(new Node(index, 0));
            res[index] = 0;
            interview[index] = true;
        }

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int node = current.to;

            if (res[node] < current.cost) continue;

            for (Node next : graph.get(node)) {
                int nextNode = next.to;
                long nextCost = next.cost;

                if (res[nextNode] > res[node] + nextCost && !interview[nextNode]) {
                    res[nextNode] = res[node] + nextCost;
                    pq.offer(new Node(nextNode, res[nextNode]));
                }
            }
        }

        int index = 0;
        long answer = 0;
        for (int i = n; i >= 1; i--) {
            if (answer <= res[i] && !interview[i]) {
                answer = res[i];
                index = i;
            }
        }

        System.out.print(index + "\n" + answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n+1; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int from, to, cost;
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            graph.get(to).add(new Node(from, cost));
        }

        st = new StringTokenizer(br.readLine());
        hubo = new ArrayList<>();
        for (int i = 0; i < k; i++) hubo.add(Integer.parseInt(st.nextToken()));

        dijkstra();
    }
}