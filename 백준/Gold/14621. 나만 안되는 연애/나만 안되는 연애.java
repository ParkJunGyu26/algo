import java.io.*;
import java.util.*;

// MST + 조건
public class Main {
    static char[] status;
    static int n, m, answer = 0;
    static int[] parent;
    static PriorityQueue<int[]> pq;

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        status = new char[n+1];
        pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[2], i2[2]));

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            status[i] = st.nextToken().charAt(0);
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.offer(new int[] {a, b, c});
        }

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int a = find(cur[0]);
            int b = find(cur[1]);
            int cost = cur[2];

            if (a == b || status[cur[0]] == status[cur[1]]) continue;

            union(a, b);
            answer += cost;
        }

        for (int i = 1; i <= n-1; i++) {
            if (find(i) != find(i+1)) answer = -1;
        }

        System.out.println(answer);
    }
}