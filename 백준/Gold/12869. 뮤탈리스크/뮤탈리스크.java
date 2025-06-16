import java.util.*;
import java.io.*;

public class Main {
    static int n, answer = Integer.MAX_VALUE;
    static int[] arr;
    static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9, 1} , {1, 3, 9}, {1, 9, 3}};
    static int[][][] visited = new int[61][61][61];

    static boolean check(ArrayList<Integer> list) {
        boolean result = true;
        for (int num : list) if (num != 0) result = false;
        return result;
    }

    static void bfs() {
        Queue<ArrayList<Integer>> q = new LinkedList<>();
        ArrayList<Integer> start = new ArrayList<>();
        for (int i = 0; i < n; i++) start.add(arr[i]);

        if (n == 1) {
            visited[arr[0]][0][0] = 0;
            start.add(0);
            start.add(0);
        } else if (n == 2) {
            visited[arr[0]][arr[1]][0] = 0;
            start.add(0);
        } else {
            visited[arr[0]][arr[1]][arr[2]] = 0;
        }

        q.offer(start);
        while(!q.isEmpty()) {
            ArrayList<Integer> now = q.poll();

            if (check(now)) {
                answer = visited[0][0][0];
                return;
            }

            for (int i = 0; i < 6; i++) {
                ArrayList<Integer> next = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    next.add(now.get(j) - attack[i][j] < 0 ? 0 : now.get(j) - attack[i][j]);
                }

                if (visited[next.get(0)][next.get(1)][next.get(2)] > visited[now.get(0)][now.get(1)][now.get(2)] + 1) {
                    q.offer(next);
                    visited[next.get(0)][next.get(1)][next.get(2)] = visited[now.get(0)][now.get(1)][now.get(2)] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                for (int k = 0; k < 61; k++) visited[i][j][k] = Integer.MAX_VALUE;
            }
        }
        bfs();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
