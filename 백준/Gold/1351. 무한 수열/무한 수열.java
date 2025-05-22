import java.util.*;
import java.io.*;

public class Main {
    static long n, p, q;
    static HashMap<Long, Long> hm;

    static long dfs(long number) {
        if (hm.containsKey(number)) return hm.get(number);
        else {
            long P = dfs(number / p);
            long Q = dfs(number / q);

            hm.put(number, P + Q);

            return P + Q;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());
        hm = new HashMap<>();
        hm.put(0L, 1L);

        bw.write(String.valueOf(dfs(n)));
        bw.flush();
        bw.close();
        br.close();
    }
}