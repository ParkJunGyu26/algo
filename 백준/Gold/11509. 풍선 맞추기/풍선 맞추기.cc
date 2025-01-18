#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int n;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> n;
	vector<int> H(n);
	for (int i = 0; i < n; i++) cin >> H[i];

	unordered_map<int, int> um;
	for (int i = 0; i < n; i++) {
		if(um.find(H[i]) != um.end()) {
			if (um[H[i]] == 1) um.erase(H[i]);
			else um[H[i]]--;
		}

		if (um.find(H[i]-1) == um.end()) um[H[i]-1] = 1;
		else um[H[i]-1]++;

		// for (auto u : um)
		// 	cout << u.first << " " << u.second << "\n";
		// cout << "---\n";
	}

	int answer = 0;
	for (auto u : um)
		answer += u.second;
	cout << answer;
}