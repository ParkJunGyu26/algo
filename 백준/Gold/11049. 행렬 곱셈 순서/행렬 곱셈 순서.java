import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dp;
    static Node[] matrix;

    static class Node {
        int left, right;
        Node (int l, int r) {
            left = l;
            right = r;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        matrix = new Node[n];
        dp = new int[n+1][n+1];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrix[i] = new Node(0, 0);
            matrix[i].left = Integer.parseInt(st.nextToken());
            matrix[i].right = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            dp[i][i+1] = matrix[i-1].left * matrix[i-1].right * matrix[i].right;
        }

        for (int gap = 2; gap < n; gap++) {
            for (int start = 1; start <= n - gap; start++) {
                dp[start][start+gap] = Integer.MAX_VALUE;

                for (int end = start; end < start + gap; end++) {
                    dp[start][start+gap] = Math.min(dp[start][start+gap], dp[start][end] + dp[end+1][start+gap] + (matrix[start-1].left * matrix[end].left * matrix[start+gap-1].right));
                }
            }
        }

        System.out.println(dp[1][n]);
    }
}