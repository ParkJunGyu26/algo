import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;
        Set<String> allKinds = new HashSet<>(Arrays.asList(gems));
        int target = allKinds.size();

        Map<String, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int bestLeft = 0, bestRight = n-1;
        int minLength = n+1;

        while (true) {
            if (map.size() == target) {
                // 최소 구간 갱신
                if (right - left < minLength) {
                    minLength = right - left;
                    bestLeft = left;
                    bestRight = right - 1;
                }
                // 왼쪽 줄이기
                String gem = gems[left];
                map.put(gem, map.get(gem) - 1);
                if (map.get(gem) == 0) {
                    map.remove(gem);
                }
                left++;
            } else if (right == n) {
                break;
            } else {
                // 오른쪽 확장
                String gem = gems[right];
                map.put(gem, map.getOrDefault(gem, 0) + 1);
                right++;
            }
        }

        return new int[]{bestLeft + 1, bestRight + 1}; // 문제에서 1-index 반환
    }
}
