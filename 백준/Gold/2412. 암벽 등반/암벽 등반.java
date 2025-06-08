import java.util.*;
import java.io.*;

public class Main {
    static int n, t;
    static int[] dx = {-2, -2, -2, -1, -1, -1, 0, 0, 1, 1, 1, 2, 2, 2, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2}, dy = {0, 1, 2, 0, 1, 2, 1, 2, 0, 1, 2, 0, 1, 2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2};
    static HashMap<Node, Integer> hm;

    static class Node {
        int x, y;

        Node(int x, int y) {this.x = x; this.y = y;}

        @Override
        public boolean equals(Object o) {
            if (o instanceof Node) {
                Node node = (Node) o;
                return (this.x == node.x) && (this.y == node.y);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x <= 1000000 && -1 < y && y <= t);
    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        Node start = new Node(0, 0);
        hm.put(start, 0);
        q.offer(start);

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            if (y == t) {
                return hm.get(cur);
            }

            for (int i = 0; i < 24; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!inRange(nx, ny)) continue;

                
                Node nNode = new Node(nx, ny);
                if (!hm.containsKey(nNode) || hm.get(nNode) != 0) continue;

                q.offer(nNode);
                hm.put(nNode, hm.get(cur)+1);
            }
        }

        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x, y;
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            hm.put(new Node(x, y), 0);
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
        br.close();
    }
}