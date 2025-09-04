import java.io.*;
import java.util.*;

public class Main {
    static int r, c;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static char[][] graph;
    static int[][] res;

    private static boolean inRange(int nx, int ny) {
        return (-1 < nx && nx < c && -1 < ny && ny < r);
    }
   
    private static void bfs(int xx, int yy, ArrayDeque<int[]> q, int status, ArrayDeque<int[]> dq) {

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;

                if (graph[ny][nx] == 'X') {
                    dq.offer(new int[] {nx, ny});
                    if (status == 0) {
                        res[ny][nx] = 1;
                        dq.offer(new int[] {nx, ny});
                    } else {
                        res[ny][nx] = res[y][x] + 1;
                        q.offer(new int[] {nx, ny});
                    }
                } else {
                    if (status == 1) continue;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
    }

    private static void solve(int xx, int yy, PriorityQueue<int[]> pq) {
        pq.offer(new int[] {xx, yy, res[yy][xx]});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];

            if (graph[y][x] == 'L') {
                System.out.println(cur[2]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                pq.offer(new int[] {nx, ny, Math.max(res[ny][nx], cur[2])});
            }
        }
    }

    // 양 옆으로 .이 있을 수 있기 때문에
    // .은 0로, 나머지 X는 모두 MAX 초기화
    // x의 경우에는 min(상하좌우) + 1로 업데이트 시켜주기
    // 좌우로 for문 한 번씩
    // 상하로 for문 한 번씩 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new char[r][c];
        res = new int[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < c; j++) graph[i][j] = tmp.charAt(j);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (graph[i][j] != 'X' && !visited[i][j]) {
                    visited[i][j] = true;
                    q.offer(new int[] {j, i});
                    bfs(j, i, q, 0, dq);
                }
            }
        }

        q = new ArrayDeque<>();
        bfs(-1, -1, dq, 1, q);

        for (int i = 0; i < r; i++) Arrays.fill(visited[i], false);

        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[2], i2[2]));
        boolean check = false;
        for (int i = 0; i < r; i++) {
            if (check) break;
            for (int j = 0; j < c; j++) {
                if (graph[i][j] == 'L') {
                    graph[i][j] = '.';
                    visited[i][j] = true;
                    solve(j, i, pq);
                    check = true;
                    break;
                }
            }
        }
    }
}    