#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int n, int s) {
    if (s / n == 0) return {-1};
    
    vector<int> answer;
    int cnt = s % n;
    int target = s / n;
    for (int i = 0; i < n-cnt; i++) answer.push_back(target);
    target++;
    for (int i = 0; i < cnt; i++) answer.push_back(target);
    
    return answer;
}