import java.io.*;
import java.util.*;

class Solution {
    static final int MAX_VALUE = 500_001;
    
    static ArrayList<ArrayList<Integer>> graph;
    
    private static void bfs(int start, int n, int[] answer, int[] sources) {
        int[] res = new int[n+1];
        Arrays.fill(res, MAX_VALUE);
        res[start] = 0;
        
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        
        while(!q.isEmpty()) {
            int node = q.poll();
            
            for (int nextNode : graph.get(node)) {
                if (res[nextNode] != MAX_VALUE) continue;
                
                res[nextNode] = res[node] + 1;
                q.offer(nextNode);
            }
        }
        
        for (int i = 0; i < sources.length; i++) {
            answer[i] = (res[sources[i]] == MAX_VALUE ? -1 : res[sources[i]]);
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        
        // 양방향
        for (int i = 0; i < roads.length; i++) {
            graph.get(roads[i][0]).add(roads[i][1]);
            graph.get(roads[i][1]).add(roads[i][0]);
        }
        
        // 거리 측정 및 정답
        bfs(destination, n, answer, sources);
        
        return answer;
    }
}