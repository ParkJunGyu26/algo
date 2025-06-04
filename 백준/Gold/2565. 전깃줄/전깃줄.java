import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static ArrayList<Node> list;
    static ArrayList<Integer> st;

    static class Node {
        int index, res;

        Node(int index, int res) {
            this.index = index;
            this.res = res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        st = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index, r;
            index = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
    
            list.add(new Node(index, r));
        }
        list.sort((n1, n2) -> Integer.compare(n1.index, n2.index));

        st.add(list.get(0).res);
        for (int i = 1; i < n; i++) {
            int target = list.get(i).res;

            if (st.get(st.size()-1) < target) st.add(target);
            else {
                int left = 0;
                int right = st.size()-1;

                int index = -1;
                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (st.get(mid) > target) {
                        index = mid;
                        right = mid - 1;
                    } else if (st.get(mid) < target) {
                        left = mid + 1;
                    } else {
                        index = mid;
                        break;
                    }
                }
                st.set(index, target);
            }
        }

        bw.write(String.valueOf(n - st.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}