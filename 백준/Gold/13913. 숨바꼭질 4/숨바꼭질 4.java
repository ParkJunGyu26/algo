import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static int MAX_SIZE;
    static int[] visited;

    static class Node {
        int num;
        ArrayList<Integer> list;

        Node (int num, ArrayList<Integer> list) {
            this.num = num;
            this.list = list;
        }
    }

    static boolean inRange(int number) {
        return (0 <= number && number < MAX_SIZE);
    }

    static void bfs() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        visited = new int[MAX_SIZE];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<Node> q = new LinkedList<>();
        ArrayList<Integer> l = new ArrayList<>();

        visited[n] = 0;
        l.add(n);
        q.offer(new Node(n, l));

        while (!q.isEmpty()) {
            Node current = q.poll();
            int num = current.num;
            ArrayList<Integer> list = current.list;

            if (num == k) {
                bw.write(String.valueOf(list.size()-1) + "\n");
                for (int index : list) bw.write(String.valueOf(index) + " ");
                bw.flush();
                bw.close();
                return;
            }

            int nNum = num * 2;
            if (inRange(nNum) && visited[nNum] > visited[num] + 1) {
                visited[nNum] = visited[num] + 1;
                ArrayList<Integer> nList = new ArrayList<>(list);
                nList.add(nNum);
                q.offer(new Node(nNum, nList));
            }

            nNum = num + 1;

            if (inRange(nNum) && visited[nNum] > visited[num] + 1) {
                visited[nNum] = visited[num] + 1;
                ArrayList<Integer> nList = new ArrayList<>(list);
                nList.add(nNum);
                q.offer(new Node(nNum, nList));
            }

            nNum = num - 1;
            if (inRange(nNum) && visited[nNum] > visited[num] + 1) {
                visited[nNum] = visited[num] + 1;
                ArrayList<Integer> nList = new ArrayList<>(list);
                nList.add(nNum);
                q.offer(new Node(nNum, nList));
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        MAX_SIZE = Math.max(n, k) + 7;
        
        if (n >= k) {
            System.out.println(n - k);
            for (int i = n; i >= k; i--) System.out.print(i + " ");
        } else bfs();
        br.close();
    }
}