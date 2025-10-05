import java.io.*;
import java.util.*;

class Solution {
    static int n, m;
    static HashMap<String, String> parent; // (자식 → 부모)
    static HashMap<String, Integer> res;   // (사람 → 이익금)

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        n = enroll.length;
        m = seller.length;
        parent = new HashMap<>();
        res = new HashMap<>();
        int[] answer = new int[n];

        // 부모 관계 세팅
        for (int i = 0; i < n; i++) {
            String c = enroll[i];
            String p = referral[i];
            parent.put(c, p.equals("-") ? null : p);
        }

        // 판매 기록 처리
        for (int i = 0; i < m; i++) {
            String cur = seller[i];
            int money = amount[i] * 100;

            while (cur != null && money > 0) {
                int give = money / 10; // 부모에게 줄 돈
                int keep = money - give; // 자신이 가져가는 돈

                res.put(cur, res.getOrDefault(cur, 0) + keep);

                cur = parent.get(cur); // 부모로 이동
                money = give;          // 부모에게 줄 돈으로 갱신
            }
        }

        // 결과 배열 생성
        for (int i = 0; i < n; i++) {
            answer[i] = res.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}
