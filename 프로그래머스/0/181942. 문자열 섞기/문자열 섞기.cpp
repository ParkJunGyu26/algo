#include <string>
#include <vector>
#include <iostream>

using namespace std;

string solution(string str1, string str2) {
    string answer = "";
    for (int i = 0; i < str1.size(); i++) {
        /*
        answer += (str1[i] + str2[i]);
        c++에서는 문자(char)끼리 더하면 해당 문자의 아스키 코드 값이 더해진다.
        */
        answer += str1[i];
        answer += str2[i];
    }
    return answer;
}