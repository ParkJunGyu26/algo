import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] a, b;
    static String A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = br.readLine();
        B = br.readLine();

        a = new int[26];
        b = new int[26];
        for (int i = 0; i < n; i++) {
            a[A.charAt(i) - 'a']++;
            b[B.charAt(i) - 'a']++;
        }

        // 조건1
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                System.out.println("NO");
                return;
            }
        }

        // 조건2
        if (A.charAt(0) != B.charAt(0) || A.charAt(n-1) != B.charAt(n-1)) {
            System.out.println("NO");
            return;
        }

        // 조건3
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (A.charAt(i) != 'a' && A.charAt(i) != 'e' && A.charAt(i) != 'i' && A.charAt(i) != 'o' && A.charAt(i) != 'u') sb1.append(A.charAt(i));
            if (B.charAt(i) != 'a' && B.charAt(i) != 'e' && B.charAt(i) != 'i' && B.charAt(i) != 'o' && B.charAt(i) != 'u') sb2.append(B.charAt(i));
        }

        if (sb1.toString().equals(sb2.toString())) System.out.println("YES");
        else System.out.println("NO");
    }
}