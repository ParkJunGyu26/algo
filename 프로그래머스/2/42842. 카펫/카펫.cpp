#include <string>
#include <vector>
#include <cmath>
#include <iostream>

using namespace std;

// 세로 <= 가로
vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    // cout << int(sqrt(yellow));
    int hubo = 1;
    for (int i = 1; i <= int(sqrt(yellow)); i++) {
        if (yellow % i == 0 && brown == 4 + (yellow / i * 2 + i * 2)) {
            hubo = max(hubo, i);
        }
    }
    
    answer.push_back((brown + yellow) / (hubo+2));
    answer.push_back(hubo+2);
    return answer;
}

/*

노랑 : 4
12 / 4 -> [4, 4]  / [5, 1]

*/