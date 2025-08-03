import java.util.*;
import java.io.*;

public class Main {
    static int p, q, x, y;
    static long n;
    static HashMap<Long, Long> dp = new HashMap<>();

    private static long dfs(long number) {
        if (number <= 0) return 1L;

        if (dp.containsKey(number)) return dp.get(number);

        long result = dfs(number / p - x) + dfs(number / q - y);
        dp.put(number, result);

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        System.out.println(dfs(n));
    }
}