#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    
    // 1. 옷을 종류별로 구분하기
    unordered_map<string, int> map;
    for (vector<string> cloth : clothes)
        map[cloth[1]]++;
    
    // 2. 옷을 입지 않는 경우도 포함한 모든 경우의 수 계산
    int answer = 1;
    for (const auto& it : map)
        answer *= it.second +1;
    
    // 3. 전체 집합 - (한 번도 옷을 입지 않는 경우)
    return answer - 1;
}