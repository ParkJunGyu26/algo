import java.util.*;
import java.io.*;

public class Main {
    static int n, m, start, end, money;
    static int[] res;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        int to, dist;
        Node (int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static class Info {
        Node node;
        int totalMoney;

        Info (Node node, int totalMoney) {
            this.totalMoney = totalMoney;
            this.node = node;
        }
    }

    static void bfs() {
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(new Node(start, 0), 0));
        res[start] = 0;

        while (!q.isEmpty()) {
            Info cur = q.poll();
            Node curNode = cur.node;
            int totalUsedMoney = cur.totalMoney;
            
            for (Node next : graph.get(curNode.to)) {
                if (next.dist + totalUsedMoney > money) continue;

                if (res[next.to] == 0 || res[next.to] == Integer.MAX_VALUE) res[next.to] = next.dist;
                else {
                    if (res[next.to] >= res[curNode.to]) continue;
                    res[next.to] = Math.min(res[next.to], res[curNode.to]);
                }
                q.offer(new Info(next, next.dist + totalUsedMoney));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        money = Integer.parseInt(st.nextToken());

        res = new int[n+1];
        Arrays.fill(res, Integer.MAX_VALUE);
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int from, to, dist;
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
            graph.get(to).add(new Node(from, dist));
        }

        bfs();

        int answer = res[end] == Integer.MAX_VALUE ? -1 : res[end];
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}