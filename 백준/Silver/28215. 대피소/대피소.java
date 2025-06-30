import java.util.*;
import java.io.*;

public class Main {
    static boolean[] visited;
    static int n, k, answer = Integer.MAX_VALUE;
    static Dot[] graph;

    static class Dot {
        int x, y;
        Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void dfs(int index, ArrayList<Integer> combi) {
        if (combi.size() == k) {
            int result = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;

                int calculate = Integer.MAX_VALUE;
                for (int j = 0; j < k; j++) {
                    int diff = Math.abs(graph[i].x - graph[combi.get(j)].x) + Math.abs(graph[i].y - graph[combi.get(j)].y);
                    calculate = Math.min(calculate, diff);
                }

                result = Math.max(result, calculate);
            }

            answer = Math.min(answer, result);
            return;
        }

        for (int i = index; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            combi.add(i);
            dfs(i+1, combi);
            combi.remove(combi.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new Dot[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            graph[i] = new Dot(x, y);
        }

        ArrayList<Integer> combi = new ArrayList<>();
        dfs(0, combi);
        System.out.println(answer);
    }
}