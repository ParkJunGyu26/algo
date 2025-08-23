import java.io.*;
import java.util.*;

public class Main {
    static int n, cnt;
    static int[] answer;
    static int[][] arr;
    static PriorityQueue<int[]> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        answer = new int[2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (i1, i2) -> { // 
            int result = Integer.compare(i1[0], i2[0]);
            if (result != 0) return result;
            result = Integer.compare(i2[1], i1[1]);
            return result;
        });

        pq.offer(new int[] {arr[0][0], arr[0][1]});
        answer[0] = arr[0][0];
        answer[1] = arr[0][1];
        cnt = 1;

        for (int i = 1; i < n; i++) {
            int start = arr[i][0];
            int end = arr[i][1];

            while(!pq.isEmpty() && pq.peek()[1] <= start) {
                pq.poll();
            }

            pq.offer(new int[] {start, end});
            if (cnt < pq.size()) {
                cnt = pq.size();
                answer[0] = start;
                answer[1] = pq.peek()[1];
                if (answer[1] > end) answer[1] = end;
            } else if (cnt == pq.size()) {
                if (answer[1] == start) answer[1] = end;
            }
        }

        sb.append(cnt).append("\n").append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}