import java.io.*;
import java.util.*;

// DP
class Solution {
    static int[] dp, newDp;
    static void print(String str) {
        System.out.println(str);
    }
    
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        
        dp = new int[4];
        for (int j = 0; j < 4; j++) dp[j] = land[0][j];
        
        newDp = new int[4];
        
        for (int i = 1; i < n; i++) {
            newDp[0] += (land[i][0] + Math.max(dp[1], Math.max(dp[2], dp[3])));
            newDp[1] += (land[i][1] + Math.max(dp[0], Math.max(dp[2], dp[3])));
            newDp[2] += (land[i][2] + Math.max(dp[1], Math.max(dp[0], dp[3])));
            newDp[3] += (land[i][3] + Math.max(dp[2], Math.max(dp[1], dp[0])));
    
            for (int j = 0; j < 4; j++) {
                dp[j] = newDp[j];
                newDp[j] = 0;
                answer = Math.max(answer, dp[j]);
            }
        }

        return answer;
    }
}