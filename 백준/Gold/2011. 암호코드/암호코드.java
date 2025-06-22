import java.util.*;
import java.io.*;

public class Main {
    static int MOD = 1_000_000, answer = 0;;
    static int[][] dp;
    static String code;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        code = br.readLine();
        if (code.charAt(0) == '0') {
            System.out.println(0);
            return;
        }
        
        dp = new int[code.length()][2];
        dp[0][0] = 1;
        dp[0][1] = 0;

        for (int i = 1; i < code.length(); i++) {
            if(code.charAt(i) != '0')dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;

            int twoLetter = (code.charAt(i-1) - '0') * 10 + (code.charAt(i) - '0');

            if (twoLetter == 0) {
                answer = -1;
                break;
            }

            if (1 <= twoLetter && twoLetter <= 26) {
                if (code.charAt(i-1) == '0') continue;

                if (i == 1) dp[i][1] = 1;
                else dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % MOD;
            }
        }

        bw.write(((answer == -1) ? 0 : (dp[code.length()-1][0] + dp[code.length()-1][1])%MOD) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}