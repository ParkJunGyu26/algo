import java.util.*;
import java.io.*;

public class Main {
    static int n, robot1, robot2;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<Integer> edgeList;

    static class Node {
        int to, dist;

        Node (int t, int d) {
            to = t;
            dist = d;            
        }
    }

    static void dfs(int node, int target, ArrayList<Integer> huboList) {
        if (node == target) {
            edgeList = new ArrayList<>(huboList);
            return;
        }

        for (Node next : graph.get(node)) {
            int nNode = next.to;
            int dist = next.dist;

            if (!visited[nNode]) {
                visited[nNode] = true;
                huboList.add(dist);
                dfs(nNode, target, huboList);
                huboList.remove(huboList.size()-1);
                visited[nNode] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        robot1 = Integer.parseInt(st.nextToken());
        robot2 = Integer.parseInt(st.nextToken());
        
        visited = new boolean[n+1];
        edgeList = new ArrayList<>();
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < n-1; i++) {
            int from, to, dist;
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, dist));
            graph.get(to).add(new Node(from, dist));
        }

        ArrayList<Integer> huboList = new ArrayList<>();
        visited[robot1] = true;
        dfs(robot1, robot2, huboList);

        int MAX = 0;
        int answer = 0;
        for (int dist : edgeList) {
            MAX = Math.max(MAX, dist);
            answer += dist;
        }
        
        bw.write(String.valueOf(answer - MAX));
        bw.flush();
        bw.close();
        br.close();
    }
}