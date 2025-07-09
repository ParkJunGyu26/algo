import java.util.*;
import java.io.*;

class Solution {
    static long[] dp;
    
    public long solution(int[] sequence) {
        dp = new long[2];
        dp[0] = sequence[0];
        dp[1] = -sequence[0];
        long answer = Math.max(dp[0], dp[1]);
        if (sequence.length == 1) return answer;
        
        long[] newDp;
        for (int i = 1; i <= sequence.length; i++) {
            newDp = new long[2];
            newDp[0] = Math.max(sequence[i-1], sequence[i-1] + dp[1]);
            newDp[1] = Math.max(-sequence[i-1], -sequence[i-1] + dp[0]);
            
            dp[0] = newDp[0];
            dp[1] = newDp[1];
            answer = Math.max(answer, Math.max(newDp[0], newDp[1]));
        }
        
        return answer;
    }
}