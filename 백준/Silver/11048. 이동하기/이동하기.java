import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 단 2개의 행만 유지
        int[] prevDp = new int[m + 1];
        int[] currDp = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // prevDp는 (i-1)행, currDp는 (i)행을 의미
                int maxPrev = Math.max(prevDp[j],           // 위에서 오는 경우
                                    Math.max(currDp[j-1],   // 왼쪽에서 오는 경우
                                             prevDp[j-1]));  // 왼쪽 위 대각선에서 오는 경우
                currDp[j] = maxPrev + graph[i][j];
            }
            // 현재 행 계산이 끝났으면, 다음 루프를 위해 currDp를 prevDp로 복사
            prevDp = Arrays.copyOf(currDp, m + 1);
        }

        System.out.println(prevDp[m]); // 마지막 행의 최종값이 정답
    }
}