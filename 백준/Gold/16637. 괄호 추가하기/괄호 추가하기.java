import java.io.*;
import java.util.*;

public class Main {
    static int n, answer = Integer.MIN_VALUE;
    static String str;

    private static int cal(int a, int b, char chr) {
        if (chr == '+') return a+b;
        else if (chr == '-') return a-b;
        else if (chr == '*') return a*b;
        
        return a/b;
    }

    private static void dfs(int index, int result) {
        if (index == n) {
            answer = Math.max(answer, result);
            return;
        }

        char nowCal = str.charAt(index);

        int nowNum = str.charAt(index+1) - '0';
        dfs(index+2, cal(result, nowNum, nowCal));

        if (index != n-2) {
            char nextCal = str.charAt(index+2);
            int nextNum = str.charAt(index+3) - '0';

            dfs(index+4, cal(result, cal(nowNum, nextNum, nextCal), nowCal));
            if (nowCal == '-') dfs(index+4, cal(result, cal(-nowNum, nextNum, nextCal), '+'));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        str = br.readLine();

        dfs(1, str.charAt(0) - '0');

        System.out.println(answer);
    }
}