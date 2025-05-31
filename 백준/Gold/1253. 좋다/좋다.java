import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) A[i] = Long.parseLong(st.nextToken());
        Arrays.sort(A);

        int count = 0;
        for (int k = 0; k < n; k++) {
            int i = 0, j = n - 1;
            while (i < j) {
                if (i == k) { i++; continue; }
                if (j == k) { j--; continue; }
                long sum = A[i] + A[j];
                if (sum == A[k]) {
                    count++;
                    break;
                }
                if (sum < A[k]) i++;
                else j--;
            }
        }
        System.out.println(count);
    }
}
