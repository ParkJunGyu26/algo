#include <iostream>
#include <set>
#include <vector>
#include <string>

using namespace std;

set<string> res; // 중복 제거를 위한 결과 집합
string n;

// 재귀 함수: 가능한 문자열을 왼쪽과 오른쪽으로 확장
void go(vector<string> arr, int l, int r) {
    // 모든 숫자를 사용한 경우 결과 저장
    if (l == 0 && r == n.size() - 1) {
        string combined = "";
        for (const string &s : arr) {
            combined += s + ","; // 구분자 ','를 추가
        }
        res.insert(combined);
        return;
    }

    // 왼쪽으로 확장
    if (l > 0) {
        vector<string> next_arr = arr;
        next_arr.push_back(n[l - 1] + arr.back());
        go(next_arr, l - 1, r);
    }

    // 오른쪽으로 확장
    if (r < n.size() - 1) {
        vector<string> next_arr = arr;
        next_arr.push_back(arr.back() + n[r + 1]);
        go(next_arr, l, r + 1);
    }
}

int main() {
    cin >> n;

    // 각 숫자를 초기 중심으로 설정
    for (int i = 0; i < n.size(); ++i) {
        vector<string> arr = {string(1, n[i])}; // 초기 배열에 현재 숫자를 넣음
        go(arr, i, i);
    }

    cout << res.size() << "\n";
    return 0;
}
