import java.util.*;
import java.io.*;

// 최소신장트리
public class Main {
    static int t, n, m, p, q;

    static int find(int x, int[] parent) {
        if (parent[x] == x) return x;
        return (parent[x] = find(parent[x], parent)); // 경로 압축
    }

    static void union(int a, int b, int[] parent) {
        a = find(a, parent);
        b = find(b, parent);

        if (a == b) return;

        if (a > b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            // {거리, 출발, 도착}
            // 양방향
            PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[0], i2[0]));
            int[] parent = new int[n+1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            String answer = "NO";
            for (int i = 0; i < m; i++) {
                int u, v, w;
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());

                pq.offer(new int[] {w, u, v});
            }

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();

                int start = cur[1];
                int end = cur[2];

                if (find(start, parent) == find(end, parent)) continue;

                if (Math.min(start, end) == Math.min(p, q) && Math.max(start, end) == Math.max(p, q)) {
                    answer = "YES";
                }

                union(start, end, parent);
            }

            System.out.println(answer);
        }
    }
}