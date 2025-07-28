import java.util.*;
import java.io.*;

// DFS + DP(바텀업)
public class Main {
    static int n, m;
    static HashMap<String, Long> bloods;
    static HashMap<String, String[]> parentArr;

    static long dfs(String node) {
        if (bloods.containsKey(node)) return bloods.get(node);

        if (parentArr.get(node) == null) return 0L;

        long blood = bloods.getOrDefault(node, 0L);
        for (String nNode : parentArr.get(node)) {
            blood += dfs(nNode) / 2;
        }

        bloods.put(node, blood);
        return blood;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        bloods = new HashMap<>();
        parentArr = new HashMap<>();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        String root = br.readLine();
        long MAX = 2L;
        for (int i = 0; i < n; i++) MAX *= 2;

        bloods.put(root, MAX);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] info = new String[3];
            for (int j = 0; j < 3; j++) info[j] = st.nextToken();

            parentArr.put(info[0], new String[] {info[1], info[2]});
        }

        String answer = "";
        long max = 0L;
        for (int i = 0; i < m; i++) {
            String info = br.readLine();
            long num = dfs(info);

            if (num > max) {
                max = num;
                answer = info;
            }
        }

        System.out.println(answer);
    }
}
