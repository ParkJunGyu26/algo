import java.util.*;
import java.io.*;

public class Main {
    static int n, answer = Integer.MAX_VALUE;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = n-1;
        while (left < right) {
            int sum = A[left] + A[right];

            if (sum == 0) {
                System.out.println(0);
                return;
            }

            if (Math.abs(answer) > Math.abs(sum)) answer = sum;

            if (sum < 0) left++;
            else right--;
        }

        System.out.println(answer);
    }
}
