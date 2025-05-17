import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<Node> graph;
    static ArrayList<Info> move;
    static long answer = 0;
    static int[] parent;

    static class Node {
        int x, y, z, index;

        Node(int a, int b, int c, int i) {
            x = a;
            y = b;
            z = c;
            index = i;
        }
    }

    static class Info {
        int from, to, cost;

        Info(int f, int t, int c) {
            from = f;
            to = t;
            cost = c;
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

    static void solve() {
        graph.sort((n1, n2) -> n1.x - n2.x); // x 기준 오름차순

        for (int i = 0; i < n-1; i++) {
            Node n1 = graph.get(i+1);
            Node n2 = graph.get(i);

            move.add(new Info(n1.index, n2.index, Math.abs(n1.x - n2.x)));
        }

        graph.sort((n1, n2) -> n1.y - n2.y); // y 기준 오름차순

        for (int i = 0; i < n-1; i++) {
            Node n1 = graph.get(i+1);
            Node n2 = graph.get(i);

            move.add(new Info(n1.index, n2.index, Math.abs(n1.y - n2.y)));
        }

        graph.sort((n1, n2) -> n1.z - n2.z); // z 기준 오름차순

        for (int i = 0; i < n-1; i++) {
            Node n1 = graph.get(i+1);
            Node n2 = graph.get(i);

            move.add(new Info(n1.index, n2.index, Math.abs(n1.z - n2.z)));
        }

        move.sort((n1, n2) -> n1.cost - n2.cost);
        for (int i = 0; i < move.size(); i++) {
            Info current = move.get(i);
            int x = find(current.from);
            int y = find(current.to);

            if (x == y) continue;

            union(current.from, current.to);

            answer += current.cost;
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        graph = new ArrayList<>();
        move = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x, y, z;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            graph.add(new Node(x, y, z, i+1));
        }

        solve();

        // for (Node node : graph) {
        //     System.out.println("x : " + node.x + ", y : " + node.y + ", z : " + node.z);
        // }
    }
}