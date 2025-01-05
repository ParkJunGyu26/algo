#include <iostream>
#include <vector>
#include <climits>

#define ll long long

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    ll n;
    cin >> n;
    vector<ll> vec(n);
    for (int i = 0; i < n; i++) cin >> vec[i];

    // 배열 크기가 2일 경우 바로 출력
    if (n == 2) {
        cout << vec[0] << " " << vec[1];
        return 0;
    }

    int left = 0, right = n - 1;
    ll diff = LLONG_MAX; // 절댓값 비교를 위한 초기화
    ll answer_left = 0, answer_right = 0;

    while (left < right) {
        ll current_sum = vec[left] + vec[right];

        // 현재 합이 0이라면 바로 출력 후 종료
        if (current_sum == 0) {
            cout << vec[left] << " " << vec[right];
            return 0;
        }

        // 절댓값이 더 작다면 결과 갱신
        if (abs(current_sum) < abs(diff)) {
            diff = current_sum;
            answer_left = vec[left];
            answer_right = vec[right];
        }

        // 포인터 이동
        if (current_sum < 0) {
            left++;
        } else {
            right--;
        }
    }

    // 최종 출력
    cout << answer_left << " " << answer_right;
    return 0;
}
