import java.util.*;
import java.io.*;

public class Main {
    static int t;

    private static int solve(int n, long a) {
        ArrayList<Long> list = new ArrayList<>();

        while (true) {
            if (a / n == 0) {
                list.add(a % n);
                break;
            }

            list.add(a % n);
            a /= n;
        }

        for (int i = 0; i <= list.size() / 2; i++) {
            if (list.get(i) != list.get(list.size() - i - 1)) return 0;
        }

        return 1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n;
            long a;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            
            bw.write(solve(n, a) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}