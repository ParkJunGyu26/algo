#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n, x, y;
vector<pair<int, int>> dot; // (x, y)

bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.second != b.second) // y가 다르다면, y 좌표 오름차순
		return a.second < b.second;
	return a.first < b.first; // y가 같다면, x 좌표 오름차순
}

int main() {
	cin >> n;
	for (int i = 0; i < n; i++) {
		cin >> x >> y;
		dot.push_back({x, y});
	}

	sort(dot.begin(), dot.end(), cmp);
	for (auto d : dot)
		cout << d.first << " " << d.second << "\n";
}