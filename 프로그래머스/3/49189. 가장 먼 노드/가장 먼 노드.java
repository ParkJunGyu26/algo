import java.util.*;
import java.io.*;

class Solution {
    static int answer = 0, maxLength = 0;
    static ArrayList<ArrayList<Integer>> graph;
    
    static void bfs(int start, int[] res, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        res[start] = 0;
        
        while (!q.isEmpty()) {
            int node = q.poll();
            
            for (int nNode : graph.get(node)) {
                if (res[nNode] == 0 && nNode != start) {
                    res[nNode] = res[node]+1;
                    q.offer(nNode);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) maxLength = Math.max(maxLength, res[i]);
        
        for (int i = 1; i <= n; i++) if (maxLength == res[i]) answer++;
    }
    
    public int solution(int n, int[][] edge) {
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        
        
        int[] res = new int[n+1];
        Arrays.fill(res, 0);
        bfs(1, res, n);
        
        return answer;
    }
}