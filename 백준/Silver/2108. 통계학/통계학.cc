#include <iostream>
#include <unordered_map>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<int> num;
	vector<int> hubo;
	unordered_map<int, int> m;
	int many = 0;
	int SUM = 0;

	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		int tmp;
		cin >> tmp;
		num.push_back(tmp);
	}

	sort(num.begin(), num.end());
	
	for (int i = 0; i < n; i++) {
		SUM += num[i];
		if (m.find(num[i]) == m.end()) m.insert(make_pair(num[i], 1));
		else m[num[i]]++;
	}

	int cnt = 0;
	for (auto a : m) {
		if (a.second > cnt) cnt = a.second;
	}

	for (auto a : m)
		if (a.second == cnt) hubo.push_back(a.first);

	sort(hubo.begin(), hubo.end());

	if (hubo.size() >= 2) many = hubo[1];
	else many = hubo[0];

	int f = round((double)SUM/n);

	if (f == -0) f = 0;

	cout << f << "\n" << num[n/2] << "\n" << many << "\n" << num[n-1]-num[0];

	// 산평, 중앙, 최빈(2개 이상이면, 두 번째로 작은 값), 범위
}