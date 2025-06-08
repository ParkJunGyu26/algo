import java.util.*;
import java.io.*;

public class Main {
    static int n, t;
    static int[] dx = {-2, -2, -2, -1, -1, -1, 0, 0, 1, 1, 1, 2, 2, 2, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2}, dy = {0, 1, 2, 0, 1, 2, 1, 2, 0, 1, 2, 0, 1, 2, -1, -2, -1, -2, -1, -2, -1, -2, -1, -2};
    static HashMap<String, Integer> hm;

    // 좌표를 String 키로 변환하는 메서드
    static String makeKey(int x, int y) {
        return x + "," + y;
    }

    static boolean inRange(int x, int y) {
        return (-1 < x && x <= 1000000 && -1 < y && y <= t);
    }

    static int bfs() {
        Queue<int[]> q = new LinkedList<>(); // [x, y] 배열로 좌표 저장
        String startKey = makeKey(0, 0);
        hm.put(startKey, 0);
        q.offer(new int[]{0, 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if (y == t) {
                return hm.get(makeKey(x, y));
            }

            for (int i = 0; i < 24; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!inRange(nx, ny)) continue;

                String nKey = makeKey(nx, ny);
                if (!hm.containsKey(nKey) || hm.get(nKey) != 0) continue;

                q.offer(new int[]{nx, ny});
                hm.put(nKey, hm.get(makeKey(x, y)) + 1);
            }
        }

        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int x, y;
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            hm.put(makeKey(x, y), 0);
        }

        bw.write(String.valueOf(bfs()));
        bw.flush();
        bw.close();
        br.close();
    }
} 