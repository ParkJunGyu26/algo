import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_VALUE = 1000 * 1000 * 1000 + 1;
    static int answer = MAX_VALUE;
    static int n;
    static boolean[] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] graph;
    static ArrayList<ArrayList<Integer>> dist;
    static ArrayList<int[]> info;

    private static void dfs(int nodeIndex, int prefixDist, int totalCnt) {
        if (totalCnt == info.size()) {
            answer = Math.min(prefixDist + dist.get(nodeIndex).get(0), answer);
            return;
        }

        for (int i = 1; i < info.size(); i++) {
            if (visited[i]) continue;

            visited[i] = true;
            dfs(i, prefixDist + dist.get(nodeIndex).get(i), totalCnt+1);
            visited[i] = false;
        }
    }

    private static void dijkstra(int nodeIndex) {
        int[] dot = info.get(nodeIndex);

        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(res[i], MAX_VALUE);
        res[dot[1]][dot[0]] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[2], i2[2]));
        pq.offer(new int[] {dot[0], dot[1], 0}); // {x, y, dist}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            int r = cur[2];

            if (res[y][x] < r) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                
                if (-1 < nx && nx < n && -1 < ny && ny < n) {
                    int nr = graph[ny][nx];
                    
                    if (res[ny][nx] > res[y][x] + nr) {
                        res[ny][nx] = res[y][x] + nr;
                        pq.offer(new int[] {nx, ny, res[ny][nx]});
                    }
                }
            }
        }

        for (int i = 0; i < info.size(); i++) {
            if (i == nodeIndex) continue;

            int[] d = info.get(i);

            int x = d[0];
            int y = d[1];

            dist.get(nodeIndex).set(i, res[y][x]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        info = new ArrayList<>(); // 탈영병, 부대 간의 좌표 위치
        info.add(new int[] {-1, -1});

        dist = new ArrayList<>(); // 탈영병, 부대 간의 최단 거리
        graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == -1) {
                    graph[i][j] = 0;
                    info.set(0, new int[] {j, i});
                } else if (graph[i][j] == 0) {
                    info.add(new int[] {j, i});
                }
            }
        }

        if (info.size() < 2) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < info.size(); i++) {
            dist.add(new ArrayList<>());
            for (int j = 0; j < info.size(); j++) dist.get(i).add(MAX_VALUE);
        }

        // 부대 및 탈영병 간의 최단거리 계산
        for (int i = 0; i < info.size(); i++) {
            dijkstra(i);
        }

        visited = new boolean[info.size()];
        visited[0] = true;

        dfs(0, 0, 1);

        System.out.println(answer);
    }
}