import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static HashMap<String, Long> answer;
    static HashMap<String, Integer> parent;
    static HashMap<String, ArrayList<String>> childTree;

    static void topologySort(String root) {
        Queue<String> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            String node = q.poll();

            for (String nNode : childTree.getOrDefault(node, new ArrayList<>())) {
                parent.put(nNode, parent.get(nNode) - 1);
                answer.put(nNode, answer.getOrDefault(nNode, 0L) + (answer.get(node) / 2));

                if (parent.get(nNode) <= 0) {
                    q.offer(nNode);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        String root = br.readLine();
        parent = new HashMap<>();
        childTree = new HashMap<>();
        answer = new HashMap<>();

        parent.put(root, 0);
        long MAX = 2L;
        for (int i = 0; i < n; i++) MAX *= 2;
        answer.put(root, MAX);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] tmp = new String[3];
            for (int j = 0; j < 3; j++) tmp[j] = st.nextToken();

            int cnt = 0;
            cnt += (parent.get(tmp[1]) == null ? 0 : 1);
            cnt += (parent.get(tmp[2]) == null ? 0 : 1);
            parent.put(tmp[0], cnt);
            
            ArrayList<String> left = childTree.getOrDefault(tmp[1], new ArrayList<>());
            ArrayList<String> right = childTree.getOrDefault(tmp[2], new ArrayList<>());
            left.add(tmp[0]);
            childTree.put(tmp[1], left);

            right.add(tmp[0]);
            childTree.put(tmp[2], right);
        }

        topologySort(root);

        String ans = "";
        long max = 0L;
        for (int i = 0; i < m; i++) {
            String info = br.readLine();
            long num = answer.getOrDefault(info, 0L);

            if (max < num) {
                max = num;
                ans = info;
            }
        }

        System.out.println(ans);
    }
}