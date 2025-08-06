import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int n, answer = 0;
    static int[] res;
    static int[][] dp;
    static ArrayList<ArrayList<Integer>> graph;

    static void dfs(int node) {
        if (visited[node]) return;

        visited[node] = true;
        dp[node][1] = res[node];

        for (int next : graph.get(node)) {
            if (visited[next]) continue;

            dfs(next);

            dp[node][0] += Math.max(dp[next][0], dp[next][1]);
            dp[node][1] += dp[next][0];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        dp = new int[n+1][2];
        visited = new boolean[n+1];
        res = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) res[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n-1; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        dfs(1);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}