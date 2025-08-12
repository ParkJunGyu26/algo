import java.io.*;
import java.util.*;

public class Main {
    static int n, m, cnt;
    static char[][] graph;
    static int[][] visited; // 0=미방문, 1=방문중, 2=완료
    static int[] dx = {-1, 1, 0, 0}; // U D L R
    static int[] dy = {0, 0, -1, 1};

    static void dfs(int x, int y) {
        visited[x][y] = 1; // 방문중

        int dir = getDir(graph[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (visited[nx][ny] == 0) {
            dfs(nx, ny);
        } else if (visited[nx][ny] == 1) {
            // 방문 중인 곳을 다시 방문 → 사이클
            cnt++;
        }

        visited[x][y] = 2; // 완료
    }

    static int getDir(char c) {
        switch (c) {
            case 'U': return 0;
            case 'D': return 1;
            case 'L': return 2;
            case 'R': return 3;
        }
        return -1; // 이 경우는 없음
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = tmp.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(cnt);
    }
}
