import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] S = new String[n];
        for (int i = 0; i < n; i++) S[i] = br.readLine();

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        int root = -1;
        for (int i = 0; i < n - 1; i++) {
            String[] parts = br.readLine().split(" ");
            int a = Integer.parseInt(parts[0]) - 1;
            int b = Integer.parseInt(parts[1]) - 1;
            graph.get(a).add(b);
            root = a;
        }

        // 반복 DFS와 StringBuilder 사용
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            sb.append(S[cur]);
            // 자식 노드를 역순으로 넣어야 원래 순서와 동일
            List<Integer> children = graph.get(cur);
            for (int i = children.size() - 1; i >= 0; i--) {
                stack.push(children.get(i));
            }
        }

        System.out.print(sb.toString());
    }
}
