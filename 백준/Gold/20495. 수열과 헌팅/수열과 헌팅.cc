#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// 최소 인덱스 탐색
int min_check(long long target, vector<long long> &sorted_MAX) {
    return lower_bound(sorted_MAX.begin(), sorted_MAX.end(), target) - sorted_MAX.begin() + 1;
}

// 최대 인덱스 탐색
int max_check(long long target, vector<long long> &sorted_MIN) {
    return upper_bound(sorted_MIN.begin(), sorted_MIN.end(), target) - sorted_MIN.begin();
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;
    vector<long long> MAX, MIN;
    vector<long long> sorted_MAX, sorted_MIN;

    // 입력 받기
    for (int i = 0; i < n; i++) {
        int a, b;
        cin >> a >> b;
        MIN.push_back(a-b);
        MAX.push_back(a+b);
    }

    // 정렬된 배열 만들기
    sorted_MIN = MIN;
    sorted_MAX = MAX;
    sort(sorted_MIN.begin(), sorted_MIN.end());
    sort(sorted_MAX.begin(), sorted_MAX.end());

    // 각 수에 대해 가능한 위치 찾기
    for (int i = 0; i < n; i++) {
        cout << min_check(MIN[i], sorted_MAX) << " " 
             << max_check(MAX[i], sorted_MIN) << "\n";
    }
    
    return 0;
}