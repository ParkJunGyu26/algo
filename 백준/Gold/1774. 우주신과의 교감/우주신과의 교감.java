import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent;
    static ArrayList<Dot> dots;
    static PriorityQueue<Node> graph;

    static class Dot {
        int x, y;

        Dot (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int from;
        int to;
        double dist;

        Node (int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

    // mst
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        dots = new ArrayList<>();
        dots.add(new Dot(-1, -1));
        for (int i = 0; i < n; i++) {
            int x, y;
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            
            dots.add(new Dot(x, y));
        }

        graph = new PriorityQueue<>((g1, g2) -> Double.compare(g1.dist, g2.dist));

        for (int i = 1; i <= n; i++) {
            Dot start = dots.get(i);
            for (int j = i+1; j <= n; j++) {
                Dot end = dots.get(j);

                long xx = (long) Math.pow(Math.max(start.x, end.x) - Math.min(start.x, end.x), 2);
                long yy = (long) Math.pow(Math.max(start.y, end.y) - Math.min(start.y, end.y), 2);
                double d = Math.sqrt(yy+xx);

                // 양방향 고려
                graph.add(new Node(i, j, d));
                graph.add(new Node(j, i, d));
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1, n2;
            n1 = Integer.parseInt(st.nextToken());
            n2 = Integer.parseInt(st.nextToken());
            
            union(n1, n2);
        }

        double answer = 0;
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            Node cur = graph.poll();
            int x = find(cur.from);
            int y = find(cur.to);

            if (x == y) continue;

            answer += cur.dist;

            union(cur.from, cur.to);
        }

        String ans = String.format("%.2f", answer);
        bw.write(ans);
        bw.flush();
        bw.close();
        br.close();
    }
}