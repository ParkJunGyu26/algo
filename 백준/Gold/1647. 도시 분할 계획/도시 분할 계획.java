import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent;

    static class Node implements Comparable<Node> {
        int from, to, cost;

        Node(int f, int t, int c) {
            from = f;
            to = t;
            cost = c;
        }

        // 비용 기준으로 오름차순 정렬 -> 크루스칼 알고리즘에서 엣지 비용이 낮은 것부터 처리하기 위함
        @Override
        public int compareTo(Node no) {
            return this.cost - no.cost;
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

    static boolean isParentSame(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 1; i < n+1; i++) parent[i] = i;

        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, c));
        }

        int answer = 0;
        int size = pq.size();
        int max_cost = 0;
        for (int i = 0; i < size; i++) {
            Node node = pq.poll();
            int from = find(node.from);
            int to = find(node.to);
    
            if (isParentSame(from, to)) continue;
    
            union(node.from, node.to);
            answer += node.cost;
            max_cost = Math.max(node.cost, max_cost);
        }

        System.out.println(answer - max_cost);
    }
}