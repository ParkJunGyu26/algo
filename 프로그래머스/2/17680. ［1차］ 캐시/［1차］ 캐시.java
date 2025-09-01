import java.util.*;

class Solution {
    ArrayDeque<String> cache;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        cache = new ArrayDeque<>();
        int n = cities.length;
        
        if (cacheSize == 0) return 5 * n;
        
        for (int i = 0; i < n; i++) {
            String str = cities[i].toUpperCase();
            
            boolean hit = false;
            for (String target : cache) {
                if (target.equals(str)) {
                    hit = true;
                    cache.remove(target);
                    cache.addLast(target);
                    break;
                }
            }
            
            if (hit) answer++;
            else {
                answer += 5;
                
                while (!cache.isEmpty() && cache.size() >= cacheSize) cache.removeFirst();
                cache.addLast(str);
            }
        }
        return answer;
    }
}