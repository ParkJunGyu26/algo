import java.util.*;
import java.io.*;

public class Main {
    static String first, second;

    private static String SUM() {
        StringBuilder result = new StringBuilder();
        String minString, maxString;
        if (first.length() > second.length()) {
            minString = second;
            maxString = first;
        } else {
            minString = first;
            maxString = second;
        }

        int min = minString.length();
        int max = maxString.length();

        int add = 0;
        for (int i = 0; i < min; i++) {
            int fChar = first.charAt(i) - '0';
            int sChar = second.charAt(i) - '0';

            result.append((add + fChar + sChar) % 2);
            add = (add + fChar + sChar) / 2;
        }

        for (int i = min; i < max; i++) {
            int maxChar = maxString.charAt(i) - '0';

            result.append((maxChar + add) % 2);
            add = (add + maxChar) / 2;
        }
        if (add == 1) result.append(add);

        boolean check = true;
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '1') check = false;
        }

        if (check) return "0";

        for (int i = result.length()-1; i >= 0; i--) {
            if (result.charAt(i) == '1') break;

            result.deleteCharAt(i);
        }

        return result.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        first = new StringBuilder(st.nextToken()).reverse().toString();
        second = new StringBuilder(st.nextToken()).reverse().toString();

        System.out.println(SUM());
    }
}