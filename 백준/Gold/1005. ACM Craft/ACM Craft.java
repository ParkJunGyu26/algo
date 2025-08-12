import java.io.*;
import java.util.*;

public class Main {
    static int t, n, k, w;
    static int[] res, inDegree;
    static ArrayList<ArrayList<Integer>> graph;

    private static void topologySort(StringBuilder sb) {
        int[] answer = new int[n+1];

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                answer[i] = res[i];
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int next : graph.get(node)) {
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
                answer[next] = Math.max(answer[next], (answer[node] + res[next]));
            }
        }

        sb.append(answer[w]).append("\n");
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            inDegree = new int[n+1];
            res = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) res[i] = Integer.parseInt(st.nextToken());

            for (int i = 0; i < k; i++) {
                int a, b;
                st = new StringTokenizer(br.readLine());

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                graph.get(a).add(b);
                inDegree[b]++;
            }

            w = Integer.parseInt(br.readLine());
            topologySort(sb);
        }
        System.out.println(sb);
    }
}
