#include <iostream>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <vector>

using namespace std;

bool cmp(pair<string, int>& a, pair<string, int>& b) {
	if (a.second != b.second)
		return a.second > b.second;

	if (a.first.size() != b.first.size())
		return a.first.size() > b.first.size();

	return a.first < b.first;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	unordered_map<string, int> infos;
	int n, m;
	string str;

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		cin >> str;
		if (str.size() >= m) {
			if (infos.find(str) == infos.end()) infos[str] = 1;
			else infos[str]++;
		}
	}

	vector<pair<string, int>> v(infos.begin(), infos.end());
	sort(v.begin(), v.end(), cmp);
	for (auto b : v)
		cout << b.first << "\n";
}