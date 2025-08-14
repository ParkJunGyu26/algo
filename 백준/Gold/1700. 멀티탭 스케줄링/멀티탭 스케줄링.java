import java.io.*;
import java.util.*;

public class Main {
    static int n, k, answer = 0;
    static int[] arr;
    static HashMap<Integer, TreeSet<Integer>> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[k];
        hm = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            TreeSet<Integer> ts = hm.getOrDefault(arr[i], new TreeSet<>());
            ts.add(i);
            hm.put(arr[i], ts);
        }

        HashSet<Integer> using = new HashSet<>();
        for (int i = 0; i < k; i++) {
            TreeSet<Integer> now = hm.get(arr[i]);
            now.remove(i);

            if (using.size() == n) {
                if (!using.contains(arr[i])) {
                    int target = 0; // 숫자
                    int index = 0; // 인덱스 번호
                    answer++;

                    for (int num : using) {
                        if (!hm.containsKey(num)) {
                            target = num;
                            break;
                        }

                        TreeSet<Integer> indexSet = hm.getOrDefault(num, new TreeSet<>());
                        int lastIndex = indexSet.first();

                        if (lastIndex > index) {
                            index = lastIndex;
                            target = num;
                        }
                    }

                    using.remove(target);
                }
            }
            using.add(arr[i]);

            if (now.isEmpty()) hm.remove(arr[i]);
            else hm.put(arr[i], now);
        }

        System.out.println(answer);
    }
}