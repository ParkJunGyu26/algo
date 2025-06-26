import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static long[] animal; // 양 또는 늑대 수
    static char[] type;   // 'S' or 'W'

    static long dfs(int curr) {
        long sum = 0;

        for (int next : tree.get(curr)) {
            long child = dfs(next);
            sum += child;
        }

        if (type[curr] == 'S') {
            return sum + animal[curr];
        } else {
            return Math.max(0, sum - animal[curr]); // 늑대가 양을 잡아먹음
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        animal = new long[n + 1];
        type = new char[n + 1];
        for (int i = 0; i <= n; i++) tree.add(new ArrayList<>());

        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char t = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            type[i] = t;
            animal[i] = a;
            tree.get(p).add(i); // 부모 → 자식
        }

        System.out.println(dfs(1));
    }
}
