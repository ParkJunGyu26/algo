#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <cmath>

#define MAX_SIZE 1000001

int sosu[MAX_SIZE];

using namespace std;

string make_string_number(int n, int k) {
    string change = "";
    while (true) {
        if (n / k == 0) {
            change += to_string(n%k);
            reverse(change.begin(), change.end());
            return change;
        }
        change += to_string(n%k);
        n /= k;
    }
}

bool check_sosu(long num) {
    if (num == 1) return false;
    long i;
    int cnt = 0;
    for (i = 1; i < (long)sqrt(num)+1; i++)
        if (num % i == 0) cnt ++;
    
    return (cnt == 1);
}

// 에라토스테네스로 하니깐 배열 범위 초과되는듯
// 소수 판별 제곱근 이용해보기
int solution(int n, int k) {
    int answer = 0;
    
    // 에라토스테네스의 체 쓴 배열 만들어놓기
    // sosu[0] = sosu[1] = 1; 
    // eratos();
    
    string change = make_string_number(n, k); // n을 k진수로 변환
    
    string target = "";
    for (int i = 0; i < change.size(); i++) {
        if (change[i] != '0') target += change[i];
        
        if (change[i] == '0' || i == change.size()-1) {
            if (target.size() > 0 && check_sosu(stol(target))) answer++;
            target = "";
        }
        
    }
    
    // if (target.size() > 0 && check_sosu(stoll(target))) answer++;
    
    return answer;
}