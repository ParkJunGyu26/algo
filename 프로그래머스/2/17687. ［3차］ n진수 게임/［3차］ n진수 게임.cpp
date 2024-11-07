#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

char tmp[16] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

string setting(int number, int n) {
    string answer = "";
    
    while (true) {
        if (number / n == 0) {
            answer += tmp[number%n];
            reverse(answer.begin(), answer.end());
            return answer;
        }
        answer += tmp[number%n];
        number /= n;
    }
}

// 10진수 -> 2진수뿐만 아니라 16진수로도 바꿔볼 수 있어야됨
string solution(int n, int t, int m, int p) {
    string answer = "";
    int now_person = 1; // 전체 사람 순서
    int now_number = 0; // 현재 변환될 십진수
    while (true) {
        string change = setting(now_number, n); // 해당 n진수로 바뀐 값
        now_number++;
        
        for (int i = 0; i < change.size(); i++) {
            if (now_person == p) {
                answer += change[i];
                p += m;
                t--;
                if (t == 0) return answer;
            }
            now_person++;
        }
    }
    
    return answer;
}