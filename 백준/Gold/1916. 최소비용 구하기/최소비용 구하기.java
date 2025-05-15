import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static ArrayList<Pair>[] graph;
    static long[] distance;

    static class Pair implements Comparable<Pair> {
        int node;
        long cost;

        Pair(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        Arrays.fill(distance, Long.MAX_VALUE);  // Long.MAX_VALUE 사용

        distance[start] = 0;
        pq.add(new Pair(start, 0));

        while(!pq.isEmpty()) {
            Pair current = pq.poll();
            long cost = current.cost;
            int node = current.node;

            if (distance[node] < cost) continue;

            // 인접 리스트 순회
            for (Pair next : graph[node]) {
                long nextCost = cost + next.cost;

                if (nextCost < distance[next.node]) {
                    distance[next.node] = nextCost;
                    pq.add(new Pair(next.node, nextCost));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화 (파이썬 코드와 유사한 방식)
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        distance = new long[n+1];
        
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 중복 간선 처리는 나중에 dijkstra 알고리즘 내에서 자동으로 처리됨
            graph[a].add(new Pair(b, c));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(distance[end]);
    }
}