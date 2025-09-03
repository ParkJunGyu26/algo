import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 2001;
    static int n, m, p;
    static int[] S, answer, dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static char[][] graph;
    static int[][] res;
    static ArrayList<ArrayDeque<int[]>> list;

    private static int bfs(int index, int number) {
        boolean isMove = false;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> qq = new ArrayDeque<>(list.get(index));

        while(!qq.isEmpty()) {
            int[] cur = qq.poll();
            q.offer(new int[] {cur[0], cur[1], number});
        }
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            if (cnt == 0) {
                qq.offer(cur);
                continue;
            }
    
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
    
                if (-1 < nx && nx < m && -1 < ny && ny < n) {
                    if (graph[ny][nx] == '.') {
                        isMove = true;
                        q.offer(new int[] {nx, ny, cnt-1});
                        graph[ny][nx] = (char) (index + '0');
                    }
                }
            }
        }

        list.set(index, new ArrayDeque<>(qq));

        if (isMove) return 1;

        return 0;
    }

    private static void simulation() {
        
        while (true) {
            int cnt = 0;
            for (int i = 1; i <= p; i++) {
                cnt += bfs(i, S[i]);
            }

            if (cnt == 0) break; 
        }
        
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();  
        n = Integer.parseInt(st.nextToken());                              
        m = Integer.parseInt(st.nextToken());                              
        p = Integer.parseInt(st.nextToken());

        res = new int[n][m]; // {x, y, {인덱스번호, res 값}}
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = MAX;
            }
        }

        answer = new int[p+1];
        graph = new char[n][m];
        S = new int[p+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= p; i++) S[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= p; i++) list.add(new ArrayDeque<>());

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = tmp.charAt(j);
                if ('0' < graph[i][j] && graph[i][j] <= '9') {
                    list.get(graph[i][j] - '0').addLast(new int[] {j, i});
                    res[i][j] = 0;
                }
            }
        }

        simulation();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) if ('0' < graph[i][j] && graph[i][j] <= '9') answer[graph[i][j] - '0']++;
        }

        for (int i = 1; i <= p; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb);
    }
}