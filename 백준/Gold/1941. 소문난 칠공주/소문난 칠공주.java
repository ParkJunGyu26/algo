import java.io.*;
import java.util.*;

public class Main {
    static char[][] map = new char[5][5];
    static boolean[] selected = new boolean[25];
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        combination(0, 0, 0);
        System.out.println(answer);
    }

    // 7명을 뽑는 조합
    static void combination(int idx, int cnt, int yCount) {
        if (yCount >= 4) return; // Y가 4명 이상이면 가지치기
        if (cnt == 7) {
            if (isConnected()) answer++;
            return;
        }
        for (int i = idx; i < 25; i++) {
            selected[i] = true;
            if (map[i / 5][i % 5] == 'Y') combination(i + 1, cnt + 1, yCount + 1);
            else combination(i + 1, cnt + 1, yCount);
            selected[i] = false;
        }
    }

    // 선택된 7명이 연결되어 있는지 BFS로 체크
    static boolean isConnected() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[25];
        for (int i = 0; i < 25; i++) {
            if (selected[i]) {
                q.offer(i);
                visited[i] = true;
                break;
            }
        }
        int cnt = 1;
        while (!q.isEmpty()) {
            int now = q.poll();
            int x = now / 5, y = now % 5;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                int next = nx * 5 + ny;
                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if (selected[next] && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    cnt++;
                }
            }
        }
        return cnt == 7;
    }
}
