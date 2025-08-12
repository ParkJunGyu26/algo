import java.io.*;
import java.util.*;

// MST : 최소신장트리
public class Main {
    static int n, m;
    static long answer = 0;
    static int[] parent;
    static ArrayList<ArrayList<int[]>> graph;
    static PriorityQueue<int[]> pq;

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean unionFind(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (a < b) parent[b] = a;
        else parent[a] = b;

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[2], i2[2]));

        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            pq.offer(new int[] {a, b, c});
            answer += c;
        }

        for (int i = 0; i < m; i++) {
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int cost = cur[2];

            if(unionFind(a, b)) {
                answer -= cost;
            }
        }

        for (int i = 1; i <= n-1; i++) if (find(i) != find(i+1)) answer = -1;

        System.out.println(answer);
    }
}