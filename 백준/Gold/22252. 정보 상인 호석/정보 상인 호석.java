import java.util.*;
import java.io.*;

public class Main {

    static int q, k, c, b, query;
    static String name;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        q = Integer.parseInt(br.readLine());
        
        StringTokenizer st;

        HashMap<String, PriorityQueue<Integer>> info = new HashMap<>();

        long answer = 0;
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            query = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            if (query == 1) {
                k = Integer.parseInt(st.nextToken());
                
                for (int i = 0; i < k; i++) {
                    c = Integer.parseInt(st.nextToken());
                    if (!info.containsKey(name)) info.put(name, new PriorityQueue<>());
    
                    info.get(name).add(-c);
                }
            } else {
                b = Integer.parseInt(st.nextToken());

                if (info.containsKey(name)) {
                    while (!info.get(name).isEmpty() && b-- > 0) {
                        answer += (-info.get(name).poll());
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}