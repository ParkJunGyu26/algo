import java.io.*;
import java.util.*;

// P : 응시자 . O : 빈 테이블 . X : 파티션
class Solution {
    static int n = 5;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    
    private static boolean bfs(int[] dot, String[][] graph, int index) {
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(res[i], Integer.MAX_VALUE);
        res[dot[1]][dot[0]] = 0;
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(dot);
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if (!(-1 < nx && nx < n && -1 < ny && ny < n)) continue;
                
                if (graph[index][ny].charAt(nx) == 'X') continue;
                
                if (res[ny][nx] != Integer.MAX_VALUE) continue;
                
                res[ny][nx] = res[y][x] + 1;
                
                if (graph[index][ny].charAt(nx) == 'P') {
                    if (res[ny][nx] <= 2) return false;
                    continue;
                }
                
                q.offer(new int[] {nx, ny});
            }
        }
        return true;
    }
    
    public int[] solution(String[][] places) {
        
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            ArrayList<int[]> list = new ArrayList<>();
            
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        list.add(new int[] {k, j});
                    }
                }
            }
            
            boolean check = true;
            for (int[] dot : list) {
                check = bfs(dot, places, i);
                if (!check) break;
            }
            
            answer[i] = (check ? 1 : 0);
        }
        
        return answer;
    }
}