import java.util.*;
import java.io.*;

public class Main {
    static int s, answer = Integer.MAX_VALUE;
    // i : 화면, j : 클립보드
    static int[][] res;

    static class Info {
        int monitor, clipboard;

        Info (int m, int c) {
            monitor = m;
            clipboard = c;
        }
    }

    static void bfs() {
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(1, 0));
        res[0][0] = res[1][0] = 0;

        while (!q.isEmpty()) {
            Info cur = q.poll();

            int monitor = cur.monitor;
            int clipboard = cur.clipboard;

            if (res[monitor][clipboard] == Integer.MAX_VALUE) continue;

            if (monitor == s) {
                return;
            }

            if (monitor + clipboard <= s * 2 && res[monitor][monitor] > res[monitor][clipboard] + 1) {
                res[monitor][monitor] = res[monitor][clipboard] + 1;
                q.offer(new Info(monitor, monitor));
            }

            if (monitor + clipboard <= s * 2 && clipboard != 0 && res[monitor + clipboard][clipboard] > res[monitor][clipboard] + 1) {
                res[monitor + clipboard][clipboard] = res[monitor][clipboard] + 1;
                q.offer(new Info(monitor + clipboard, clipboard));
            }

            if (monitor > 0 && res[monitor - 1][clipboard] > res[monitor][clipboard] + 1) {
                res[monitor - 1][clipboard] = res[monitor][clipboard] + 1;
                q.offer(new Info(monitor - 1, clipboard));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = Integer.parseInt(br.readLine());

        res = new int[s*2+1][s*2+1];
        for (int i = 0; i <= s*2; i++) Arrays.fill(res[i], Integer.MAX_VALUE);
        bfs();
        for (int i = 0; i <= s*2; i++) answer = Math.min(answer, res[s][i]);
        System.out.println(answer);
    }
}