import java.util.*;
import java.io.*;

public class Main {

    static int n, d, k, c;
    static int answer = 0;
    static int duplicate = 0;
    static int[] arr, info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        info = new int[d+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < k; i++) {
            if (info[arr[i]] == 0) answer++;
            info[arr[i]]++;
            if (info[arr[i]] >= 2) duplicate++;
        }

        if (info[c] == 0) answer++;

        for (int i = k; i < n+k-1; i++) {
            if (info[arr[i-k%n]] >= 2) duplicate--;
            info[arr[i-k%n]]--;

            info[arr[i%n]]++;
            if (info[arr[i%n]] >= 2) duplicate++;

            int cnt = k - duplicate;
            if (info[c] == 0) cnt++;

            if (answer < cnt) answer = cnt;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}