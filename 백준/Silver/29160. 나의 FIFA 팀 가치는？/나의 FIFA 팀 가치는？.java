import java.util.*;
import java.io.*;

public class Main {

    static long answer = 0;
    static int n, k;
    static ArrayList<PriorityQueue<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(new PriorityQueue<>());
        }

        for (int i = 1; i <= n; i++) {
            int p, w;
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            list.get(p).add(-w);
        }

        for (int i = 1; i <= 11; i++) {
            for (int j = k; j >= 0; j--) {
                if (!list.get(i).isEmpty()) {
                    int Top = -list.get(i).poll();

                    if (j > 0) {
                        if (Top > 0) {
                            list.get(i).add(-(Top-1));
                        }
                    } else {
                        answer += Top;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}