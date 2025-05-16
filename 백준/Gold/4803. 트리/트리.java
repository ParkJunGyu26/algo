import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] parent, rank;
    static ArrayList<ArrayList<Integer>> graph;

    static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            parent[y] = x;
            parent[x] = 0;
            return;
        } 

        if (x > y) parent[x] = y;
        else parent[y] = x;
    }

    // 사이클 유무 판단 -> 유니온 파인드로 파인드 했는데, 동일하면 그건 사이클! -> 이것들은 모두 다 -1 로!!
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int index = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                bw.close();
                br.close();
                return;
            }

            graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            parent = new int[n+1];
            rank = new int[n+1];
            for (int i = 1; i <= n; i++) parent[i] = i;

            int answer = 0;
            for (int i = 0; i < m; i++) {
                int u, v;
                st = new StringTokenizer(br.readLine());

                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                
                union(u, v);
            }

            Set<Integer> tree = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int target = find(i);
                if (parent[target] > 0) tree.add(target);
            }

            int cnt = tree.size();
            bw.write("Case " + index++ + ": ");
            if (cnt == 0) bw.write("No trees.");
            else if (cnt == 1) bw.write("There is one tree.");
            else bw.write("A forest of " + cnt + " trees.");
            bw.write("\n");
            bw.flush();
        }
    }
}