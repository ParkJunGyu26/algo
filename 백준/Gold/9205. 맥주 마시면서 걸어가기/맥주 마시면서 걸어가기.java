import java.util.*;
import java.io.*;

public class Main {
    static final int PLUS = 32768;
    static int t, n, x, y;
    static ArrayList<Dot> dot;
    static ArrayList<ArrayList<Node>> graph;

    static class Dot {
        int x, y;

        Dot (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int target;
        int dist;

        Node(int target, int dist) {
            this.target = target;
            this.dist = dist;
        }
    }

    static void inputDot(BufferedReader br) throws IOException {
        dot = new ArrayList<>();
        for (int i = 0; i < n+2; i++) {
            int x, y;
            StringTokenizer st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken()) + PLUS;
            y = Integer.parseInt(st.nextToken()) + PLUS;

            dot.add(new Dot(x, y));
        }
    }

    static void makeGraph() {
        graph = new ArrayList<>();
        for (int i = 0; i < n+2; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n+2; i++) {
            Dot start = dot.get(i);
            for (int j = i+1; j < n+2; j++) {
                Dot end = dot.get(j);
                int dist = Math.abs(start.x - end.x) + Math.abs(start.y - end.y);

                graph.get(i).add(new Node(j, dist));
                graph.get(j).add(new Node(i, dist));
            }
        }
    }

    static boolean solve() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n+2];
        Arrays.fill(visited, false);
        visited[0] = true;
        q.offer(0);

        while (!q.isEmpty()) {
            int nowDot = q.poll();

            if (nowDot == n+1) return true;

            for (Node next : graph.get(nowDot)) {
                int nextDot = next.target;
                int dist = next.dist;

                if (!visited[nextDot] && dist <= 50*20) {
                    visited[nextDot] = true;
                    q.offer(nextDot);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            inputDot(br);
            makeGraph();
            if(solve()) bw.write("happy\n");
            else bw.write("sad\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }
}