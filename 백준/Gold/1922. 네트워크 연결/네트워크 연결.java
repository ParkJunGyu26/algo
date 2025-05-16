import java.util.*;
import java.io.*;

// 크루스칼
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

        // 모든 간선들을 오름차순 정렬 -> 가중치 작은 간선부터 선택하기 위함
        @Override
        public int compareTo(Node no) {
            return this.cost - no.cost;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    // 유니온 파인드에서는 작은 쪽으로 붙이는 걸로 했는데 여기엔 고려 안해도 되나..?
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

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        Queue<Node> pq = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.offer(new Node(a, b, c));
        }

        int answer = 0;
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int to = find(current.to);
            int from = find(current.from);

            if (isParentSame(to, from)) continue; // 유니온 파인드로 정점 동일하면 스킵

            answer += current.cost;
            union(current.to, current.from);
        }

        System.out.println(answer);
    }
}