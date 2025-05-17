import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations); // 오름차순 정렬
        int left = 0;
        int right = citations[citations.length - 1];
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            // mid 이상 인용된 논문 개수 세기
            int cnt = 0;
            for (int c : citations) {
                if (c >= mid) cnt++;
            }

            if (cnt >= mid) { // 조건 만족: 더 큰 값도 가능한지 탐색
                answer = mid; // 일단 저장
                left = mid + 1;
            } else { // 조건 불만족: 더 작은 값 탐색
                right = mid - 1;
            }
        }
        return answer;
    }
}
