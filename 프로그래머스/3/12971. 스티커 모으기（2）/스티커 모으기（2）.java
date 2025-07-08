class Solution {
    public int solution(int[] sticker) {
        int len = sticker.length;
        if (len == 1) return sticker[0];

        // Case 1: 첫 번째 스티커를 선택하는 경우
        int[] DP1 = new int[len];
        DP1[0] = sticker[0];
        DP1[1] = Math.max(sticker[0], sticker[1]);
        for (int i = 2; i < len - 1; i++) {
            DP1[i] = Math.max(DP1[i - 1], DP1[i - 2] + sticker[i]);
        }

        // Case 2: 첫 번째 스티커를 선택하지 않는 경우
        int[] DP2 = new int[len];
        DP2[1] = sticker[1];
        for (int i = 2; i < len; i++) {
            DP2[i] = Math.max(DP2[i - 1], DP2[i - 2] + sticker[i]);
        }

        return Math.max(DP1[len - 2], DP2[len - 1]);
    }
}
