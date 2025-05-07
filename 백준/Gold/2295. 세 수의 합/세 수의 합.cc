#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;

int n;
vector<int> vec;
unordered_set<int> us;

// 2중 for 사용해서, vec[i] + vec[j] 그리고 남은 하나의 범위를 이분 탐색으로 최대 범위 인덱스 찾기
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n);
	for (int i = 0; i < n; i++) cin >> vec[i];
	sort(vec.begin(), vec.end());

	for (int i = 0; i < n; i++) {
		for (int j = i; j < n; j++) us.insert(vec[i] + vec[j]);
	}

	for (int i = n-1; i >= 0; i--) {
		for (int j = 0; j < n; j++) {
			int target = vec[i] - vec[j];

			if (us.find(target) != us.end()) {
				cout << vec[i];
				return 0;
			}
		}
	}
}