import java.io.*;
import java.util.*;

public class Main {
    static int n, m, t, k, a, b;
    static char[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        graph = new char[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) graph[i][j] = str.charAt(j);
        }

        while (t-- > 0) {
            char[][] nextGraph = new char[n][m];
            for (int i = 0; i < n; i++) Arrays.fill(nextGraph[i], '.');

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    int cnt = 0;
                    for (int ny = i - k; ny <= i+k; ny++) {
                        for (int nx = j - k; nx <= j+k; nx++) {
                            if (!(-1 < nx && nx < m && -1 < ny && ny < n)) continue;

                            if (ny == i && nx == j) continue;

                            if (graph[ny][nx] == '*') cnt++;
                        }
                    }

                    if (graph[i][j] == '*') {
                        if (a <= cnt && cnt <= b) nextGraph[i][j] = '*';
                    } else {
                        if (a < cnt && cnt <= b) nextGraph[i][j] = '*';
                    }
                }
            }

            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++) graph[i][j] = nextGraph[i][j];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) sb.append(graph[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}