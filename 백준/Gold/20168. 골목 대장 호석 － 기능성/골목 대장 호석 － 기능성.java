import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int N, M, A, B, C;
    static List<List<Edge>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        int maxCost = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Edge(v, cost));
            graph.get(v).add(new Edge(u, cost));
            maxCost = Math.max(maxCost, cost);
        }

        int left = 1, right = maxCost;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canReachWithMaxToll(mid)) {
                answer = mid;
                right = mid - 1; // 더 작은 수치심으로 시도
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canReachWithMaxToll(int maxToll) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        boolean[] visited = new boolean[N + 1];
        pq.offer(new int[]{A, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], costSum = cur[1];

            if (node == B) return true;
            if (visited[node]) continue;
            visited[node] = true;

            for (Edge e : graph.get(node)) {
                if (!visited[e.to] && e.cost <= maxToll && costSum + e.cost <= C) {
                    pq.offer(new int[]{e.to, costSum + e.cost});
                }
            }
        }
        return false;
    }
}
