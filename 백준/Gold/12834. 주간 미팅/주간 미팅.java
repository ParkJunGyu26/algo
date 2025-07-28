import java.util.*;
import java.io.*;

// 다익스트라 O(N V log E)
public class Main {
    static int n, v, e, A, B;
    static int[] team;
    static long answer = 0;
    static ArrayList<ArrayList<int[]>> graph;

    static int dijkstra(int now, int target) {
        int[] distance = new int[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[now] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(distance[i1], distance[i2]));
        pq.offer(now);

        while(!pq.isEmpty()) {
            int node = pq.poll();

            for (int[] info : graph.get(node)) {
                int next = info[0];
                int dist = info[1];

                if (distance[next] > distance[node] + dist) {
                    distance[next] = distance[node] + dist;
                    pq.offer(next);
                }
            }
        }

        return distance[target] == Integer.MAX_VALUE ? -1 : distance[target];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        team = new int[n];
        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) graph.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) team[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < e; i++) {
            int a, b, l;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, l});
            graph.get(b).add(new int[] {a, l});
        }

        for (int i = 0; i < n; i++) {
            answer += (dijkstra(team[i], A) * 1L);
            answer += (dijkstra(team[i], B) * 1L);
        }

        System.out.println(answer);
    }
}