import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String s = br.readLine();
        String bomb = br.readLine();
        
        StringBuilder stack = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            stack.append(s.charAt(i));
            
            if (stack.length() >= bomb.length()) {
                boolean isBomb = true;
                
                for (int j = 0; j < bomb.length(); j++) {
                    if (stack.charAt(stack.length() - bomb.length() + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }
                
                if (isBomb) {
                    stack.delete(stack.length() - bomb.length(), stack.length());
                }
            }
        }
        
        System.out.println(stack.length() > 0 ? stack.toString() : "FRULA");
    }
}