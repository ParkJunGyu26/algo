import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static String[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        A = new String[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i ++) A[i] = st.nextToken();

        Arrays.sort(A, (l1, l2) -> (l2 + l1).compareTo(l1 + l2));

        for (int i = 0; i < n; i++) sb.append(A[i]);

        int cnt = 0;
        for (int i = 0; i < sb.length(); i++) if (sb.charAt(i) == '0') cnt++;

        if (cnt == sb.length()) bw.write("0");
        else bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}