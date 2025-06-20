import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int left = 0;
            int right = n - 1;
            int minAbsDiff = Integer.MAX_VALUE;

            while (left < right) {
                int sum = arr[left] + arr[right];
                int currentAbsDiff = Math.abs(sum - k);

                minAbsDiff = Math.min(minAbsDiff, currentAbsDiff);

                if (sum < k) {
                    left++;
                } else if (sum > k) {
                    right--;
                } else {
                    minAbsDiff = 0;
                    break;
                }
            }

            long count = 0;
            left = 0;
            right = n - 1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                int currentAbsDiff = Math.abs(sum - k);

                if (currentAbsDiff == minAbsDiff) {
                    count++;
                }

                if (sum < k) {
                    left++;
                } else {
                    right--;
                }
            }

            bw.write(count + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}