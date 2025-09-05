import java.io.*;
import java.util.*;

public class Main {
    static int n, r, gaziNode, treeLength, branchLength;
    static int[] dp;
    static boolean[] visited;
    static ArrayList<ArrayList<int[]>> graph;

    static private void findGigaNode(int node, int prefixSum) {
        // 가지가 1개라면, 기둥에 포함된 노드
        // 가지가 0개라면, 리프 노드
        // 가지가 2개 이상이라면, 기가 노드!
        if (graph.get(node).size() != 2) {
            treeLength = prefixSum;
            gaziNode = node;
            return;
        }

        for (int[] info : graph.get(node)) {
            int nextNode = info[0];
            int dist = info[1];

            if (visited[nextNode]) continue;

            visited[nextNode] = true;
            findGigaNode(nextNode, prefixSum + dist);
        }
    }

    private static void findBranch(int node, int prefixSum) {
        if (graph.get(node).size() == 1) {
            branchLength = Math.max(branchLength, prefixSum);
            return;
        }

        for (int[] info : graph.get(node)) {
            int nextNode = info[0];
            int dist = info[1];

            if (visited[nextNode]) continue;

            visited[nextNode] = true;
            findBranch(nextNode, prefixSum + dist);
        }
    }

    // 나무 기둥 길이(루트 ~ 가지)
    // 가장 긴 가지 길이(Max(가지 ~ 리프))
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        gaziNode = -1;
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        visited = new boolean[n+1];
        dp = new int[n+1];

        visited[0] = visited[r] = true;
        graph.get(r).add(new int[] {0, 0});
        for (int i = 0; i < n-1; i++) {
            int a, b, d;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[] {b, d});
            graph.get(b).add(new int[] {a, d});
        }

        // 기가 노드를 찾아라! (gaziNode, treeLength)
        findGigaNode(r, 0);

        // 가장 긴 가지 길이를 찾아라!
        findBranch(gaziNode, 0);

        System.out.println(treeLength + " " + branchLength);
    }
}