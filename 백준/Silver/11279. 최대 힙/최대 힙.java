import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int x;
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            if (x == 0) {
                if (pq.size() == 0) bw.write("0\n");
                else {
                    int xx = -pq.poll();
                    bw.write(String.valueOf(xx));
                    bw.write("\n");
                }
                bw.flush();
            } else pq.add(-x);
        }
        bw.close();
        br.close();
    }
}