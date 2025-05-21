import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] A;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        A = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) A[i] = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        list.add(A[0]);
        
        for (int i = 1; i < n; i++) {
            if (A[i] > list.get(list.size()-1)) {
                list.add(A[i]);
                continue;
            }

            // A[i] 보다 큰 값 중에서 가장 작은 값의 인덱스
            // A[i] 와 동일한 값의 인덱스
            int left = 0;
            int right = list.size()-1;
            int answer = 0;

            while (left <= right) {
                int mid = (left + right) / 2;
                int target = list.get(mid);

                if (target >= A[i]) {
                    answer = mid;
                    right = mid-1;
                } else left = mid + 1;
            }

            list.set(answer, A[i]);
        }

        bw.write(String.valueOf(list.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}