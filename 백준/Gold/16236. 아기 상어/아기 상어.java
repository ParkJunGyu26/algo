import java.io.*;
import java.util.*;

public class Main {
    static int n, answer = 0;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0}, start;
    static int[][] graph;

    static boolean inRange(int x, int y) {
        return (-1 < x && x < n && -1 < y && y < n);
    }

    static int[] bfs(int[] now) {
        int[] result = new int[] {0, 0, -1, -1, 0};
        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(visited[i], Integer.MAX_VALUE);
        Queue<int[]> q = new LinkedList<>();

        ArrayList<int[]> hubo = new ArrayList<>();
        int MIN = Integer.MAX_VALUE;

        q.offer(now);
        visited[now[3]][now[2]] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int level = cur[0];
            int eat = cur[1];
            int x = cur[2];
            int y = cur[3];
            int res = cur[4];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!inRange(nx, ny)) continue;

                if (graph[ny][nx] > level || visited[ny][nx] <= visited[y][x] + 1) continue;

                visited[ny][nx] = visited[y][x] + 1;
                if (graph[ny][nx] == level || graph[ny][nx] == 0) {
                    q.offer(new int[] {level, eat, nx, ny, res+1});
                }

                // 0 보다 크고, 레벨 보다 작으면 먹어라!
                if (0 < graph[ny][nx] && graph[ny][nx] < level) {
                    if (visited[ny][nx] > MIN) continue; // 최단 거리 같은 경우에도 탐색하긴 해야됨
                    MIN = visited[ny][nx];
                    hubo.add(new int[] {level, eat+1, nx, ny, res+1});
                }
            }
        }

        if (hubo.isEmpty()) return result;

        // 조건에 맞도록 정렬 (y 오름차순, x 오름차순)
        hubo.sort((a1, a2) -> {
            int comp = Integer.compare(a1[3], a2[3]); // y오름차순
            if (comp != 0) return comp;
            comp = Integer.compare(a1[2], a2[2]); // y 같으면, x 오름차순
            return comp;
        });

        // 후보에서 정해진 좌표는 0으로 처리
        graph[hubo.get(0)[3]][hubo.get(0)[2]] = 0;

        // 정답 업데이트
        answer = hubo.get(0)[4];

        // 레벨과 먹은 게 같다면 레벨업 + 먹은 거 초기화
        if (hubo.get(0)[0] == hubo.get(0)[1]) {
            hubo.get(0)[0]++;
            hubo.get(0)[1] = 0;
        }

        // 이동한 노드 결과 업데이트 + 깊은 복사
        for (int i = 0; i < result.length; i++) {
            result[i] = hubo.get(0)[i];
        }

        return result;
    }

    static void solve() {
        int[] init = {2, 0, start[0], start[1], 0}; // 레벨, 먹은 거 개수, 좌표(x, y), 시간
        int[] now = new int[5];
        for (int i = 0; i < now.length; i++) now[i] = init[i]; // 깊은 복사

        while (true) {
            int[] target = bfs(now);
            if (target[2] == -1) return; // 다음 노드 정해진 게 없다면 움직이지 못한다.

            for (int i = 0; i < now.length; i++) now[i] = target[i]; // 깊은 복사
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    start = new int[]{j, i};
                    graph[i][j] = 0;
                }
            }
        }

        solve();
        System.out.println(answer);
    }
}