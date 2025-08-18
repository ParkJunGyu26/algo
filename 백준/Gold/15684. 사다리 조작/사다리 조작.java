import java.io.*;
import java.util.*;

// 가중치 음수라면 벨만-포드!!
// 가중치 양수라면, 다익스트라 & 플로이드-워셜 & MST(최소신장트리)
public class Main {
    static int n, m, h, answer = 4;
    static int[] dx = {1, 0, -1}, dy = {0, 1, 0};
    static int[][] graph;

    private static boolean checkColumn(int index) {
        int row = index / m;
        int column = index % m;

        // 현재 가로선은 그려지지 않았다
        if (graph[row][column] != 1) return false;

        // 다음 가로선도 그려지지 않았다.
        if (index < (n*m-1)) {
            row = (index+1) / m;
            column = (index+1) % m;
    
            if (graph[row][column] != 1) return false;
        }

        return true;
    }

    private static void makeColumn(int index, int cnt, int total) {
        if (cnt == total) {

            boolean result = true;
            for (int i = 0; i < m; i++) {
                if(!dfs(i, 0, i, -1, -1)) {
                    result = false;
                    break;
                }
            }

            if (result) answer = Math.min(answer, total);

            return;
        }

        for (int i = index; i < n*m-1; i++) {
            if (!checkColumn(i) || (i+1) % m == 0) continue;

            graph[i/m][i%m] = 0;
            graph[(i+1)/m][(i+1)%m] = 2;

            // 현재 가로선 번호와 다음 가로선 번호의 행이 다른 경우는 +1
            if ((i / m) != ((i+1) / m)) makeColumn(i+1, cnt+1, total);
            else makeColumn(i+2, cnt+1, total); // 행이 같은 경우에는 +2

            graph[i/m][i%m] = graph[(i+1)/m][(i+1)%m] = 1;
        }
    }

    private static boolean dfs(int start, int row, int column, int by, int bx) {
        if (row == n) {
            return (start == column);
        }

        int nx = column + dx[graph[row][column]];
        int ny = row + dy[graph[row][column]];

        if (nx == bx && ny == by) {
            nx = column + dx[1];
            ny = row + dy[1];
        }

        return dfs(start, ny, nx, row, column);
    }

    private static int simulation() {
        boolean result = true;

        for (int column = 0; column < m; column++) {
            if (!dfs(column, 0, column, -1, -1)) {
                result = false;
                break;
            }
        }

        if (result) return 0;

        // 가로선 하나씩 만들기. 연속으로 접해선 안됨
        for (int cnt = 1; cnt < 4; cnt++) {
            makeColumn(0, 0, cnt);
        }

        return (answer == 4) ? -1 : answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(graph[i], 1);

        for (int i = 0; i < h; i++) {
            int a, b;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            
            graph[--a][b] = 2;
            graph[a][--b] = 0;
        }

        System.out.println(simulation());
    }
}