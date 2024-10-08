#include <string>
#include <vector>
#include <iostream>

using namespace std;

int changeBin(int num) {
    int cnt = 0;
    while (num) {
        if (num % 2 == 1) cnt++;
        num /= 2;
    }
    return cnt;
}

int solution(int n) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int target = n+1;
    while (changeBin(n) != changeBin(target)) {
        target++;
    }
    
    return target;
}