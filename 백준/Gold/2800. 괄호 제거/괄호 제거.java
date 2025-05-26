import java.util.*;
import java.io.*;

public class Main {
    static String str;
    static int[] indexArr;
    static ArrayList<Integer> stack;
    static TreeSet<String> answer = new TreeSet<>();
    static int cnt = 0;

    static void dfs(int size, int index, ArrayList<Integer> hubo) {
        if (hubo.size() == size) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                boolean check = true;

                for (int j = 0; j < hubo.size(); j++) {
                    if (indexArr[i] == hubo.get(j)) check = false; 
                }

                if (check) sb.append(str.charAt(i));
            }

            answer.add(sb.toString());

            return;
        }

        for (int i = index; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                hubo.add(i);
                dfs(size, i+1, hubo);
                hubo.remove(hubo.size()-1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        str = br.readLine();
        indexArr = new int[str.length()];
        Arrays.fill(indexArr, -1);

        // 괄호 쌍 개수
        for (int i = 0; i < str.length(); i++) if (str.charAt(i) == '(') cnt++;

        // 괄호 쌍 맞추기
        stack = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                indexArr[i] = i;
                stack.add(i);
            } else if (str.charAt(i) == ')') {
                indexArr[i] = stack.get(stack.size()-1);
                stack.remove(stack.size()-1);
            }
        }
        
        ArrayList<Integer> hubo = new ArrayList<>();
        for (int i = 1; i <= cnt; i++) { // 괄호 제거 개수(i)
            dfs(i, 0, hubo);
        }

        for (String ans : answer) {
            bw.write(ans + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }
}

/*
 * 
 * (1)+(2)+(3)
 * 
 * 
 */