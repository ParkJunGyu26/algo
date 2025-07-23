import java.util.*;
import java.io.*;

public class Main {
    static int t, n, a, b;
    static long A = 0L, B = 0L;
    static int[] dot;

    private static void solve(ArrayList<Integer> list) {
        for (int num : list) {
            long aDiff = Math.abs(a - num) * 2L;
            long bDiff = Math.abs(b - num) * 2L;

            if (aDiff == bDiff) {
                if (A > B) {
                    B += bDiff;
                } else {
                    A += aDiff;
                }
            } else if (aDiff > bDiff) {
                B += bDiff;
            } else {
                A += aDiff;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            ArrayList<Integer> same = new ArrayList<>();
            ArrayList<Integer> different = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int dot = Integer.parseInt(st.nextToken());
                if (Math.abs(dot - a) == Math.abs(dot - b)) different.add(dot);
                else same.add(dot);
            }
            
            A = B = 0;
            solve(same);
            solve(different);

            bw.write((A+B) + " " + (Math.abs(A-B)) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}