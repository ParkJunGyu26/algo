#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<pair<int, int>> vec;

bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.second == b.second) return a.first < b.first;
	return a.second < b.second;
}

// 끝나는 시간이 빠를수록, 많은 회의를 담을 수 있다.
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vec.resize(n); // (시작, 끝)
	for (int i = 0; i < n; i++) cin >> vec[i].first >> vec[i].second;

	sort(vec.begin(), vec.end(), cmp);

	int answer = 1;
	int end_time = vec[0].second;
	for (int i = 1; i < n; i++) {
		if (end_time <= vec[i].first) {
			answer++;
			end_time = vec[i].second;
		}
	}

	cout << answer;
}