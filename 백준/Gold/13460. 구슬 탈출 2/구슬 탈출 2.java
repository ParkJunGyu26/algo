import java.io.*;
import java.util.*;

public class Main {
    static int n, m, ax, ay, bx, by;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] graph;
    static int[][][] visited;

    private static void redNextBlue(ArrayDeque<int[]> q, int[] cur, int dire) {
        int Ax = cur[0];
        int Ay = cur[1];
        int Bx = cur[2];
        int By = cur[3];

        int nx = Ax + dx[dire];
        int ny = Ay + dy[dire];

        int[] next = new int[5];
        next[0] = Ax;
        next[1] = Ay;
        next[2] = Bx;
        next[3] = By;
        next[4] = cur[4] + 1;

        // 레드
        while (true) {
            if (graph[ny][nx] == 'O' || graph[ny][nx] == '#' || ((nx == Bx) && (ny == By))) {
                next[0] = Ax;
                next[1] = Ay;
                if (graph[ny][nx] == 'O') {
                    next[0] = nx;
                    next[1] = ny;
                } 
                break;
            }
            Ax = nx;
            Ay = ny;
            nx = Ax + dx[dire];
            ny = Ay + dy[dire];
        }

        nx = Bx + dx[dire];
        ny = By + dy[dire];
        // 블루
        while (true) {
            if (graph[ny][nx] == 'O' || graph[ny][nx] == '#' || ((nx == next[0]) && (ny == next[1]))) {
                next[2] = Bx;
                next[3] = By;
                if (graph[ny][nx] == 'O') {
                    next[2] = nx;
                    next[3] = ny;
                }
                break;
            }
            Bx = nx;
            By = ny;
            nx = Bx + dx[dire];
            ny = By + dy[dire];
        }

        for (int i = 0; i < 4; i++) {
            if (cur[i] != next[i]) {
                q.offer(next);
                return;
            }
        }
    }

    private static void blueNextRed(ArrayDeque<int[]> q, int[] cur, int dire) {
        int Ax = cur[0];
        int Ay = cur[1];
        int Bx = cur[2];
        int By = cur[3];

        int nx = Bx + dx[dire];
        int ny = By + dy[dire];

        int[] next = new int[5];
        next[0] = Ax;
        next[1] = Ay;
        next[2] = Bx;
        next[3] = By;
        next[4] = cur[4] + 1;

        // 블루
        while (true) {
            if (graph[ny][nx] == 'O' || graph[ny][nx] == '#' || ((nx == Ax) && (ny == Ay))) {
                next[2] = Bx;
                next[3] = By;
                if (graph[ny][nx] == 'O') {
                    next[2] = nx;
                    next[3] = ny;
                }
                break;
            }
            Bx = nx;
            By = ny;
            nx = Bx + dx[dire];
            ny = By + dy[dire];
        }

        nx = Ax + dx[dire];
        ny = Ay + dy[dire];
        // 레드
        while (true) {
            if (graph[ny][nx] == 'O' || graph[ny][nx] == '#' || ((nx == next[2]) && (ny == next[3]))) {
                next[0] = Ax;
                next[1] = Ay;
                if (graph[ny][nx] == 'O') {
                    next[0] = nx;
                    next[1] = ny;
                } 
                break;
            }
            Ax = nx;
            Ay = ny;
            nx = Ax + dx[dire];
            ny = Ay + dy[dire];
        }

        for (int i = 0; i < 4; i++) {
            if (cur[i] != next[i]) {
                q.offer(next);
                return;
            }
        }
    }
    
    // ax, bx 비교해서 왼쪽 갈 때는 작은 쪽 먼저, 오른쪽 갈 때는 큰 쪽 먼저
    // ay, by 비교해서 위로 갈 때는 작은 쪽 먼저, 아래 갈 때는 큰 쪽 먼저
    private static int bfs() {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {ax, ay, bx, by, 0});

        // 4^10 -> 2^20
        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int nowAx = cur[0];
            int nowAy = cur[1];
            int nowBx = cur[2];
            int nowBy = cur[3];
            int cnt = cur[4];

            if (cnt > 10) return -1;

            if (graph[nowBy][nowBx] != 'O' && graph[nowAy][nowAx] == 'O') return cnt;

            if (graph[nowBy][nowBx] == 'O') continue;

            // 위쪽으로 굴리기
            if (nowAy < nowBy) {
                redNextBlue(q, cur, 0);
            } else {
                blueNextRed(q, cur, 0);
            }

            // 오른쪽으로 굴리기
            if (nowAx < nowBx) {
                blueNextRed(q, cur, 1);
            } else {
                redNextBlue(q, cur, 1);
            }

            // 아래쪽으로 굴리기
            if (nowAy < nowBy) {
                blueNextRed(q, cur, 2);
            } else {
                redNextBlue(q, cur, 2);
            }

            // 왼쪽으로 굴리기
            if (nowAx < nowBx) {
                redNextBlue(q, cur, 3);
            } else {
                blueNextRed(q, cur, 3);
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        visited = new int[n][m][4];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = tmp.charAt(j);
                if (graph[i][j] == 'R') {
                    ax = j;
                    ay = i;
                } else if (graph[i][j] == 'B') {
                    bx = j;
                    by = i;
                }

                Arrays.fill(visited[i][j], 2);
            }
        }

        System.out.println(bfs());
    }
}