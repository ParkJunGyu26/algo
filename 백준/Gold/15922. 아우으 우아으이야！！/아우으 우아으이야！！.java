import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static long x, y;
    static Pair[] arr;

    static class Pair implements Comparable<Pair>{
        long x, y;
        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair other) {
            return Long.compare(this.x, other.x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Long.parseLong(st.nextToken());
            y = Long.parseLong(st.nextToken());

            arr[i] = new Pair(x, y);
        }

        long answer = Math.abs(arr[0].y - arr[0].x);
        long before_end = arr[0].y;

        for (int i = 1; i < n; i++) {
            long start = arr[i].x;
            long end = arr[i].y;

            if (before_end >= end) continue;

            if (start <= before_end) answer += Math.abs(end - before_end);
            else answer += Math.abs(end - start);

            before_end = end;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();

    }
}