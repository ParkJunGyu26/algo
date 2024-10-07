#include <string>
#include <vector>
#include <iostream>

using namespace std;


int solution(int n) {
    int answer = 0;
    for (int i = 1; i <= n; i++) {
        int cnt = 0;
        for (int j = i; j <= n; j++) {
            if (cnt + j <= n) {
                cnt += j;
                if (cnt == n) {
                    answer++;
                    break;
                }
            } else {
                break;
            }
        }
    }
    
    return answer;
}