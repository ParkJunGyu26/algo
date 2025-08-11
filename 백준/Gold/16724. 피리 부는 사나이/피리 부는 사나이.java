import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static HashMap<Character, Integer> hm;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static int[][] res;
    static char[][] graph;
    static HashSet<Integer> hs;

    private static int dfs(int x, int y, int kind) {
        res[y][x] = kind;

        int dire = hm.get(graph[y][x]);
        int nx = x + dx[dire];
        int ny = y + dy[dire];

        if (!(-1 < nx && nx < m && -1 < ny && ny < n)) return 0;

        if (res[ny][nx] != 0) {
            if (res[ny][nx] == kind) return kind;
            return res[y][x] = res[ny][nx];
        }

        res[y][x] = dfs(nx, ny, kind);

        if (res[y][x] != kind) return res[y][x];

        return kind;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();
        hm.put('D', 2);
        hm.put('L', 3);
        hm.put('R', 1);
        hm.put('U', 0);

        res = new int[n][m];
        graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) graph[i][j] = tmp.charAt(j);
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (res[i][j] == 0) {
                    dfs(j, i, ++cnt);
                }
            }
        }

        hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (res[i][j] != 0) hs.add(res[i][j]);
            }
        }

        System.out.println(hs.size());
    }
}
