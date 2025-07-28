import java.util.*;
import java.io.*;

// 위상정렬 + DP
public class Main {
    static int n, m, answer = 0;
    static ArrayList<ArrayList<Integer>> smaller, taller;

    static int bfs(int node, ArrayList<ArrayList<Integer>> info) {
        boolean visited[] = new boolean[n+1];
        visited[node] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        int cnt = 0;

        while (!q.isEmpty()) {
            int now = q.poll();
            ArrayList<Integer> graph = info.get(now);

            for (int next : graph) {
                if (visited[next]) continue;

                visited[next] = true;
                q.offer(next);
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        smaller = new ArrayList<>();
        taller = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            smaller.add(new ArrayList<>());
            taller.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            taller.get(a).add(b);
            smaller.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            int tall = bfs(i, taller);
            int small = bfs(i, smaller);

            if (tall + small + 1 == n) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}