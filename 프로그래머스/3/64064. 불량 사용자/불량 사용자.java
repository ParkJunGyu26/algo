import java.io.*;
import java.util.*;

// 조합..
class Solution {
    int n, m;
    boolean[] visited;
    HashSet<String> answer;
    
    private void dfs(int index, String[] user_id, String[] banned_id) {
        if (index == m) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < n; i++) if (visited[i]) sb.append(String.valueOf(i));
            
            answer.add(sb.toString());
            
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            
            String target = user_id[i];
            int len = target.length();
            
            String ban = banned_id[index];
            int ban_len = ban.length();
            
            if (len != ban_len) continue;
            
            boolean result = true;
            for (int j = 0; j < len; j++) {
                char b = ban.charAt(j);
                char u = target.charAt(j);
                
                if (b == '*') continue;
                
                if (b != u) {
                    result = false;
                    break;
                }
            }
            
            if (result) {
                visited[i] = true;
                
                dfs(index+1, user_id, banned_id);
                
                visited[i] = false;
            }
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        answer = new HashSet<>();
        
        n = user_id.length;
        m = banned_id.length;
        visited = new boolean[n];
        
        dfs(0, user_id, banned_id);
        
        return answer.size();
    }
}