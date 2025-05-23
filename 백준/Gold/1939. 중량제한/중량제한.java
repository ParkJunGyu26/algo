import java.util.*;
import java.io.*;

public class Main {
    static int n, m, start, end;
    static int[] res;
    static ArrayList<ArrayList<Node>> graph;

    static class Node {
        int to, dist;

        Node (int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n2.dist, n1.dist)); // 최대힙
        pq.offer(new Node(start, Integer.MAX_VALUE)); // 시작점의 중량 제한은 무한대
        res[start] = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int node = cur.to;

            if (node == end) return; // 도착하면 바로 종료

            for (Node next : graph.get(node)) {
                int nNode = next.to;
                int move = next.dist;
                int now = Math.min(res[node], move);

                // 더 큰 중량 제한으로 nNode에 도달할 수 있으면 갱신
                if (res[nNode] < now) {
                    res[nNode] = now;
                    pq.offer(new Node(nNode, now));
                }
            }
        }
    }

    // 다익스트라인데, 최대힙 경로 중에서 최소값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        res = new int[n+1];
        for (int i = 1; i <= n; i++) res[i] = 0;
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            // 양방향
            int a, b, c;
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();

        bw.write(String.valueOf(res[end]));
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
 * 
 * 
6 5
1 2 1
2 3 2
3 4 3
4 5 4
5 6 5
1 6
-> 1


8 7
1 2 2
2 4 2
4 6 2
6 8 4
1 3 3
3 5 1
5 7 2
1 8
-> 2
 * 
 */