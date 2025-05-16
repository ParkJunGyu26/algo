import java.util.*;
import java.io.*;

public class Main {
    static int v, e;
    static ArrayList<ArrayList<Node>> graph;
    static int[] parent;

    static class Node implements Comparable<Node> {
        int to, from, cost;

        Node(int to, int from, int cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node e) {
            return this.cost - e.cost;
        }

    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[v+1];
        for (int i = 1; i <= v; i++) parent[i] = i;

        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a, b, c;

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            pq.offer(new Node(a, b, c));
        }

        int answer = 0;
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Node node = pq.poll();
            int to = find(node.to);
            int from = find(node.from);

            if (!isSameParent(to, from)) {
                answer += node.cost;
                union(node.to, node.from);
            }
        }

        System.out.println(answer);
    }
}