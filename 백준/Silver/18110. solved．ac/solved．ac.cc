#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    cin >> n;

    // 의견이 없는 경우 0 출력
    if (n == 0) {
        cout << 0;
        return 0;
    }

    vector<int> v(n);
    for (int i = 0; i < n; i++)
        cin >> v[i];

    // 정렬
    sort(v.begin(), v.end());

    // 제외할 개수 계산 (반올림)
    int remove_cnt_top = round(n * 0.15);
    int remove_cnt_bottom = round(n * 0.15);

    // 양쪽에서 제외
    v.erase(v.begin(), v.begin() + remove_cnt_bottom);
    v.erase(v.end() - remove_cnt_top, v.end());

    // 평균 계산 및 반올림
    double avg = 0;
    for (int num : v)
        avg += num;
    avg /= v.size();

    // 평균을 정수로 반올림
    cout << round(avg);

    return 0;
}