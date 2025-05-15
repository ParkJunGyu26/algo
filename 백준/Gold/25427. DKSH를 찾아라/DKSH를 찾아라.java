import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static String s;
    static char[] target = {'D', 'K', 'S', 'H'};
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        s = br.readLine();

        long cntD = 0, cntDK = 0, cntDKS = 0, cntDKSH = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'D') cntD++;
            else if (c == 'K') cntDK += cntD;
            else if (c == 'S') cntDKS += cntDK;
            else if (c == 'H') cntDKSH += cntDKS;
        }
        System.out.println(cntDKSH);

    }
}