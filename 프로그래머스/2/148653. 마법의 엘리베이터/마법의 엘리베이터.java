import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static String strNum;
    
    private static void dfs(int numIndex, int maxIndex, int prefixSum, boolean roundCheck) {
        if (numIndex == maxIndex+1) {
            if (roundCheck) prefixSum++;
            
            answer = Math.min(answer, prefixSum);
            
            return;
        }
        
        int target = strNum.charAt(maxIndex - numIndex) - '0';
        if (roundCheck) target++;
        
        dfs(numIndex+1, maxIndex, prefixSum + target, false); // 내리기
        dfs(numIndex+1, maxIndex, prefixSum + (10 - target), true); // 올리기
    }
    
    public int solution(int storey) {
        strNum = String.valueOf(storey);
        
        int n = strNum.length();
        
        dfs(1, n, 0, false);
        
        return answer;
    }
}