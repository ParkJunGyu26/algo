import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static Pair[] arr;
    static HashSet<Integer> visited;

    static class Pair implements Comparable<Pair> {
        int deadline, cnt;
        Pair(int deadline, int cnt) {
            this.deadline = deadline;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair p) {
            return this.deadline - p.deadline;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        visited = new HashSet<>();

        // [0] : 데드라인, [1] : 컵라면수
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i1[1], i2[1]));
        arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            int a, b;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            arr[i] = new Pair(a, b);
        }
        Arrays.sort(arr);

        for (Pair pair : arr) {
            int deadline = pair.deadline;
            int cnt = pair.cnt;

            if (pq.isEmpty()) {
                pq.offer(new int[] {deadline, cnt});
                visited.add(deadline);
            } else {
                int[] top = pq.peek();
                int nowDeadline = top[0];
                int nowCnt = top[1];

                if (!visited.contains(deadline) || pq.size() < deadline) {
                    pq.offer(new int[] {deadline, cnt});
                    visited.add(deadline);
                } else {
                    if (nowCnt < cnt) {
                        visited.remove(nowDeadline);
                        pq.poll();
                        pq.offer(new int[] {deadline, cnt});
                        visited.add(deadline);
                    }
                }
            }
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            answer += cur[1];
        }

        System.out.println(answer);
    }
}