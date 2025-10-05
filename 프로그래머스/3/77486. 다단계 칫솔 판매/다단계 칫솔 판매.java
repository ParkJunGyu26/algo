import java.io.*;
import java.util.*;

class Solution {
    static int n, m;
    static HashMap<String, String> parent; // (자식, 부모)
    static HashMap<String, Integer> res;
    
    private static void dfs(String node, Integer money) {
        int haveMoney = res.getOrDefault(node, 0);
        int divide = (money / 10);
        
        res.put(node, haveMoney + money - divide);
        if (parent.get(node) == null) return;
        
        if (divide > 0) dfs(parent.get(node), divide);
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        n = enroll.length;
        m = seller.length;
        parent = new HashMap<>();
        res = new HashMap<>();
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            String c = enroll[i];
            String p = referral[i];
            
            parent.put(c, p);
        }
        
        for (int i = 0; i < m; i++) {
            dfs(seller[i], amount[i] * 100);
        }
        
        for (int i = 0; i < n; i++) {
            answer[i] = res.getOrDefault(enroll[i], 0);
        }
        
        return answer;
    }
}