import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] answer, believe;
    static ArrayList<ArrayList<Integer>> graph;

    static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) if (answer[i] == 0) q.offer(i);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for (int next : graph.get(cur)) {
                if (answer[next] != Integer.MAX_VALUE) continue;

                believe[next]++;
                if (believe[next] >= (graph.get(next).size() + 1) / 2) {
                    q.offer(next);
                    answer[next] = answer[cur] + 1;
                }
            }
        }
    }

    // 위상정렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        believe = new int[n+1];
        answer = new int[n+1];
        Arrays.fill(answer, Integer.MAX_VALUE);

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            while (true) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0) break;

                graph.get(i).add(tmp);
            }
        }

        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int startNode = Integer.parseInt(st.nextToken());
            answer[startNode] = 0;
        }

        topologySort();
        for (int i = 1; i <= n; i++) {
            if (answer[i] == Integer.MAX_VALUE) bw.write(-1 + " ");
            else bw.write(answer[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}