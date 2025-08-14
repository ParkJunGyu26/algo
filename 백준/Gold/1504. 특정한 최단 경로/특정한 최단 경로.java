import java.io.*;
import java.util.*;

public class Main {
    static final long MAX_DISTANCE = 800L * 200_000 * 1_000 + 1;

    static long answer;
    static int n, e, v1, v2;
    static ArrayList<ArrayList<int[]>> graph;

    static class Node {
        int node;
        long dist;

        Node(int node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    private static long dijkstra(int start, int end) {
        long[] res = new long[n+1];
        Arrays.fill(res, MAX_DISTANCE);
        res[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Long.compare(n1.dist, n2.dist));
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            int node = cur.node;
            long dist = cur.dist;

            if (res[node] < dist) continue;

            for (int info[] : graph.get(node)) {
                int nextNode = info[0];
                int nextDist = info[1];

                if (res[nextNode] > res[node] + nextDist) {
                    res[nextNode] = res[node] + nextDist;
                    pq.offer(new Node(nextNode, res[nextNode]));
                }
            }
        }

        return res[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < e; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, c});
            graph.get(b).add(new int[] {a, c});
        }

        st = new StringTokenizer(br.readLine());

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        long oneToV1 = dijkstra(1, v1);
        long oneToV2 = dijkstra(1, v2);

        long v1ToV2 = dijkstra(v1, v2);

        long v1ToN = dijkstra(v1, n);
        long v2ToN = dijkstra(v2, n);

        long first = oneToV1 + v1ToV2 + v2ToN;
        long second = oneToV2 + v1ToV2 + v1ToN;

        if (first >= MAX_DISTANCE && second >= MAX_DISTANCE) answer = -1;
        else answer = Math.min(first, second);
        
        System.out.println(answer);
    }
}