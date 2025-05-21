import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int MAX_SIZE = 200_001;
    static int[] visited = new int[MAX_SIZE];
    static int[] parent = new int[MAX_SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            bw.write((n - k) + "\n");
            for (int i = n; i >= k; i--) bw.write(i + " ");
            bw.flush();
            bw.close();
            br.close();
            return;
        }

        Arrays.fill(visited, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = 0;
        parent[n] = -1;

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == k) break; // 올바른 종료 조건!
            for (int next : new int[]{cur * 2, cur + 1, cur - 1}) {
                if (0 <= next && next < MAX_SIZE && visited[next] == -1) {
                    visited[next] = visited[cur] + 1;
                    parent[next] = cur;
                    q.offer(next);
                }
            }
        }

        bw.write(visited[k] + "\n");
        Stack<Integer> stack = new Stack<>();
        for (int i = k; i != -1; i = parent[i]) stack.push(i);
        while (!stack.isEmpty()) bw.write(stack.pop() + " ");
        bw.flush();
        bw.close();
        br.close();
    }
}
