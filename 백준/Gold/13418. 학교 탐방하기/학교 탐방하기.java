import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] parent;
    static PriorityQueue<Node> best_pq, bad_pq;

    static class Node implements Comparable<Node> {
        int from, to, cost;

        Node(int f, int t, int c) {
            from = f;
            to = t;
            cost = c;
        }

        @Override
        public int compareTo(Node node) { // 오름차순(최소 힙)
            return node.cost - this.cost;
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return (parent[x] = find(parent[x]));
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parent[x] = y;
        else parent[y] = x; 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int MAX = 0;
        int MIN = 0;

        parent = new int[n+1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        // 0 : 오름막길(안좋음), 1 : 내리막길(좋음)
        best_pq = new PriorityQueue<>(); // 내림차순(최대 힙)
        bad_pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a == 0) {
                if (c == 0) MAX = MIN = 1;
                continue;
            }
            best_pq.offer(new Node(a, b, c));
            best_pq.offer(new Node(b, a, c));
            bad_pq.offer(new Node(a, b, c));
            bad_pq.offer(new Node(b, a, c));
        }

        while (!best_pq.isEmpty()) {
            Node current = best_pq.poll();
            int x = find(current.from);
            int y = find(current.to);

            if (x == y) continue;

            union(current.from, current.to);

            if (current.cost == 0) {
                MAX++;
            }
        }

        for (int i = 0; i <= n; i++) parent[i] = i;
        while (!bad_pq.isEmpty()) {
            Node current = bad_pq.poll();
            int x = find(current.from);
            int y = find(current.to);

            if (x == y) continue;

            union(current.from, current.to);

            if (current.cost == 0) MIN++;
        }

        System.out.println(Math.abs(MAX*MAX - MIN*MIN));
    }
}