#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int t;
vector<int> answer(2);

void three(string w, int k) {
	int min_size = 10000;
	vector<queue<int>> vec(26);

	for (int i = 0; i < w.size(); i++) {
		int index = w[i] - 'a';
		vec[index].push(i);

		if (vec[index].size() == k) {
			int now_size = 1 + abs(vec[index].front() - i);
			min_size = min(min_size, now_size);
			vec[index].pop();
		}
	}

	if (min_size != 10000) answer[0] = min_size;
}

void four(string w, int k) {
	int max_size = 0;
	vector<queue<int>> vec(26);

	for (int i = 0; i < w.size(); i++) {
		int index = w[i] - 'a';
		vec[index].push(i);

		if (vec[index].size() == k) {
			int now_size = 1 + abs(vec[index].front() - i);
			max_size = max(max_size, now_size);
			vec[index].pop();
		}
	}

	if (max_size != 10000) answer[1] = max_size;
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> t;
	for (int _ = 0; _ < t; _++) {
		string w;
		int k;
		cin >> w >> k;

		three(w, k);
		four(w, k);

		if (answer[0] == 0 || answer[1] == 0) cout << -1;
		else cout << answer[0] << " " << answer[1];
		cout << "\n";

		answer.clear();
	}
}