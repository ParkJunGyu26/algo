import java.util.*;
import java.io.*;

// 트리가 보장되므로 사이클 아님. 그래서 방문처리 필요없음
public class Main {
    static int n, r, q;
    static int[] dp;
    static ArrayList<ArrayList<Integer>> graph;

    static int dfs(int node, int parent) {
        dp[node] = 1;
        for (int next : graph.get(node)) {
            if (next != parent) {
                dp[node] += dfs(next, node);
            }
        }

        return dp[node];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        dp = new int[n+1];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n-1; i++) {
            int u, v;
            st = new StringTokenizer(br.readLine());

            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        dfs(r, -1);

        for (int i = 0; i < q; i++) {
            int query;
            query = Integer.parseInt(br.readLine());

            bw.write(String.valueOf(dp[query]) + "\n");
            bw.flush();
        }
        
        bw.close();
        br.close();
    }
}