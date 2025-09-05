import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 1; i <= n; i++) set.add(i);
        
        for (int i = 0; i < n; i++) {
            if (k != 0 && k >= (n - i - 1)) {
                k -= (n - i - 1);
                arr[i] = set.last();
            } else {
                arr[i] = set.first();
            }
            set.remove(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(arr[i]).append(" ");
        System.out.println(sb);
    }
}