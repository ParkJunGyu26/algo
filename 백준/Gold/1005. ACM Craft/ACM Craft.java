import java.io.*;
import java.util.*;

public class Main {
    static int t, n, k, w;
    static int[] build, dp, inDegree;
    static int[] head, to, next;
    static int edgeCnt;

    static void addEdge(int u, int v) {
        to[edgeCnt] = v;
        next[edgeCnt] = head[u];
        head[u] = edgeCnt++;
    }

    static void topologySort(StringBuilder sb) {
        int[] q = new int[n + 5];
        int front = 0, rear = 0;

        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q[rear++] = i;
                dp[i] = build[i]; // 자기 건물 시간
            }
        }

        while (front < rear) {
            int cur = q[front++];
            if (cur == w) { // 목표 건물 완성 시간 도달
                sb.append(dp[w]).append('\n');
                return;
            }
            for (int e = head[cur]; e != -1; e = next[e]) {
                int nxt = to[e];
                if (dp[nxt] < dp[cur] + build[nxt]) {
                    dp[nxt] = dp[cur] + build[nxt];
                }
                if (--inDegree[nxt] == 0) {
                    q[rear++] = nxt;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();

        t = fr.nextInt();
        while (t-- > 0) {
            n = fr.nextInt();
            k = fr.nextInt();

            build = new int[n + 1];
            dp = new int[n + 1];
            inDegree = new int[n + 1];

            head = new int[n + 1];
            Arrays.fill(head, -1);
            to = new int[k + 5];
            next = new int[k + 5];
            edgeCnt = 0;

            for (int i = 1; i <= n; i++) build[i] = fr.nextInt();

            for (int i = 0; i < k; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                addEdge(a, b);
                inDegree[b]++;
            }

            w = fr.nextInt();
            Arrays.fill(dp, 0);
            topologySort(sb);
        }
        System.out.print(sb);
    }

    // 빠른 입력
    static class FastReader {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}
