import java.io.*;
import java.util.*;

public class Main {
    // 최단 경로 탐색
    private static int dijkstra1(int start, int end, ArrayList<ArrayList<int[]>> graph) {
        int[] res = new int[graph.size()];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int node = cur[0];
            int dist = cur[1];

            if (res[node] < dist) continue;

            for (int[] info : graph.get(node)) {
                int nextNode = info[0];
                int nextDist = info[1];

                if (res[nextNode] > res[node] + nextDist) {
                    res[nextNode] = res[node] + nextDist;
                    pq.offer(new int[] {nextNode, res[nextNode]});
                }
            }
        }

        return res[end];
    }

    private static void dfs(int node, ArrayList<HashSet<Integer>> parent, boolean[][] shortEdge, int start, boolean[] visited) {
        if (visited[node]) return;

        visited[node] = true;
        for (int beforeNode : parent.get(node)) {
            shortEdge[beforeNode][node] = true;
            dfs(beforeNode, parent, shortEdge, start, visited);
        }
    }
    
    // 최단 경로에 포함되는 엣지들 방문처리
    private static void dijkstra2(int start, int end, ArrayList<ArrayList<int[]>> graph, int shortDist, boolean[][] shortEdge) {
        ArrayList<HashSet<Integer>> parent = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) parent.add(new HashSet<>());

        int[] res = new int[graph.size()];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int node = cur[0];
            int dist = cur[1];

            if (res[node] < dist) continue;

            for (int[] info : graph.get(node)) {
                int nextNode = info[0];
                int nextDist = info[1];

                if (end == nextNode) {
                    if (res[node] + nextDist != shortDist) continue;

                    shortEdge[node][nextNode] = true;
                    boolean[] visited = new boolean[graph.size()];
                    
                    dfs(node, parent, shortEdge, start, visited);

                    continue;
                }

                if (res[nextNode] >= res[node] + nextDist) {
                    if (res[nextNode] > res[node] + nextDist) {
                        parent.get(nextNode).clear();

                        res[nextNode] = res[node] + nextDist;
                        pq.offer(new int[] {nextNode, res[nextNode]});
                    }

                    parent.get(nextNode).add(node);
                }
            }
        }
    }

    // 거의 최단 경로 탐색
    private static int dijkstra3(int start, int end, ArrayList<ArrayList<int[]>> graph, boolean[][] shortEdge) {
        int[] res = new int[graph.size()];
        Arrays.fill(res, Integer.MAX_VALUE);
        res[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        pq.offer(new int[] {start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int node = cur[0];
            int dist = cur[1];

            if (res[node] < dist) continue;

            for (int[] info : graph.get(node)) {
                int nextNode = info[0];
                int nextDist = info[1];

                if (shortEdge[node][nextNode]) continue; // 최단 경로에 포함된 엣지이므로 패스

                if (res[nextNode] > res[node] + nextDist) {
                    res[nextNode] = res[node] + nextDist;
                    pq.offer(new int[] {nextNode, res[nextNode]});
                }
            }
        }

        return res[end] == Integer.MAX_VALUE ? -1 : res[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if (n == 0 && m == 0) break;
    
            // 연결 그래프
            ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
            for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
    
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
    
            for (int i = 0; i < m; i++) {
                int u, v, p;
                st = new StringTokenizer(br.readLine());
    
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken());
    
                graph.get(u).add(new int[] {v, p});
            }

            boolean[][] shrotEdge = new boolean[n][n];
            
            // 1. s -> d 최단경로 값 찾기
            int shortDist = dijkstra1(s, d, graph);
            
            // 2-1. s -> d 최단경로에 포함된 경로(엣지) 찾기
            dijkstra2(s, d, graph, shortDist, shrotEdge);

            // 2-2. d -> s 최단경로에 포함된 경로(엣지) 찾기
            dijkstra2(d, s, graph, shortDist, shrotEdge);

            // 3. s -> d "거의 최단 경로" 찾기
            int answer = dijkstra3(s, d, graph, shrotEdge);

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}