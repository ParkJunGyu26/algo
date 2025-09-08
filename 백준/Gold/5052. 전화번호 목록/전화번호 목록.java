import java.io.*;
import java.util.*;

public class Main {
    static int t;
    static HashSet<String> hs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            hs = new HashSet<>();
            for (int i = 0; i < n; i++) hs.add(br.readLine());

            boolean check = false;
            for (String str : hs) {
                if (check) break;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < str.length(); i++) {
                    sb.append(str.charAt(i));

                    if (!sb.toString().equals(str) && hs.contains(sb.toString())) {
                        check = true;
                    }
                }
            }

            if (check) answer.append("NO");
            else answer.append("YES");
            answer.append("\n");
        }

        System.out.println(answer);
    }
}