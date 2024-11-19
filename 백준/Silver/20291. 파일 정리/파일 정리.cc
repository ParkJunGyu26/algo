#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(pair<string, int> &a, pair<string, int> &b) {
	return a.first < b.first;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	
	string str, tmp;
	bool check;
	
	int n;
	unordered_map<string, int> mp;

	cin >> n;
	for (int _ = 0; _ < n; _++) {
		cin >> str;
		tmp = "";
		check = false;
		for (int i = 0; i < str.size(); i++) {
			if (str[i] == '.') {
				check = true;
				continue;
			}

			if (check) tmp += str[i];
		}

		if (mp.find(tmp) == mp.end()) mp[tmp] = 1;
		else mp[tmp]++;
	}

	vector<pair<string, int>> v(mp.begin(), mp.end());
	sort(v.begin(), v.end(), cmp);
	for (auto vv : v)
		cout << vv.first << " " << vv.second << "\n";
}