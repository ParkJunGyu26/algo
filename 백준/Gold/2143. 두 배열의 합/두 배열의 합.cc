#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t, n, m;
    cin >> t >> n;
    vector<int> A(n);
    for(int i = 0; i < n; i++) cin >> A[i];
    cin >> m;
    vector<int> B(m);
    for(int i = 0; i < m; i++) cin >> B[i];
    
    // A의 모든 부분합을 저장
    vector<int> sum_A;
    for(int i = 0; i < n; i++) {
        int sum = 0;
        for(int j = i; j < n; j++) {
            sum += A[j];
            sum_A.push_back(sum);
        }
    }
    
    // B의 모든 부분합을 저장
    vector<int> sum_B;
    for(int i = 0; i < m; i++) {
        int sum = 0;
        for(int j = i; j < m; j++) {
            sum += B[j];
            sum_B.push_back(sum);
        }
    }
    
    // B의 부분합을 정렬하여 이분 탐색 준비
    sort(sum_B.begin(), sum_B.end());
    
    long long answer = 0;
    // A의 각 부분합에 대해 B에서 필요한 값을 이분 탐색
    for(int sum_a : sum_A) {
        int target = t - sum_a;
        auto upper = upper_bound(sum_B.begin(), sum_B.end(), target);
        auto lower = lower_bound(sum_B.begin(), sum_B.end(), target);
        answer += upper - lower;
    }
    
    cout << answer;
    return 0;
}