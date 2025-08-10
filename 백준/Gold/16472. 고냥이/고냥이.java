import java.util.*;
import java.io.*;

public class Main {
    static int n, answer = 0;
    static String S;
    static int[] countArr;

    // 투포인터 + 자료구조
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        S = br.readLine();

        countArr = new int[26];
        int left = 0;
        int right = 0;

        int cnt = 0; // 사용한 알파벳 개수
        while (left <= right && right < S.length()) {
            int rightChr = S.charAt(right) - 'a';

            if (countArr[rightChr] == 0) {
                while (cnt + 1 > n) {
                    int leftChr = S.charAt(left) - 'a';
                    countArr[leftChr]--;
                    if (countArr[leftChr] == 0) cnt--;
                    left++;
                }
                cnt++;
            }
            countArr[rightChr]++;
            answer = Math.max(answer, (right - left + 1));
            right++;
        }
        System.out.println(answer);
    }
}