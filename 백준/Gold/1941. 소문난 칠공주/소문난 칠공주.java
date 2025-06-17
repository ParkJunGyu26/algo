import java.util.*;
import java.io.*;

public class Main {
    static boolean[] visited = new boolean[25];
    static char[][] graph = new char[5][5];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean check(ArrayList<Integer> hubo) {
        boolean[][] isInGroup = new boolean[5][5];
        int sCount = 0;

        for (int num : hubo) {
            int y = num / 5;
            int x = num % 5;
            isInGroup[y][x] = true;
            if (graph[y][x] == 'S') {
                sCount++;
            }
        }

        if (sCount < 4) {
            return false;
        }

        boolean[][] visitedBFS = new boolean[5][5];
        Queue<Node> queue = new LinkedList<>();
        int startY = hubo.get(0) / 5;
        int startX = hubo.get(0) % 5;
        queue.add(new Node(startX, startY));
        visitedBFS[startY][startX] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }

                if (isInGroup[ny][nx] && !visitedBFS[ny][nx]) {
                    visitedBFS[ny][nx] = true;
                    count++;
                    queue.add(new Node(nx, ny));
                }
            }
        }

        return count == 7;
    }

    static void dfs(int index, ArrayList<Integer> hubo) {
        if (hubo.size() == 7) {
            if (check(hubo)) {
                answer++;
            }
            return;
        }

        for (int i = index; i < 25; i++) {
            if (!visited[i]) {
                visited[i] = true;
                hubo.add(i);
                dfs(i + 1, hubo);
                hubo.remove(hubo.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                graph[i][j] = line.charAt(j);
            }
        }

        dfs(0, new ArrayList<>());
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
