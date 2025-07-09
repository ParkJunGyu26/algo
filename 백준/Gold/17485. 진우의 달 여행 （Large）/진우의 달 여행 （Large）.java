import java.util.*;
import java.io.*;

class Main {
    static int n, m;
    static int[][] graph, dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        dp = new int[m][3];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) graph[i][j] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i < m; i++) {
		    for (int j = -1; j <= 1; j++) {
			    if (i + j < 0 || i + j >= m) continue;

			    dp[i][(j+1)%3] = graph[1][i] + graph[0][i+j];
		    }
	    }
        
        for (int i = 2; i < n; i++) {
            int[][] newDp = new int[m][3];
            for (int j = 0; j < m; j++) Arrays.fill(newDp[j], Integer.MAX_VALUE);
            
            for (int j = 0; j < m; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (j + k < 0 || j + k >= m) continue;
                    
                    newDp[j][k+1] = Math.min(dp[j+k][(k+2)%3], dp[j+k][(k+3)%3]) + graph[i][j];
                }
            }
            
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 3; k++) dp[j][k] = newDp[j][k];
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) answer = Math.min(answer, dp[i][j]);
        }
        
        System.out.println(answer);
    }
}