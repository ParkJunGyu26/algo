import java.io.*;
import java.util.*;

public class Main {
    static int X, Y;
    static StringBuilder sb;
    static String END = "123456780";
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    private static int bfs() {
        ArrayDeque<String[]> q = new ArrayDeque<>();
        q.offer(new String[] {sb.toString(), "0"});

        HashSet<String> visited = new HashSet<>();
        visited.add(sb.toString());

        while (!q.isEmpty()) {
            String[] cur = q.poll();
            String now = cur[0];
            int cnt = Integer.parseInt(cur[1]);

            if (now.equals(END)) return cnt;

            int index = now.indexOf("0");
            int x = index % 3;
            int y = index / 3;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (-1 < nx && nx < 3 && -1 < ny && ny < 3) {
                    int nIndex = nx + 3 * ny;
                    char nChar = now.charAt(nIndex);

                    StringBuilder nsb = new StringBuilder(now);
                    nsb.setCharAt(nIndex, '0');
                    nsb.setCharAt(index, nChar);

                    String next = nsb.toString();

                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.offer(new String[] {next, String.valueOf(cnt + 1)});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++ ) sb.append(st.nextToken());
        }

        System.out.println(bfs());
    }
}