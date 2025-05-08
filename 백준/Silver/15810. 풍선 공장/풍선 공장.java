import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] A;

    public static boolean check(long target) {
        long cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += (target / A[i]);

            if (cnt >= m) return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[n];
        long left = 1;
        long right = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (right < A[i]) right = A[i];
        }

        right *= m;
        right++;

        while (left < right) {
            long mid = (left + right) / 2;

            if (check(mid)) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        bw.write(String.valueOf(left));
        bw.flush();
        bw.close();
        br.close();
    }
}