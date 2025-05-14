import java.util.*;
import java.io.*;

public class Main {

    static int n, c;
    static int[] X;

    static int check(long target) {

        int cnt = 1;
        int now = X[0];
        for (int i = 1; i < n; i++) {
            long diff = Math.abs(now - X[i]);
            if (target <= diff) {
                cnt++;
                now = X[i];
            }

        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        X = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(X); // O(N log N)

        long left = 0;
        long right = 1_000_000_001;
        // O(N log N)
        while (left < right) {
            long mid = (left + right) / 2;

            int count = check(mid);

            if (count >= c) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        bw.write(String.valueOf(left-1));
        bw.flush();
        bw.close();
        br.close();
    }
}