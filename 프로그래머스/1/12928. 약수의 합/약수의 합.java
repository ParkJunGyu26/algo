import java.util.*;
import java.io.*;

// 약수 : 브루트포스 / 제곱근 / 에라토스테네스
class Solution {
    public static int solve1(int n) { // 브루트포스
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                result += i;
            }
        }
    
        return result;
    }
    
    public int solution(int n) {
        int answer = 0;
        
        answer = solve1(n);
        // answer = solve2(n);
        // answer = solve3(n);
        
        return answer;
    }
}