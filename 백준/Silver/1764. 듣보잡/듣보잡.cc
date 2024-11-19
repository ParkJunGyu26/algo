#include <iostream>
#include <string>
#include <unordered_map>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	vector<string> answer;
	unordered_map<string, int> infos;
	string tmp;

	int n, m;
	cin >> n >> m;
	for (int i = 0; i < n+m; i++) {
		cin >> tmp;
		if(infos.find(tmp) == infos.end()) infos[tmp] = 1;
		else infos[tmp]++;
	}

	for (auto info : infos) {
		if (info.second == 2) answer.push_back(info.first);
	}

	sort(answer.begin(), answer.end());

	cout << answer.size() << "\n";
	for (auto a : answer)
		cout << a << "\n";
}