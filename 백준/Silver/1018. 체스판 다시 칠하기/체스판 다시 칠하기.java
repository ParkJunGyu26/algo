import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char[][] graph;
    static String[] compareString;

    static int compare(int j, int i) {
        int result = Integer.MAX_VALUE;
        for (int k = 0; k < 2; k++) {
            int index = k;
            int cnt = 0;

            for (int y = i; y < i+8; y++) {
                for (int x = j; x < j+8; x++) {
                    if (graph[y][x] != compareString[index % 2].charAt(x-j)) cnt++;
                }
                index++;
            }

            result = Math.min(result, cnt);
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        // 입력 최적화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력값 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];

        for (int i = 0; i < n; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < m; j++) graph[i][j] = tmp.charAt(j);
        }

        // 비교 문자열 초기화
        compareString = new String[2];
        compareString[0] = "WBWBWBWB";
        compareString[1] = "BWBWBWBW";

        // 로직
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= n-8; i++) {
            for (int j = 0; j <= m-8; j++) {
                answer = Math.min(answer, compare(j, i));
            }
        }

        System.out.println(answer);
    }
}