import java.util.*;
import java.io.*;

public class Main {

    static int n, k;
    static int[] degree, res, dp;
    static ArrayList<ArrayList<Integer>> graph;

    public static void tologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                q.add(i);
                dp[i] = res[i];
            }
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int i = 0; i < graph.get(node).size(); i++) {
                int nNode = graph.get(node).get(i);
                dp[nNode] = Math.max(dp[nNode], dp[node]);

                if (--degree[nNode] == 0) {
                    q.add(nNode);
                    dp[nNode] += res[nNode];
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (answer < dp[i]) answer = dp[i];
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        degree = new int[n+1];
        res = new int[n+1];
        dp = new int[n+1];
        graph = new ArrayList<>();

        graph.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());

            res[i] = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            degree[i] += k;

            for (int j = 0; j < k; j++) {
                int node = Integer.parseInt(st.nextToken());
                graph.get(node).add(i);
            }
        }

        tologySort();
    }
}