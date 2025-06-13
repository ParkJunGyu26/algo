import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (answer.get(answer.size()-1) < arr[i]) answer.add(arr[i]);
            else {
                int left = 0;
                int right = answer.size()-1;

                int index = -1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    int target = answer.get(mid);

                    if (target == arr[i]) {
                        index = mid;
                        break;
                    } else if (target < arr[i]) {
                        left = mid+1;
                    } else {
                        index = mid;
                        right = mid-1;
                    }
                }

                answer.set(index, arr[i]);
            }
        }

        bw.write(String.valueOf(answer.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}
