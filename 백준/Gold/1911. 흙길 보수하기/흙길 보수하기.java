import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long L = Long.parseLong(st.nextToken());

        long[][] arr = new long[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Long.parseLong(st.nextToken());
            arr[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr, Comparator.comparingLong(a -> a[0]));

        long coveredEnd = -1;
        long answer = 0;

        for (int i = 0; i < n; i++) {
            long s = arr[i][0];
            long e = arr[i][1];

            if (coveredEnd >= e) {
                continue;
            }

            long startToCover = Math.max(s, coveredEnd + 1);
            long len = e - startToCover;
            long need = (len + L - 1) / L;

            answer += need;
            coveredEnd = startToCover + need * L - 1;
        }

        System.out.println(answer);
    }
}
