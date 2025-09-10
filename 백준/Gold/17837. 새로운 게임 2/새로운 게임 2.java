import java.io.*;
import java.util.*;

public class Main {
    static int n, k, answer;
    // 0 : 오른쪽, 1 : 왼쪽, 2 : 위쪽, 3 : 아래쪽
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, -1, 1};
    static int[] reverse = {1, 0, 3, 2};
    static int[][] graph;
    static int[][] pos; // 말 위치 (index → {x,y})
    static ArrayDeque<int[]>[][] horse;

    private static void simulation() {
        int cnt = 1;

        while (cnt <= 1000) {
            for (int i = 1; i <= k; i++) {
                int x = pos[i][0];
                int y = pos[i][1];
                Deque<int[]> curStack = horse[y][x];

                // i번 말 위에 쌓인 말들만 moveStack으로 이동
                Deque<int[]> moveStack = new ArrayDeque<>();
                while (!curStack.isEmpty()) {
                    int[] h = curStack.pollLast();
                    moveStack.offerFirst(h);
                    if (h[0] == i) break;
                }

                int[] cur = moveStack.peekFirst();
                int index = cur[0];
                int dire = cur[1];
                int nx = x + dx[dire];
                int ny = y + dy[dire];

                // 이동할 칸 확인
                if (graph[ny][nx] == 2) { // 파란색
                    dire = reverse[dire];
                    cur[1] = dire; // 방향 갱신
                    nx = x + dx[dire];
                    ny = y + dy[dire];

                    if (graph[ny][nx] == 2) { // 또 파란색 → 제자리
                        while (!moveStack.isEmpty()) curStack.offerLast(moveStack.pollFirst());
                        continue;
                    }
                }

                // 말 이동 처리
                if (graph[ny][nx] == 0) { // 흰색
                    while (!moveStack.isEmpty()) {
                        int[] h = moveStack.pollFirst();
                        pos[h[0]][0] = nx;
                        pos[h[0]][1] = ny;
                        horse[ny][nx].offerLast(h);
                    }
                } else if (graph[ny][nx] == 1) { // 빨간색
                    while (!moveStack.isEmpty()) {
                        int[] h = moveStack.pollLast();
                        pos[h[0]][0] = nx;
                        pos[h[0]][1] = ny;
                        horse[ny][nx].offerLast(h);
                    }
                }

                // 종료 조건: 말이 4개 이상 쌓이면 게임 종료
                if (horse[ny][nx].size() >= 4) {
                    answer = cnt;
                    return;
                }
            }
            cnt++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pos = new int[k + 1][2];
        horse = new ArrayDeque[n + 2][n + 2];
        graph = new int[n + 2][n + 2];
        for (int i = 0; i <= (n + 1); i++) {
            Arrays.fill(graph[i], 2);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                horse[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= k; i++) {
            int a, b, c; // 행, 열, 방향
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            horse[a][b].addLast(new int[]{i, c - 1});
            pos[i][0] = b;
            pos[i][1] = a;
        }

        simulation();
        System.out.println(answer == 0 ? -1 : answer);
    }
}