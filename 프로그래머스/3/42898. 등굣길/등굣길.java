import java.util.*;
import java.io.*;

class Solution {
    static final int MOD = 1_000_000_007;
    static boolean[][] canMove;
    static int[][] dp;
    
    public int solution(int m, int n, int[][] puddles) {
        dp = new int[n+1][m+1];
        canMove = new boolean[n+1][m+1];
        
        for (int i = 0; i <= n; i++) canMove[i][0] = true;
        for (int i = 0; i <= m; i++) canMove[0][i] = true;
        
        for (int i = 0; i < puddles.length; i++) {
            canMove[puddles[i][1]][puddles[i][0]] = true;
        }
        
        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (canMove[i][j]) continue;
                
                if (!canMove[i-1][j]) {
                    dp[i][j] += dp[i-1][j];
                    dp[i][j] %= MOD;
                }
                
                if (!canMove[i][j-1]) {
                    dp[i][j] += dp[i][j-1];
                    dp[i][j] %= MOD;
                }
            }
        }
        
        return dp[n][m];
    }
}