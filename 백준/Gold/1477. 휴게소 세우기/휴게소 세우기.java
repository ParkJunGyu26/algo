import java.io.*;
import java.util.*;

public class Main {
    static int n, m, l;
    static List<Integer> restAreas = new ArrayList<>();
    static List<Integer> gaps = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                restAreas.add(Integer.parseInt(st.nextToken()));
            }
            restAreas.sort(Comparator.naturalOrder());
        }

        // 구간 차이 계산
        int before = 0;
        for (int pos : restAreas) {
            gaps.add(pos - before);
            before = pos;
        }
        gaps.add(l - before);

        // 이분 탐색 범위 설정
        int left = 1;
        int right = l;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int required = getRequiredStations(gaps, mid);

            if (required <= m) {
                answer = mid;
                right = mid - 1; // 더 줄일 수 있는가?
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    private static int getRequiredStations(List<Integer> gaps, int maxLength) {
        int count = 0;
        for (int gap : gaps) {
            count += (gap - 1) / maxLength;
        }
        return count;
    }
}
