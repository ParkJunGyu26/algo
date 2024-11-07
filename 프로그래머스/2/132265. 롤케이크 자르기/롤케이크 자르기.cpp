#include <string>
#include <vector>
#include <set>

#define MAX_SIZE 10001

int total_kind[MAX_SIZE] = {0}; // 전체 종류 누적합 -> 계수정렬(1부터 10,000이라 메모리 ㄱㅊ)
int now_kind[MAX_SIZE] = {0}; // 현재 종류 누적합

using namespace std;

// 누적합 원리로 풀자.
int solution(vector<int> topping) {
    int answer = 0;
    
    set<int> kind;
    for (auto t : topping)
        kind.insert(t);
    
    int total = kind.size(); // 전체 종류 개수 
    for (auto t : topping)
        total_kind[t]++;
    
    int now = 0; // 누적한 현재 종류 개수
    for (int i = 0; i < topping.size(); i++) {
        now_kind[topping[i]]++;
        total_kind[topping[i]]--;
        
        if (now_kind[topping[i]] == 1) now++;
        if (total_kind[topping[i]] == 0) total--;
        
        if (total == now) answer++;
    }
    
    return answer;
}