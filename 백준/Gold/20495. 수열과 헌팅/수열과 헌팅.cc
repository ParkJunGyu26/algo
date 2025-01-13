#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;
    vector<pair<long long, long long>> nums(n);
    vector<long long> sorted_min, sorted_max;

    // 입력 받기
    for (int i = 0; i < n; i++) {
        int a, b;
        cin >> a >> b;
        nums[i] = {a - b, a + b};  // 최소값, 최대값 저장
        sorted_min.push_back(a - b);
        sorted_max.push_back(a + b);
    }

    // 이분 탐색을 위해 정렬
    sort(sorted_min.begin(), sorted_min.end());
    sort(sorted_max.begin(), sorted_max.end());

    // 각 수에 대해 가능한 위치 찾기
    for (int i = 0; i < n; i++) {
        long long cur_min = nums[i].first;
        long long cur_max = nums[i].second;

        // cur_min보다 작은 최대값의 개수 + 1이 최소 위치
        int min_pos = lower_bound(sorted_max.begin(), sorted_max.end(), cur_min) - sorted_max.begin() + 1;
        
        // cur_max보다 작거나 같은 최소값의 개수가 최대 위치
        int max_pos = upper_bound(sorted_min.begin(), sorted_min.end(), cur_max) - sorted_min.begin();

        cout << min_pos << " " << max_pos << "\n";
    }

    return 0;
}