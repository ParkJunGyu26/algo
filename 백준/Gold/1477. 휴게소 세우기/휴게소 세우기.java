import java.io.*;
import java.util.*;

public class Main {
    static int n, m, l, answer = Integer.MAX_VALUE;
    static ArrayList<Integer> list, gap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        if (n == 0) {
            answer = (l % (m+1) == 0) ? 0 : 1;
            System.out.println(answer + (l / (m+1)));
            return;
        }

        list = new ArrayList<>();
        gap = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) list.add(Integer.parseInt(st.nextToken()));
        list.sort((i1, i2) -> Integer.compare(i1, i2));

        int before = 0;
        for (int i = 0; i < n; i++) {
            gap.add(list.get(i) - before);
            before = list.get(i);
        }
        gap.add(l - before);
        gap.sort((i1, i2) -> Integer.compare(i1, i2));

        int left = 1;
        int right = gap.get(gap.size()-1);

        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = 0;
            for (int num : gap) {
                cnt += (num % mid == 0) ? (num / mid) - 1 : (num / mid);
            }

            if (cnt <= m) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.println(answer);
    }
}