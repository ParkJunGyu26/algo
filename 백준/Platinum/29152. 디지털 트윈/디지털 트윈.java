import java.io.*;
import java.util.*;

public class Main {
    static final long INF = (long)4e18;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        long[] prev = new long[N + 1];
        long[] curr = new long[N + 1];
        long[] pref = new long[N + 1];
        long[] suff = new long[N + 2];

        Arrays.fill(prev, INF);
        prev[1] = 0;

        for (int r = 1; r <= N; r++) {
            String line = br.readLine().trim();

            int L = N + 1;
            int R = 0;
            for (int c = 1; c <= N; c++) {
                char ch = line.charAt(c - 1);
                if (ch == '1') {
                    if (L == N + 1) L = c;
                    R = c;
                }
            }
            boolean empty = (R == 0);

            long best = INF;
            for (int i = 1; i <= N; i++) {
                long val = prev[i];
                if (val < INF) val -= i;
                best = Math.min(best, val);
                pref[i] = best;
            }
            best = INF;
            suff[N + 1] = INF;
            for (int i = N; i >= 1; i--) {
                long val = prev[i];
                if (val < INF) val += i;
                best = Math.min(best, val);
                suff[i] = best;
            }

            Arrays.fill(curr, INF);

            if (empty) {
                for (int c = 1; c <= N; c++) {
                    long a = pref[c];
                    if (a < INF) a += c;
                    long b = suff[c];
                    if (b < INF) b -= c;
                    curr[c] = Math.min(a, b);
                }
            } else {
                // 왼쪽으로 마무리
                long rightMin = suff[R];
                if (rightMin < INF) {
                    for (int c = 1; c <= L; c++) {
                        curr[c] = Math.min(curr[c], rightMin - c);
                    }
                }
                // 오른쪽으로 마무리
                long leftMin = pref[L];
                if (leftMin < INF) {
                    for (int c = R; c <= N; c++) {
                        long val = leftMin + c;
                        if (val < curr[c]) curr[c] = val;
                    }
                }
            }

            if (r == N) {
                long last = curr[N];
                if (last >= INF / 2) {
                    System.out.println(-1);
                    return;
                } else {
                    long answer = last + N;
                    System.out.println(answer);
                    return;
                }
            }

            long[] tmp = prev;
            prev = curr;
            curr = tmp;
        }
    }
}
