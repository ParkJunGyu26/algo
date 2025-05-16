import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] degree, answer;
    static ArrayList<ArrayList<Integer>> graph;

    static void topologySort() {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                q.offer(i);
            }
            answer[i] = 1;
        }

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int i = 0; i < graph.get(node).size(); i++) {
                int nNode = graph.get(node).get(i);
                answer[nNode] = Math.max(answer[node], answer[nNode]);

                if (--degree[nNode] == 0) {
                    q.offer(nNode);
                    answer[nNode]++;
                }
            }
        }

        for (int i = 1; i <= n; i++) System.out.print(answer[i] + " ");

        // for (int i = 1; i <= n; i++) bw.write(String.valueOf(answer[i]) + " ");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        degree = new int[n+1];
        answer = new int[n+1];

        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            degree[b]++;
        }

        topologySort();
    }
}