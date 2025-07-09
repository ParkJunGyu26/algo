import java.util.*;
import java.io.*;

class Solution {
    static long[][] dp;
    
    public long solution(int[] sequence) {
        long answer = 0;
        dp = new long[sequence.length+1][2];
        for (int i = 1; i <= sequence.length; i++) {
            dp[i][0] = Math.max(sequence[i-1], sequence[i-1] + dp[i-1][1]);
            dp[i][1] = Math.max(sequence[i-1] * -1, sequence[i-1] * -1 + dp[i-1][0]);
            answer = Math.max(answer, Math.max(dp[i][0], dp[i][1]));
        }
        
        return answer;
    }
}