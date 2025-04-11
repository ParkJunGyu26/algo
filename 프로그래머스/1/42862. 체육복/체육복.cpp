#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    vector<int> vec(n+2);
    for (int i = 0; i < lost.size(); i++) vec[lost[i]]--;
    for (int i = 0; i < reserve.size(); i++) vec[reserve[i]]++;
    
    for (int i = 1; i <= n; i++) {
        if (vec[i] < 0) {
            if (vec[i-1] > 0) {
                vec[i-1]--;
                answer++;
            } else if (vec[i+1] > 0) {
                vec[i+1]--;
                answer++;
            }
        } else answer++;
    }
    
    int cnt = 0;
    vec.clear();
    for (int i = 0; i < lost.size(); i++) vec[lost[i]]--;
    for (int i = 0; i < reserve.size(); i++) vec[reserve[i]]++;
    
    for (int i = 1; i <= n; i++) {
        if (vec[i] < 0) {
            if (vec[i+1] > 0) {
                vec[i+1]--;
                cnt++;
            } else if (vec[i-1] > 0) {
                vec[i-1]--;
                cnt++;
            }
        } else cnt++;
    }
    
    return max(cnt, answer);
}